package ca.mcgill.ecse321.librarysystem.controller;
import java.util.HashSet;
import ca.mcgill.ecse321.librarysystem.dto.*;
import ca.mcgill.ecse321.librarysystem.models.CheckOutItem;
import ca.mcgill.ecse321.librarysystem.models.Offline;
import ca.mcgill.ecse321.librarysystem.models.OpeningHour;

public class Conversion {
    public static OpeningHourDto convertToDto(OpeningHour openingHour){
        if(openingHour == null) throw new IllegalArgumentException("Opening Hour not found.");
        return new OpeningHourDto(openingHour.getId(), openingHour.getDayOfWeek(), openingHour.getStartTime(), openingHour.getEndTime());
    }
  
    public static CheckOutItemDto convertToDTO(CheckOutItem checkOutItem) {
		if(checkOutItem==null) throw new IllegalArgumentException("Checkout item not found.");
		CheckOutItemDto checkOutItemDto = new CheckOutItemDto(checkOutItem.getID(),checkOutItem.getIsCheckedOut(),checkOutItem.getIsReserved(), checkOutItem.getBorrowingPeriod(), checkOutItem.getStartDate());
        return checkOutItemDto;
	  }
  
    public static OfflineDto convertToDto(Offline offline){
        if(offline==null) throw new IllegalArgumentException("Customer not found.");
        HashSet<EventDto> howard = null;
        HashSet<MediaDto> howard2 = null;
        return new OfflineDto(offline.getId(), offline.getAddress(), offline.getName(), offline.getAccountCategory(), offline.getIsLocal(), offline.getNumChecked(), howard, howard2);
    }
}
