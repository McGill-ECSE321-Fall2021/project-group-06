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
import ca.mcgill.ecse321.librarysystem.models.Account;
import ca.mcgill.ecse321.librarysystem.models.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem.models.Librarian;
import ca.mcgill.ecse321.librarysystem.models.OpeningHour;
import ca.mcgill.ecse321.librarysystem.models.Shift;


public class ShiftService {
	@Autowired
	ShiftRepository shiftRepository;
	
	/**
	 * @author Isabella Hao
	 * Assign Schedules
	 * 
	 * @param id Librarian id
	 */
    @Transactional
    public List<Shift> AssignSchedules(int id, Date date){
		return null;
    	
    }
    
   
    
    /**
	 * @author Isabella Hao
	 * Edit Assigned Schedules
	 * 
	 */
    @Transactional
    public List<Shift> EditAssignedSchedules(int shiftId, Date date){
		return null;
    	
    }
    
    /**
	 * @author Isabella Hao
	 * View Schedules
	 * 
	 */
    @Transactional
    public List<Shift> ViewSchedule(int id, Date date){
		return null;
    	
    }
    
    @Transactional
    public List<Shift> getShifts(){
    	List<Shift> shifts=(List<Shift>) shiftRepository.findAll();
    	return shifts;
    	
    }
    
    @Transactional
    public void deleteShift(int id) {
    	shiftRepository.delete(shiftRepository.findShiftByShiftID(id));
    }
   
}
