package ca.mcgill.ecse321.librarysystem.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

public class MediaDto {

    public enum Item { Book, Movie, Music, Newspaper, Archive }

    //Media Attributes
    private Item mediaType;
    private int mediaID;
  
    //Media Associations
    private AccountDto account;
    
	public MediaDto() {
	}

	// public HeadLibrarianDto(String name) {
	// 	this(name, Date.valueOf("1971-01-01"), Time.valueOf("00:00:00"), Time.valueOf("23:59:59"));
	// }

	public MediaDto(Item mediaType, int mediaID, AccountDto account) {
        this.mediaType = mediaType;
        this.mediaID = mediaID;
        this.account = account;
	}

    public Item getMediaType() {
        return mediaType;
    }

    public int getMediaID() {
        return mediaID;
    }

    public AccountDto getAccount() {
        return account;
    }

}