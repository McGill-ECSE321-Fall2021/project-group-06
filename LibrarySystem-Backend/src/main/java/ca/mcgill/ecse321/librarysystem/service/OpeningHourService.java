package ca.mcgill.ecse321.librarysystem.service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.EventRepository;
import ca.mcgill.ecse321.librarysystem.dao.MediaRepository;
import ca.mcgill.ecse321.librarysystem.dao.OpeningHourRepository;
import ca.mcgill.ecse321.librarysystem.dao.ShiftRepository;
import ca.mcgill.ecse321.librarysystem.models.OpeningHour;
import ca.mcgill.ecse321.librarysystem.models.Shift.DayOfWeek;

@Service
public class OpeningHourService {
    // @Autowired
    // AccountRepository accountRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    MediaRepository mediaRepository;
    @Autowired
    OpeningHourRepository openingHourRepository;
    @Autowired
    ShiftRepository shiftRepository;

    /**
     * @author Niels Mainville
     * Sets an Opening Hour
     * @param Id
     * @param date
     * @param startTime
     * @param endTime
     */
    @Transactional
    public OpeningHour createOpeningHour(int id, DayOfWeek DayOfWeek, Time startTime, Time endTime){
        if (id == 0){
            throw new IllegalArgumentException("Opening Hour Id cannot be 0");
        }
        if(openingHourRepository.findOpeningHourById(id) != null){
            throw new IllegalArgumentException("Opening Hour Id already exists");
        }
        if (DayOfWeek == null){
            throw new IllegalArgumentException("Opening Hour day cannot be empty");
        }
        if (startTime == null){
            throw new IllegalArgumentException("Opening Hour starting time cannot be empty");
        }
        if (endTime == null){
            throw new IllegalArgumentException("Opening Hour ending time cannot be empty");
        }
        OpeningHour openingHour = new OpeningHour();
        openingHour.setId(id);
        openingHour.setDayOfWeek(DayOfWeek);
        openingHour.setStartTime(startTime);
        openingHour.setEndTime(endTime);
        openingHourRepository.save(openingHour);
        return openingHour;
    }

    //@Author Howard Yu
    @Transactional
    public List<OpeningHour> getAllOpeningHours(){

        Iterable<OpeningHour> openingHours = openingHourRepository.findAll();
        return toList(openingHours);
    }
    /**
     * @author Niels Mainville
     * @param id
     * @return
     */
    @Transactional
    public OpeningHour getOpeningHour(int id){
        String error="";

        if (openingHourRepository.findOpeningHourById(id) == null) {
            error = error + "OpeningHour cannot be found!";
        }
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        OpeningHour openingHour = (OpeningHour) openingHourRepository.findOpeningHourById(id);
        return openingHour;
    }
    /**
     * @Author Niels Mainville
     * @param id
     * @return
     */
    @Transactional
    public boolean deleteOpeningHour(int id){
        OpeningHour openingHour = openingHourRepository.findOpeningHourById(id);
        if (openingHour == null){
            throw new IllegalArgumentException("Opening Hour Id does not exist");
        }
        openingHourRepository.delete(openingHour);
        return true;
    }
    /**
     * @author Niels Mainville
     * @param id
     * @param newDay
     * @param newStart
     * @param newEnd
     * @return
     */
    @Transactional
    public OpeningHour updateOpeningHours(int id, DayOfWeek newDay, Time newStart, Time newEnd){
        if(openingHourRepository.findOpeningHourById(id) == null){
            throw new IllegalArgumentException("Opening Hour Id does not exist");
        }
        if (newDay == null){
            throw new IllegalArgumentException("Opening Hour day cannot be empty");
        }
        if (newStart == null){
            throw new IllegalArgumentException("Opening Hour starting time cannot be empty");
        }
        if (newEnd == null){
            throw new IllegalArgumentException("Opening Hour ending time cannot be empty");
        }
        OpeningHour openingHour = openingHourRepository.findOpeningHourById(id);
        openingHour.setDayOfWeek(newDay);
        openingHour.setStartTime(newStart);
        openingHour.setEndTime(newEnd);
        openingHourRepository.save(openingHour);
        return openingHour;
    }
    /**
     * @Author Niels Mainville
     * @param <T>
     * @param iterable
     * @return
     */
    // @Transactional
    // public List<OpeningHour> getOpeningHourList(HeadLibrarian headLibrarianName){
    //     if (headLibrarianName == null){
    //         throw new IllegalArgumentException("Head Librarian cannot be null");
    //     }
    //     return openingHourRepository.findByHeadLibrarian(headLibrarianName);
    // }
    //helper method
    //@author Howard Yu
    private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
