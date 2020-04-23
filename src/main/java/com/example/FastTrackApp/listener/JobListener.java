package com.example.FastTrackApp.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.FastTrackApp.models.FileRecordDTO;

@Component
public class JobListener extends JobExecutionListenerSupport {
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public JobListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            jdbcTemplate.query("SELECT IDBBUNIQUE,LAST_UPDATE_DT,PX_BID,PX_MID,PX_ASK,PX_OPEN,PX_HIGH,PX_LOW, PX_LAST FROM filerecord",
                    (rs,rowNum)->{
                        return new FileRecordDTO(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),
                                rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
                    }
            );
        }
    }
}
