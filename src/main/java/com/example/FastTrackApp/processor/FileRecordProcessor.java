package com.example.FastTrackApp.processor;


import org.springframework.batch.item.ItemProcessor;

import com.example.FastTrackApp.models.FileRecord;
import com.example.FastTrackApp.models.FileRecordDTO;

public class FileRecordProcessor implements ItemProcessor<FileRecord, FileRecordDTO> {

    @Override
    public FileRecordDTO process(final FileRecord fileRecord) throws Exception {
        System.out.println("Transforming FileRecord(s) to FileRecordDTO(s)..");
        final FileRecordDTO fileRecordDto = new FileRecordDTO(fileRecord.getIDBBUNIQUE(), fileRecord.getLAST_UPDATE_DT(), fileRecord.getPX_ASK(),
        		fileRecord.getPX_BID(), fileRecord.getPX_HIGH(), fileRecord.getPX_LAST(), fileRecord.getPX_LOW(), fileRecord.getPX_MID(), fileRecord.getPX_OPEN());

        return fileRecordDto;
    }
}
