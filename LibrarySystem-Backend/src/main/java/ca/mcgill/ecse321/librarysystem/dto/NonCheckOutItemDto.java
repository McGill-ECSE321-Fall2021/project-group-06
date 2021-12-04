package ca.mcgill.ecse321.librarysystem.dto;
import ca.mcgill.ecse321.librarysystem.models.Media.Item;
/**
* @author Howard
*/
public class NonCheckOutItemDto extends MediaDto {
    
	public NonCheckOutItemDto(){

	}
	public NonCheckOutItemDto(Item mediaType, String name, int mediaID){
		super(mediaType,name,mediaID);
	}

}