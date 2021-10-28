package ca.mcgill.ecse321.librarysystem.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

public class LibrarySystemDto {

    private Set<AccountDto> accounts;
    private Set<OpeningHourDto> openingHours;
    private Set<EventDto> events;
    private Set<MediaDto> medias;
    
	public LibrarySystemDto() {
	}

	// public HeadLibrarianDto(String name) {
	// 	this(name, Date.valueOf("1971-01-01"), Time.valueOf("00:00:00"), Time.valueOf("23:59:59"));
	// }

	public LibrarySystemDto( HashSet<AccountDto> accounts, HashSet<OpeningHourDto> openingHours, HashSet<EventDto> events, HashSet<MediaDto> medias) {
        this.accounts = accounts;
        this.openingHours = openingHours;
        this.events = events;
        this.medias = medias;
	}

    public Set<AccountDto> getAccounts() {
        return accounts;
    }

    public Set<OpeningHourDto> getOpeningHours() {
        return openingHours;
    }

    public Set<EventDto> getEvents() {
        return events;
    }

    public Set<MediaDto> getMedias() {
        return medias;
    }

}