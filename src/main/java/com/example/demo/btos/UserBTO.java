package com.example.demo.btos;


import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UserBTO {

    private String userName;
    private String password;
    private String email;
    private Date passwordUpdateTimestamp;
    private Date lastConnTimestamp;
    private Timestamp dateInscription;
    
    public Timestamp getDateInscription() {
		long millis = System.currentTimeMillis();
		this.setDateInscription(new Timestamp(millis));
		return this.dateInscription;
    }
//    public Date getPasswordUpdateTimestamp() {
//		long millis = System.currentTimeMillis();
//		this.setPasswordUpdateTimestamp(new Date(millis));
//		return this.passwordUpdateTimestamp;
//    }
//    public Date getLastConnTimestamp() {
//		long millis = System.currentTimeMillis();
//		this.setLastConnTimestamp(new Date(millis));
//		return this.lastConnTimestamp;
//    }
}
