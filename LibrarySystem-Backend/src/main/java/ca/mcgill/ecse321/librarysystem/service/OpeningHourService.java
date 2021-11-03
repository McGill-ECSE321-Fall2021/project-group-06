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
import ca.mcgill.ecse321.librarysystem.models.OpeningHour;

@Service
public class OpeningHourService {
    @Autowired
    AccountRepository accountRepository;
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
    public OpeningHour createOpeningHour(int id, Date date, Time startTime, Time endTime){
        if (id == 0){
            throw new IllegalArgumentException("Opening Hour Id cannot be 0");
        }
        if (date == null){
            throw new IllegalArgumentException("Opening Hour date cannot be empty");
        }
        if (startTime == null){
            throw new IllegalArgumentException("Opening Hour stating time cannot be empty");
        }
        if (endTime == null){
            throw new IllegalArgumentException("Opening Hour ending time cannot be empty");
        }
        OpeningHour openingHour = new OpeningHour();
        openingHour.setId(id);
        openingHour.setDate(date);
        openingHour.setStartTime(startTime);
        openingHour.setEndTime(endTime);
        openingHourRepository.save(openingHour);
        return openingHour;
    }

    @Transactional
    public OpeningHour getOpeningHour(int id){
        if (id == 0){
            throw new IllegalArgumentException("Opening Hour Id cannot be 0");
        }
        return openingHourRepository.findOpeningHourById(id);
    }

    @Transactional
    public List<OpeningHour> getOpeningHourList(HeadLibrarian headLibrarianName){
        if (headLibrarianName == null){
            throw new IllegalArgumentException("Head Librarian cannot be null");
        }
        return openingHourRepository.findByHeadLibrarian(headLibrarianName);
    }

}
