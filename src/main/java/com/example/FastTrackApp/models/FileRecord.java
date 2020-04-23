package com.example.FastTrackApp.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileRecord implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String IDBBUNIQUE;
	private String LAST_UPDATE_DT, PX_BID, PX_MID, PX_ASK, PX_OPEN, PX_HIGH, PX_LOW, PX_LAST;
}
