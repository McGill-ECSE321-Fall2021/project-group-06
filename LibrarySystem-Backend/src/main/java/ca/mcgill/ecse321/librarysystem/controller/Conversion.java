package ca.mcgill.ecse321.librarysystem.controller;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Set<Shift> modelShifts = librarian.getShift();
        if (modelShifts!=null){
            for(Shift shift: modelShifts){
                shifts.add(convertToDto(shift));
            }
        }
        
        return new LibrarianDto(librarian.getId(), shifts);
    }

    public static HeadLibrarianDto convertToDto(HeadLibrarian headLibrarian){
        if(headLibrarian == null) throw new IllegalArgumentException("headLibarian not found.");
        HashSet<ShiftDto> shifts = new HashSet<ShiftDto>();
        Set<Shift> modelShifts = headLibrarian.getShift();
        if (modelShifts!=null){
            for(Shift shift: modelShifts){
                shifts.add(convertToDto(shift));
            }
        }
        HashSet<OpeningHourDto> openingHours = new HashSet<OpeningHourDto>();
        Set<OpeningHour> modelOpeningHours = headLibrarian.getOpeningHour();
        if (modelOpeningHours!= null){
            for(OpeningHour openingHour: modelOpeningHours){
                openingHours.add(convertToDto(openingHour));
            }
        }
        
        return new HeadLibrarianDto(headLibrarian.getId(), openingHours, shifts);
    }
    
    public static CheckOutItemDto convertToDto(CheckOutItem checkOutItem) {
		if(checkOutItem==null) throw new IllegalArgumentException("Checkout item not found.");
		return new CheckOutItemDto(checkOutItem.getID(),checkOutItem.getIsCheckedOut(),checkOutItem.getIsReserved(), checkOutItem.getBorrowingPeriod(), checkOutItem.getStartDate());
	}

    public static NonCheckOutItemDto convertToDto(NonCheckOutItem nonCheckOutItem) {
		if(nonCheckOutItem==null) throw new IllegalArgumentException("NonCheckout item not found.");
        return new NonCheckOutItemDto(nonCheckOutItem.getType(), nonCheckOutItem.getID());
	}
    
    public static OfflineDto convertToDto(Offline offline){
        if(offline==null) throw new IllegalArgumentException("Account not found.");
        
        HashSet<EventDto> events = new HashSet<EventDto>();
        Set<Event> modelEvents = offline.getEvents();
        if(modelEvents!=null){
            for(Event event: modelEvents){
                events.add(convertToDto(event));
            }
        }
        HashSet<MediaDto> medias = new HashSet<MediaDto>();
        Set<Media> modelMedias = offline.getMedias();
        if(modelMedias!=null){
            for(Media media: modelMedias){
                if(media instanceof CheckOutItem){
                    medias.add(convertToDto((CheckOutItem)media));
                }
                if(media instanceof NonCheckOutItem){
                    medias.add(convertToDto((NonCheckOutItem)media));
                }
            }
        }
        
        return new OfflineDto(offline.getId(), offline.getAddress(), offline.getName(), offline.getAccountCategory(), offline.getIsLocal(), offline.getNumChecked(), events, medias);
    }
    
    public static OnlineDto convertToDto(Online online){
        if(online==null) throw new IllegalArgumentException("Customer not found.");
        HashSet<EventDto> events = new HashSet<EventDto>();
        Set<Event> modelEvents = online.getEvents();
        if(modelEvents != null){
            for(Event event: modelEvents){
                events.add(convertToDto(event));
            }
        }
        

        HashSet<MediaDto> medias = new HashSet<MediaDto>();
        Set<Media> modelMedias = online.getMedias();
        if(modelMedias != null){
            for(Media media: modelMedias){
                if(media instanceof CheckOutItem){
                    medias.add(convertToDto((CheckOutItem)media));
                }
                if(media instanceof NonCheckOutItem){
                    medias.add(convertToDto((NonCheckOutItem)media));
                }
            }
        }
        
        return new OnlineDto(online.getId(), online.getAddress(), online.getName(), online.getAccountCategory(), online.getIsLocal(), online.getNumChecked(), events, medias, online.getUsername(), online.getPassword(), online.getEmail());
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
    public static Time convertStrToTime(String t){
        String[] num = t.split(":");
        int hour = Integer.parseInt(num[0]);
        int minutes = Integer.parseInt(num[1]);
        Time time = java.sql.Time.valueOf(LocalTime.of(hour,minutes));
        return time;
    }
}
