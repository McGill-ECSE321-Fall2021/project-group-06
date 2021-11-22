package ca.mcgill.ecse321.librarysystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.AccountRepository;
import ca.mcgill.ecse321.librarysystem.dao.EventRepository;
import ca.mcgill.ecse321.librarysystem.dao.MediaRepository;
import ca.mcgill.ecse321.librarysystem.dao.OpeningHourRepository;
import ca.mcgill.ecse321.librarysystem.dao.ShiftRepository;
import ca.mcgill.ecse321.librarysystem.dao.LibrarianRepository;
import ca.mcgill.ecse321.librarysystem.models.Shift.DayOfWeek;
import ca.mcgill.ecse321.librarysystem.models.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem.models.Librarian;
import ca.mcgill.ecse321.librarysystem.models.OpeningHour;
import ca.mcgill.ecse321.librarysystem.models.Shift;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

//Joyce Liu
@Service
public class HeadLibrarianService {

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	LibrarianRepository librarianRepository;
	@Autowired
	EventRepository eventRepository;
	@Autowired
	MediaRepository mediaRepository;
	@Autowired
	OpeningHourRepository openingHourRepository;
	@Autowired
	ShiftRepository shiftRepository;

	/**
	 * Create a head librarian with given parameters
	 * @param aId
	 * @param aAddress
	 * @param aName
	 * @param accountCategory
	 * @param local
	 * @param itemsChecked
	 * @return the head librarian created
	 */

	@Transactional
	public HeadLibrarian createHeadLibrarian(int aId, String password) {
		if (aId==0) {
			throw new IllegalArgumentException("Head Librarian id cannot be 0.");
		}
		if (librarianRepository.findLibrarianById(aId)!=null) {
			throw new IllegalArgumentException("Librarian id already exists");
		}
		if (password==null) {
			throw new IllegalArgumentException("Password cannot be 0.");
		}
		HeadLibrarian head=new HeadLibrarian();
		head.setId(aId);
		head.setPassword(password);
		librarianRepository.save(head);
		return head;
	}
	/**
	 * Find head librarian with given parameter
	 * @param aId
	 * @return the head librarian
	 */
	@Transactional
	public HeadLibrarian getHeadLibrarian(int aId) {
		if (librarianRepository.findLibrarianById(aId)==null) {
			throw new IllegalArgumentException("Librarian id does not exist");
		}
		return (HeadLibrarian) librarianRepository.findLibrarianById(aId);
	}

	//    /**
	//     * Find a head librarian and change their info with given parameters
	//     * @param aId
	//     * @param aAddress
	//     * @param aName
	//     * @param itemsChecked
	//     */
	//     @Transactional
	//     public HeadLibrarian updateHeadLibrarianInfo(int aId, int newID) {
	//       if (librarianRepository.findLibrarianById(aId)==null) {
	//         throw new IllegalArgumentException("Librarian does not exist");
	//       }
	//       if (librarianRepository.findLibrarianById(newID)!=null) {
	//         throw new IllegalArgumentException("Librarian id already exists");
	//       }
	//     	HeadLibrarian head=(HeadLibrarian) librarianRepository.findLibrarianById(aId);
	//		   head.setId(newID);
	//       librarianRepository.save(head);
	//		   return head;
	//     }

	/**
	 * Delete head librarian corresponding to the given parameter
	 * @param aId
	 */
	@Transactional
	public HeadLibrarian deleteHeadLibrarian(int aId) {
		if (librarianRepository.findLibrarianById(aId)==null) {
			throw new IllegalArgumentException("Librarian does not exist");
		}
		HeadLibrarian head=(HeadLibrarian) librarianRepository.findLibrarianById(aId);
		//shiftRepository.deleteAll(shiftRepository.findByLibrarian((Librarian) head));
		librarianRepository.delete(head);
		return head;
	}

	/**
	 * Create librarian with given parameters
	 * @param aId
	 * @param aAddress
	 * @param aName
	 * @param accountCategory
	 * @param local
	 * @param itemsChecked
	 * @return
	 */
	@Transactional
	public Librarian hireLibrarian(int aId, String password) {
		//i.e. createLibrarian
		if (aId==0) {
			throw new IllegalArgumentException("Librarian id cannot be 0.");
		}
		if (password==null) {
			throw new IllegalArgumentException("Password cannot be 0.");
		}
		if (librarianRepository.findLibrarianById(aId)!=null) {
			throw new IllegalArgumentException("Librarian id exists");
		}
		Librarian librarian=new Librarian();
		librarian.setId(aId);
		librarian.setPassword(password);
		librarianRepository.save(librarian);
		return librarian;
	}

	/**
	 * Delete librarian corresponding to given parameter
	 * @param aId
	 */
	@Transactional
	public Librarian fireLibrarian(int aId) {	//i.e. deleteLibrarian
		if (aId==0) {
			throw new IllegalArgumentException("There is not an account with id 0.");
		}
		if (librarianRepository.findLibrarianById(aId)==null) {
			throw new IllegalArgumentException("Librarian id does not exist");
		}
		Librarian firedLibr=librarianRepository.findLibrarianById(aId);
		//shiftRepository.deleteAll(shiftRepository.findByLibrarian((Librarian) firedLibr));
		librarianRepository.delete(firedLibr);
		return (Librarian) firedLibr;
	}

	/**
	 * Find all head librarians
	 * @return list of all head librarians
	 */
	@Transactional
	public List<HeadLibrarian> getHeadLibrarians(){
		List<Librarian> allLibrarians=(List<Librarian>) librarianRepository.findAll();
		List<HeadLibrarian> allHeadLibrarians=new ArrayList<>();
		for (Librarian a : allLibrarians) {
			if (a instanceof HeadLibrarian){
				allHeadLibrarians.add((HeadLibrarian) a);
			}
		}
		return allHeadLibrarians;
	}
	
	/**
	 * Create a set of opening hours with the given parameters
	 * @param id
	 * @param dayOfWeek
	 * @param startTime
	 * @param endTime
	 * @return the opening hours created
	 */
	@Transactional
	public OpeningHour createOpeningHour(int id, DayOfWeek dayOfWeek, Time startTime, Time endTime){
		if (openingHourRepository.findOpeningHourById(id)!=null) {
			throw new IllegalArgumentException("opening hour exists");
		}
		if (id == 0) {
			throw new IllegalArgumentException("opening hour id cannot be 0");
		}
		if (dayOfWeek == null) {
			throw new IllegalArgumentException("day of week cannot be null");
		}
		if (startTime == null) {
			throw new IllegalArgumentException("startTime cannot be null");
		}
		if (endTime == null) {
			throw new IllegalArgumentException("endTime cannot be null");
		}
		OpeningHour openingHour= new OpeningHour();
		openingHour.setDayOfWeek(dayOfWeek);
		openingHour.setEndTime(endTime);
		openingHour.setId(id);
		openingHour.setStartTime(startTime);
		openingHourRepository.save(openingHour);
		return openingHour;
	}
	
	/**
	 * Find the corresponding opening hours and update it with the given parameters
	 * @param id
	 * @param newDayOfWeek
	 * @param newStartTime
	 * @param newEndTime
	 * @return the updated opening hours
	 */
	@Transactional
	public OpeningHour updateOpeningHour(int id, DayOfWeek newDayOfWeek, Time newStartTime, Time newEndTime){
		if (openingHourRepository.findOpeningHourById(id)==null) {
			throw new IllegalArgumentException("opening hour does not exist");
		}
		if (newDayOfWeek == null) {
			throw new IllegalArgumentException("day of week cannot be null");
		}
		if (newStartTime == null) {
			throw new IllegalArgumentException("startTime cannot be null");
		}
		if (newEndTime == null) {
			throw new IllegalArgumentException("endTime cannot be null");
		}
		OpeningHour openingHour= new OpeningHour();
		openingHour.setDayOfWeek(newDayOfWeek);
		openingHour.setEndTime(newEndTime);
		openingHour.setId(id);
		openingHour.setStartTime(newStartTime);
		openingHourRepository.save(openingHour);
		return openingHour;
	}
	
	/**
	 * Create shift with given parameters
	 * @param shiftID
	 * @param dayOfWeek
	 * @param startTime
	 * @param endTime
	 * @return the created shift
	 */
	@Transactional
	public Shift createShift(int shiftID, DayOfWeek dayOfWeek, Time startTime, Time endTime){
		if (shiftRepository.findShiftByShiftID(shiftID)!=null) {
			throw new IllegalArgumentException("shift exists");
		}
		if (shiftID == 0) {
			throw new IllegalArgumentException("shift cannot be 0");
		}
		if (dayOfWeek == null) {
			throw new IllegalArgumentException("day of week cannot be null");
		}
		if (startTime == null) {
			throw new IllegalArgumentException("startTime cannot be null");
		}
		if (endTime == null) {
			throw new IllegalArgumentException("endTime cannot be null");
		}
		Shift shift = new Shift();
		shift.setDayOfWeek(dayOfWeek);
		shift.setEndTime(endTime);
		shift.setShiftID(shiftID);
		shift.setStartTime(startTime);
		shiftRepository.save(shift);
		return shift;
	}
	
	/**
	 * Find the corresponding shift and update it with given parameters
	 * @param shiftID
	 * @param newDayOfWeek
	 * @param newStartTime
	 * @param newEndTime
	 * @return the updated shift
	 */
	@Transactional
	public Shift updateShift(int shiftID, DayOfWeek newDayOfWeek, Time newStartTime, Time newEndTime){
		if (shiftRepository.findShiftByShiftID(shiftID)==null) {
			throw new IllegalArgumentException("shift does not exist");
		}
		if (newDayOfWeek == null) {
			throw new IllegalArgumentException("day of week cannot be null");
		}
		if (newStartTime == null) {
			throw new IllegalArgumentException("startTime cannot be null");
		}
		if (newEndTime == null) {
			throw new IllegalArgumentException("endTime cannot be null");
		}
		Shift shift = new Shift();
		shift.setShiftID(shiftID);
		shift.setDayOfWeek(newDayOfWeek);
		shift.setEndTime(newEndTime);
		shift.setStartTime(newStartTime);
		shiftRepository.save(shift);
		return shift;
	}
	
	/**
	 * Assign the corresponding shift to the corresponding librarian
	 * @param id
	 * @param shiftID
	 * @return the librarian
	 */
	public Librarian assignShift(int id, int shiftID){
		if(librarianRepository.findLibrarianById(id)==null){
			throw new IllegalArgumentException("librarian does not exist");
		}
		if(shiftRepository.findShiftByShiftID(shiftID)==null){
			throw new IllegalArgumentException("shift does not exist");
		}
		Librarian l=librarianRepository.findLibrarianById(id);
		l.setId(id);
		// if (librarianRepository.findLibrarianById(id).getShift()==null) {
		// 	Set<Shift> shifts=new HashSet<Shift>();
		// 	l.setShift(shifts);
		// }
		l.getShift().add(shiftRepository.findShiftByShiftID(shiftID));
		librarianRepository.save(l);
		return l;
	}
}
