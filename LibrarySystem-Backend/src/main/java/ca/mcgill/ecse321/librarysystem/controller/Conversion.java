package ca.mcgill.ecse321.librarysystem.controller;
import java.util.HashSet;
import ca.mcgill.ecse321.librarysystem.dto.*;
import ca.mcgill.ecse321.librarysystem.models.*;

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
    
    public static EventDto convertToDto(Event event){
        if(event==null) throw new IllegalArgumentException("Event not found.");
        return new EventDto(event.getName(),event.getDate(), event.getEventStart(),event.getEventEnd());
    }
    public static OfflineDto convertToDto(Offline offline){
        if(offline==null) throw new IllegalArgumentException("Customer not found.");
        
        HashSet<EventDto> howard = (HashSet)offline.getEvents();

        HashSet<MediaDto> howard2 = (HashSet)offline.getMedias();
        return new OfflineDto(offline.getId(), offline.getAddress(), offline.getName(), offline.getAccountCategory(), offline.getIsLocal(), offline.getNumChecked(), howard, howard2);
    }
    public static OnlineDto convertToDto(Online online){
        if(online==null) throw new IllegalArgumentException("Customer not found.");
        HashSet<EventDto> howard = null;
        HashSet<MediaDto> howard2 = null;
        return new OnlineDto(online.getId(), online.getAddress(), online.getName(), online.getAccountCategory(), online.getIsLocal(), online.getNumChecked(), howard, howard2, online.getUsername(), online.getPassword(), online.getEmail());
    }
}
