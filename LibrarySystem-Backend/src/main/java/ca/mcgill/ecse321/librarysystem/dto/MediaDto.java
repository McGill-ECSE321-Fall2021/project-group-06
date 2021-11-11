package ca.mcgill.ecse321.librarysystem.dto;
import ca.mcgill.ecse321.librarysystem.models.Media.Item;

/**
* @author Howard
*/
public abstract class MediaDto {

    private Item mediaType;
    private int mediaID;
	public MediaDto() {
	}

    public MediaDto(int mediaID){
        this.mediaID = mediaID;
        this.mediaType = Item.Book;
    }

	public MediaDto(Item mediaType, int mediaID) {
        this.mediaType = mediaType;
        this.mediaID = mediaID;
	}

    public Item getMediaType() {
        return mediaType;
    }

    public int getMediaID() {
        return mediaID;
    }

    

}