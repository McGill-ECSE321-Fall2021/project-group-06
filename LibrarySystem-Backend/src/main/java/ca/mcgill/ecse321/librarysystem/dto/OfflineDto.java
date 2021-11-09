package ca.mcgill.ecse321.librarysystem.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;

public class OfflineDto extends AccountDto {
    
	public OfflineDto() {
	}

	public OfflineDto(int id, String address, String name, AccountCategory accountCategory, boolean isLocal, int numChecked, HashSet<EventDto> events, HashSet<MediaDto> medias) {
        super(id, address, name, accountCategory, isLocal, numChecked, events, medias);
	}

}