package com.example.FastTrackApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.FastTrackApp.models.FileRecord;

@Repository
public interface FileRecordRepo extends JpaRepository<FileRecord, String>, JpaSpecificationExecutor<FileRecord> {

	@Query(value= "SELECT * FROM filerecord u WHERE u.IDBBUNIQUE = ?1 ",  nativeQuery = true)
	public FileRecord findRecordByIDBBUNIQUE(String IDBBUNIQUE);
}
