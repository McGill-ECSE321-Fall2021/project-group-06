package ca.mcgill.ecse321.librarysystem.controller;
import java.util.HashSet;
import ca.mcgill.ecse321.librarysystem.dto.*;
import ca.mcgill.ecse321.librarysystem.models.*;

public class Conversion {

    public static OpeningHourDto convertToDto(OpeningHour openingHour){
        if(openingHour == null) throw new IllegalArgumentException("Opening Hour not found.");
        return new OpeningHourDto(openingHour.getId(), openingHour.getDayOfWeek(), openingHour.getStartTime(), openingHour.getEndTime());
    }

    public static ShiftDto convertToDto(Shift shift){
        if(shift == null) throw new IllegalArgumentException("Shift not found.");
        return new ShiftDto(shift.getShiftID(),shift.getDayOfWeek(),shift.getStartTime(),shift.getEndTime());
    }

    public static LibrarianDto convertToDto(Librarian librarian){
        if(librarian == null) throw new IllegalArgumentException("libarian not found.");
        HashSet<ShiftDto> shifts = new HashSet<ShiftDto>();
        HashSet<Shift> modelShifts = (HashSet)librarian.getShift();
        for(Shift shift: modelShifts){
            shifts.add(convertToDto(shift));
        }
        return new LibrarianDto(shifts);
    }

    public static HeadLibrarianDto convertToDto(HeadLibrarian headLibrarian){
        if(headLibrarian == null) throw new IllegalArgumentException("headLibarian not found.");
        HashSet<ShiftDto> shifts = new HashSet<ShiftDto>();
        HashSet<Shift> modelShifts = (HashSet)headLibrarian.getShift();
        for(Shift shift: modelShifts){
            shifts.add(convertToDto(shift));
        }
        HashSet<OpeningHourDto> openingHours = new HashSet<OpeningHourDto>();
        HashSet<OpeningHour> modelOpeningHours = (HashSet)headLibrarian.getOpeningHour();
        for(OpeningHour openingHour: modelOpeningHours){
            openingHours.add(convertToDto(openingHour));
        }
        return new HeadLibrarianDto(openingHours, shifts);
    }
    
    public static CheckOutItemDto convertToDto(CheckOutItem checkOutItem) {
		if(checkOutItem==null) throw new IllegalArgumentException("Checkout item not found.");
		return new CheckOutItemDto(checkOutItem.getID(),checkOutItem.getIsCheckedOut(),checkOutItem.getIsReserved(), checkOutItem.getBorrowingPeriod(), checkOutItem.getStartDate());
	}

    public static NonCheckOutItemDto convertToDto(NonCheckOutItem nonCheckOutItem) {
		if(nonCheckOutItem==null) throw new IllegalArgumentException("NonCheckout item not found.");
        return new NonCheckOutItemDto(nonCheckOutItem.getType(), nonCheckOutItem.getID());
	}

    public static EventDto convertToDto(Event event){
        if(event==null) throw new IllegalArgumentException("Event not found.");
        return new EventDto(event.getName(),event.getDate(), event.getEventStart(),event.getEventEnd());
    }
    
    public static OfflineDto convertToDto(Offline offline){
        if(offline==null) throw new IllegalArgumentException("Customer not found.");
        
        HashSet<EventDto> events = new HashSet<EventDto>();
        HashSet<Event> modelEvents = (HashSet)offline.getEvents();
        for(Event event: modelEvents){
            events.add(convertToDto(event));
        }

        HashSet<MediaDto> medias = new HashSet<MediaDto>();
        HashSet<Media> modelMedias = (HashSet)offline.getMedias();
        for(Media media: modelMedias){
            if(media instanceof CheckOutItem){
                medias.add(convertToDto((CheckOutItem)media));
            }
            if(media instanceof NonCheckOutItem){
                medias.add(convertToDto((NonCheckOutItem)media));
            }
        }
        return new OfflineDto(offline.getId(), offline.getAddress(), offline.getName(), offline.getAccountCategory(), offline.getIsLocal(), offline.getNumChecked(), events, medias);
    }
    
    public static OnlineDto convertToDto(Online online){
        if(online==null) throw new IllegalArgumentException("Customer not found.");
        HashSet<EventDto> events = new HashSet<EventDto>();
        HashSet<Event> modelEvents = (HashSet)online.getEvents();
        for(Event event: modelEvents){
            events.add(convertToDto(event));
        }

        HashSet<MediaDto> medias = new HashSet<MediaDto>();
        HashSet<Media> modelMedias = (HashSet)online.getMedias();
        for(Media media: modelMedias){
            if(media instanceof CheckOutItem){
                medias.add(convertToDto((CheckOutItem)media));
            }
            if(media instanceof NonCheckOutItem){
                medias.add(convertToDto((NonCheckOutItem)media));
            }
        }
        return new OnlineDto(online.getId(), online.getAddress(), online.getName(), online.getAccountCategory(), online.getIsLocal(), online.getNumChecked(), events, medias, online.getUsername(), online.getPassword(), online.getEmail());
    }
}
