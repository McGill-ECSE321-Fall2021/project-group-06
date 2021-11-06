package ca.mcgill.ecse321.librarysystem.service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.AccountRepository;
import ca.mcgill.ecse321.librarysystem.dao.ShiftRepository;
import ca.mcgill.ecse321.librarysystem.models.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem.models.Librarian;
import ca.mcgill.ecse321.librarysystem.models.Shift;

@Service
public class ShiftService {
	@Autowired
	ShiftRepository shiftRepository;
	@Autowired
	AccountRepository accountRepository;
	
	// /**
	//  * @author Isabella Hao
	//  * Assign Schedules
	//  * 
	//  * @param libId Librarian id
	//  * @param id shift id */
	 
    @Transactional
    public Shift assignSchedules(int libId, int id){
		Librarian lib = (Librarian) accountRepository.findAccountById(libId);
    	Shift shift = shiftRepository.findShiftByShiftID(id);
		Set<Librarian> libs = shift.getLibrarian();
		libs.add(lib);
		shift.setLibrarian(libs);
		return shift;
    }
    
   
    
    

	//  * Edit Assigned Schedules
	//  * 
	//  * Same as update Shift
	//  * commented out
	 
    @Transactional
    public void EditAssignedSchedules(int shiftId, Set<Librarian> libs,
    		HeadLibrarian hd, Date date, Time start, Time end){
    	Shift shift = shiftRepository.findShiftByShiftID(shiftId);
    	shift.setDate(date);
    	shift.setEndTime(end);
    	shift.setHeadLibrarian(hd);
    	shift.setLibrarian(libs);
    	shift.setStartTime(start);
    }
    
    
    /**
	 * @author Isabella Hao
	 * View Schedules
	 * */
	 
    @Transactional
    public Shift viewSchedule(int shiftID){
    	Shift shift = shiftRepository.findShiftByShiftID(shiftID); 
    	return shift;
    }
    
    @Transactional
    public Shift getShift(int id) {
    	Shift shift = shiftRepository.findShiftByShiftID(id);
    	return shift;
    }
    
    @Transactional
    public Shift createShift(int id, HeadLibrarian head, Set<Librarian> librarians, 
    		Date date, Time start, Time end) {
    	Shift shift = new Shift();
    	shift.setDate(date);
    	shift.setEndTime(end);
    	shift.setHeadLibrarian(head);
    	shift.setStartTime(start);
    	shift.setShiftID(id);
    	shift.setLibrarian(librarians);
    	return shift;
    	
    }
    
    @Transactional
    public List<Shift> getShifts(){
    	List<Shift> shifts=(List<Shift>) shiftRepository.findAll();
    	return shifts;
    	
    }
    
    @Transactional
    public Shift deleteShift(int id) {
    	Shift shift =shiftRepository.findShiftByShiftID(id);
    	shiftRepository.delete(shift);
    	return shift;
    }
    
    @Transactional
    public void deleteAllShift() {
    	shiftRepository.deleteAll();
    }
    
    @Transactional
    public Shift updateShift(int id, HeadLibrarian head, Set<Librarian> librarians, 
    		Date date, Time start, Time end) {
    	Shift shift =shiftRepository.findShiftByShiftID(id);
    	shift.setDate(date);
    	shift.setEndTime(end);
    	shift.setHeadLibrarian(head);
    	shift.setStartTime(start);
    	shift.setShiftID(id);
    	shift.setLibrarian(librarians);
    	return shift;
    }
    
   
}
