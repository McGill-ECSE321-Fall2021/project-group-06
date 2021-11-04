package ca.mcgill.ecse321.librarysystem.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.AccountRepository;
import ca.mcgill.ecse321.librarysystem.dao.EventRepository;
import ca.mcgill.ecse321.librarysystem.dao.MediaRepository;
import ca.mcgill.ecse321.librarysystem.dao.OpeningHourRepository;
import ca.mcgill.ecse321.librarysystem.dao.ShiftRepository;
import ca.mcgill.ecse321.librarysystem.dto.OpeningHourDto;
import ca.mcgill.ecse321.librarysystem.models.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem.models.Librarian;
import ca.mcgill.ecse321.librarysystem.models.OpeningHour;
import ca.mcgill.ecse321.librarysystem.models.Shift;

public class HeadLibrarianService {
	@Autowired
	ShiftRepository shiftRepository;
	@Autowired
	AccountRepository accountRepository;
	/**
	 * @author Isabella Hao
	 * Hire Librarian
	 * 
	 */
	@Transactional
    public Librarian HireLibrarian () {
		return null;
		
	}
	
	/**
	 * @author Isabella Hao
	 * Fire Librarian
	 * 
	 */
    @Transactional
    public void FireLibrarian() {
    	
    }
    
    /**
	 * @author Isabella Hao
	 * Create Schedule
	 * 
	 */
    @Transactional
    public List<Shift> CreateSchedule(){
		return null;
    	
    }
}
