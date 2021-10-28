package ca.mcgill.ecse321.librarysystem.dto;

import java.sql.Date;
import java.sql.Time;

public class OnlineDto {

    private String username;
    private String password;
    private String email;
    
	public OnlineDto() {
	}

	// public OnlineDto(String name) {
	// 	this(name, Date.valueOf("1971-01-01"), Time.valueOf("00:00:00"), Time.valueOf("23:59:59"));
	// }

	public OnlineDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
	}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
    
}