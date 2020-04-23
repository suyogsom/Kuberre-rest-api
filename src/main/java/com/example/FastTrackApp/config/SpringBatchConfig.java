package com.example.FastTrackApp.config;

import com.example.FastTrackApp.listener.JobListener;
import com.example.FastTrackApp.models.FileRecord;
import com.example.FastTrackApp.models.FileRecordDTO;
import com.example.FastTrackApp.processor.FileRecordProcessor;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Bean
    public FlatFileItemReader<FileRecord> reader() {
        FlatFileItemReader<FileRecord> reader = new FlatFileItemReader<FileRecord>();
        reader.setResource(new ClassPathResource("data.csv"));

        reader.setLineMapper(new DefaultLineMapper<FileRecord>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "IDBBUNIQUE", "LAST_UPDATE_DT","PX_BID","PX_MID","PX_ASK","PX_OPEN","PX_HIGH","PX_LOW", "PX_LAST" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper() {{
                setTargetType(FileRecord.class);
            }});
        }});
        return reader;
    }


    @Bean
    public FileRecordProcessor processor() {
        return new FileRecordProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<FileRecordDTO> writer() {
        JdbcBatchItemWriter<FileRecordDTO> writer = new JdbcBatchItemWriter<FileRecordDTO>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO filerecord (IDBBUNIQUE,LAST_UPDATE_DT,PX_BID,PX_MID,PX_ASK,PX_OPEN,PX_HIGH,PX_LOW, PX_LAST) " +
                "VALUES (:IDBBUNIQUE, :LAST_UPDATE_DT,:PX_BID,:PX_MID,:PX_ASK,:PX_OPEN,:PX_HIGH,:PX_LOW, :PX_LAST)");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public Job importUserJob(JobListener listener) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<FileRecord, FileRecordDTO> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

}
