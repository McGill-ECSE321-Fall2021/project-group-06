package ca.mcgill.ecse321.librarysystem.dto;

import java.sql.Date;
import java.sql.Time;

public class OnlineDto {

    private String username;
    private String password;
    private String email;
    
	public OnlineDto() {
	}

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