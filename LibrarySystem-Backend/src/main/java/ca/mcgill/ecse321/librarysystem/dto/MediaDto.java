package ca.mcgill.ecse321.librarysystem.dto;
import ca.mcgill.ecse321.librarysystem.models.Media.Item;

/**
* @author Howard
*/
public abstract class MediaDto {

    private Item mediaType;
    private int mediaID;
    private String name;
	public MediaDto() {
	}

    public MediaDto(int mediaID){
        this.mediaID = mediaID;
        this.mediaType = Item.Book;
    }

	public MediaDto(Item mediaType, String name, int mediaID) {
        this.mediaType = mediaType;
        this.mediaID = mediaID;
        this.name = name;
	}

    public Item getMediaType() {
        return mediaType;
    }

    public int getMediaID() {
        return mediaID;
    }

    public String getMediaName(){
        return name;
    }

    

}