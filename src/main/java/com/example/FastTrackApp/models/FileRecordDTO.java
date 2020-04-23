package com.example.FastTrackApp.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileRecordDTO {	

	@Id
	private String IDBBUNIQUE;
	private String LAST_UPDATE_DT, PX_BID, PX_MID, PX_ASK, PX_OPEN, PX_HIGH, PX_LOW, PX_LAST;

	/*  // if required then we can do this
	@Override
    public String toString() {
        return "EmployeeDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }*/
	
}
