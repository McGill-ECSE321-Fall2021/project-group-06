package ca.mcgill.ecse321.librarysystem.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

public abstract class MediaDto {

    public enum Item { Book, Movie, Music, Newspaper, Archive }

    //Media Attributes
    private Item mediaType;
    private int mediaID;
  
    //Media Associations
    private AccountDto account;
    
	public MediaDto() {
	}

    public MediaDto(int mediaID){
        this.mediaID = mediaID;
        this.mediaType = Item.Book;
    }

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