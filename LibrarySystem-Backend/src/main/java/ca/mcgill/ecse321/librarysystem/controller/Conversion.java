package ca.mcgill.ecse321.librarysystem.controller;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import ca.mcgill.ecse321.librarysystem.dto.*;
import ca.mcgill.ecse321.librarysystem.models.CheckOutItem;
import ca.mcgill.ecse321.librarysystem.models.Offline;
import ca.mcgill.ecse321.librarysystem.models.OpeningHour;
import ca.mcgill.ecse321.librarysystem.models.Event;

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

    public static EventDto convertToDto(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Event not found");
        }
        EventDto eventDto = new EventDto(event.getName(),event.getDate(),event.getEventStart(),event.getEventEnd());
        return eventDto;
    }

    public static List<EventDto> convertToDto(List<Event> eventList) {
        List<EventDto> eventDtoList = new ArrayList<>();
        
        if (eventList.size() == 0){
            throw new IllegalArgumentException("No elements inside Event List");
        }

        for (Event event: eventList){
            EventDto eventDto = convertToDto(event);
            eventDtoList.add(eventDto);
        }
        
        return eventDtoList;
    }
}
