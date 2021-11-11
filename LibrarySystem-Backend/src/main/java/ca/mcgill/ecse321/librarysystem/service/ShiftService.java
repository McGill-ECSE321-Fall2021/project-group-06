package ca.mcgill.ecse321.librarysystem.service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.librarysystem.dao.ShiftRepository;
import ca.mcgill.ecse321.librarysystem.models.Shift;
import ca.mcgill.ecse321.librarysystem.models.Shift.DayOfWeek;

@Service
public class ShiftService {
	@Autowired
	ShiftRepository shiftRepository;
	// @Autowired
	// LibrarianRepository librarianRepository;
	
	// /**
	//  * @author Isabella Hao
	//  * Assign Schedules
	//  * 
	//  * @param libId Librarian id
	//  * @param id shift id */
	 
    // @Transactional
    // public Shift assignSchedules(int libId, int id){
	// 	Librarian lib = librarianRepository.findLibrarianById(libId);
    // 	Shift shift = shiftRepository.findShiftByShiftID(id);
	// 	Librarian libs = shift.getLibrarian();
	// 	libs.add(lib);
	// 	shift.setLibrarian(libs);
	// 	return shift;
    // }
    
   
    
    

	//  * Edit Assigned Schedules
	//  * 
	//  * Same as update Shift
	//  * commented out
	 
    // @Transactional
    // public void EditAssignedSchedules(int shiftId, DayOfWeek DayOfWeek, Time start, Time end){
    // 	Shift shift = shiftRepository.findShiftByShiftID(shiftId);
    // 	shift.setDayOfWeek(DayOfWeek);
    // 	shift.setEndTime(end);
    // 	//shift.setHeadLibrarian(hd);
    // 	//shift.setLibrarian(lib);
    // 	shift.setStartTime(start);
	// 	shiftRepository.save(shift);
    // }
    
    
    /**
	 * @author Isabella Hao
	 * View Schedules
	 * */
	 
    @Transactional
    public Shift viewSchedule(int shiftID){
		if (shiftRepository.findShiftByShiftID(shiftID) == null){
			throw new IllegalArgumentException("Shift Id does not exist");
		}
		return shiftRepository.findShiftByShiftID(shiftID);
    }
    
    @Transactional
    public Shift getShift(int id) {
		if (shiftRepository.findShiftByShiftID(id) == null){
			throw new IllegalArgumentException("Shift Id does not exist");
		}
		return shiftRepository.findShiftByShiftID(id);
    }
    
    @Transactional
    public Shift createShift(int id, DayOfWeek DayOfWeek, Time start, Time end) {
		if (id == 0){
            throw new IllegalArgumentException("Shift Id cannot be 0");
        }
        if(shiftRepository.findShiftByShiftID(id) != null){
            throw new IllegalArgumentException("Shift Id already exists");
        }
        if (DayOfWeek == null){
            throw new IllegalArgumentException("Shift day cannot be empty");
        }
        if (start == null){
            throw new IllegalArgumentException("Shift starting time cannot be empty");
        }
        if (end == null){
            throw new IllegalArgumentException("Shift ending time cannot be empty");
        }
    	Shift shift = new Shift();
    	shift.setDayOfWeek(DayOfWeek);
    	shift.setEndTime(end);
    	//shift.setHeadLibrarian(head);
    	shift.setStartTime(start);
    	shift.setShiftID(id);
		shiftRepository.save(shift);
    	//shift.setLibrarian(librarians);
    	return shift;
    	
    }
    
    @Transactional
    public List<Shift> getShifts(){
    	Iterable<Shift> shifts= shiftRepository.findAll();
		// if (((List<Shift>) shifts).size() == 0){
		// 	throw new IllegalArgumentException("Shift list cannot be empty");
		// }
    	return toList(shifts);
    	
    }
    
    @Transactional
    public Shift deleteShift(int id) {
    	Shift shift =shiftRepository.findShiftByShiftID(id);
		if (shift == null){
			throw new IllegalArgumentException("Shift Id does not exist");
		}
    	shiftRepository.delete(shift);
    	return shift;
    }
    
    @Transactional
    public void deleteAllShift() {
    	shiftRepository.deleteAll();
    }
    
    @Transactional
    public Shift updateShift(int id, DayOfWeek DayOfWeek, Time start, Time end) {
		if (id == 0){
            throw new IllegalArgumentException("Shift Id cannot be 0");
        }
        if (DayOfWeek == null){
            throw new IllegalArgumentException("Shift day cannot be empty");
        }
        if (start == null){
            throw new IllegalArgumentException("Shift starting time cannot be empty");
        }
        if (end == null){
            throw new IllegalArgumentException("Shift ending time cannot be empty");
        }
    	Shift shift =shiftRepository.findShiftByShiftID(id);
    	shift.setDayOfWeek(DayOfWeek);
    	shift.setEndTime(end);
    	//shift.setHeadLibrarian(head);
    	shift.setStartTime(start);
    	//shift.setShiftID(id);
		shiftRepository.save(shift);
    	//shift.setLibrarian(librarians);
    	return shift;
    }
    
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
