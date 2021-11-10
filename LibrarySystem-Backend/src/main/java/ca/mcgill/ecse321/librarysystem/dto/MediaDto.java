package ca.mcgill.ecse321.librarysystem.dto;
import ca.mcgill.ecse321.librarysystem.models.Media.Item;


public abstract class MediaDto {

    //Media Attributes
    private Item mediaType;
    private int mediaID;
  
    //Media Associations
    //private AccountDto account;
    
	public MediaDto() {
	}

    public MediaDto(int mediaID){
        this.mediaID = mediaID;
        this.mediaType = Item.Book;
        //this.account = null;
    }

	public MediaDto(Item mediaType, int mediaID) {
        this.mediaType = mediaType;
        this.mediaID = mediaID;
        //this.account = account;
	}

    public Item getMediaType() {
        return mediaType;
    }

    public int getMediaID() {
        return mediaID;
    }

    

}