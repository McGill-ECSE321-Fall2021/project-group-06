package ca.mcgill.ecse321.librarysystem.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.EventRepository;
import ca.mcgill.ecse321.librarysystem.dao.AccountRepository;
import ca.mcgill.ecse321.librarysystem.dao.MediaRepository;
import ca.mcgill.ecse321.librarysystem.dao.OpeningHourRepository;
import ca.mcgill.ecse321.librarysystem.dao.ShiftRepository;

import ca.mcgill.ecse321.librarysystem.models.Account;
import ca.mcgill.ecse321.librarysystem.models.CheckOutItem;
import ca.mcgill.ecse321.librarysystem.models.Event;
import ca.mcgill.ecse321.librarysystem.models.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem.models.Librarian;
import ca.mcgill.ecse321.librarysystem.models.LibrarySystem;
import ca.mcgill.ecse321.librarysystem.models.Media;
import ca.mcgill.ecse321.librarysystem.models.NonCheckOutItem;
import ca.mcgill.ecse321.librarysystem.models.Offline;
import ca.mcgill.ecse321.librarysystem.models.Online;
import ca.mcgill.ecse321.librarysystem.models.OpeningHour;
import ca.mcgill.ecse321.librarysystem.models.Shift;
import ca.mcgill.ecse321.librarysystem.models.Media.Item;

@Service
public class LibrarySystemService {

	@Autowired
	EventRepository eventRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	MediaRepository mediaRepository;
    @Autowired
    OpeningHourRepository openingHourRepository;
    @Autowired
    ShiftRepository shiftRepository;

    @Transactional
	public CheckOutItem createCheckOutItem(Account account, int aBorrowingPeriod, int aMediaID, boolean aIsCheckedOut, boolean aIsReserved, Item aMediaType) {
		CheckOutItem checkoutItem = new CheckOutItem();
		checkoutItem.setAccount(account);
        checkoutItem.setBorrowingPeriod(aBorrowingPeriod);
        checkoutItem.setID(aMediaID);
        checkoutItem.setIsCheckedOut(aIsCheckedOut);
        checkoutItem.setIsReserved(aIsReserved);
        checkoutItem.setType(aMediaType);
		mediaRepository.save(checkoutItem);
		return checkoutItem;
	}

	@Transactional
	public CheckOutItem getCheckoutItem(int ID) {
		CheckOutItem checkoutItem = (CheckOutItem) mediaRepository.findMediaByID(ID);
		return checkoutItem;
	}

	@Transactional
	public List<CheckOutItem> getAllCheckoutItem() {
        List<Media> something = toList(mediaRepository.findAll());
        List<CheckOutItem> checkoutItems = new ArrayList<CheckOutItem>();
        for(Media media: something){
            if (media instanceof CheckOutItem){
                checkoutItems.add((CheckOutItem)media);
            }
        }
		return checkoutItems;
	}
	public NonCheckOutItem createNonCheckOutItem(Account account, int aBorrowingPeriod, int aMediaID, boolean aIsCheckedOut, boolean aIsReserved, Item aMediaType) {
		NonCheckOutItem noncheckoutItem = new NonCheckOutItem();
		noncheckoutItem.setAccount(account);

        noncheckoutItem.setID(aMediaID);
        noncheckoutItem.setType(aMediaType);
		mediaRepository.save(noncheckoutItem);
		return noncheckoutItem;
	}

	@Transactional
	public NonCheckOutItem getNonCheckoutItem(int ID) {
		NonCheckOutItem noncheckoutItem = (NonCheckOutItem) mediaRepository.findMediaByID(ID);
		return noncheckoutItem;
	}

	@Transactional
	public List<NonCheckOutItem> getAllNonCheckoutItem() {
        List<Media> something = toList(mediaRepository.findAll());
        List<NonCheckOutItem> noncheckoutItems = new ArrayList<NonCheckOutItem>();
        for(Media media: something){
            if (media instanceof CheckOutItem){
                noncheckoutItems.add((NonCheckOutItem)media);
            }
        }
		return noncheckoutItems;
	}
	@Transactional
	public Event createEvent(String name,Date date, Time startTime, Time endTime, Account account) {
		Event event = new Event();
		event.setName(name);
        event.setDate(date);
        event.setEventStart(startTime);
        event.setEventEnd(endTime);
        event.setAccount(account);
        //we need a setId?????
        event.setName(account.getName());
		eventRepository.save(event);
		return event;
	}

	@Transactional
	public Event getEvent(String name) {
		Event event = eventRepository.findEventByName(name);
		return event;
	}

	@Transactional
	public List<Event> getAllEvents() {
		return toList(eventRepository.findAll());
	}

	@Transactional
	public OpeningHour createOpeningHour(String name, Date date, Time startTime, Time endTime, HeadLibrarian headLibrarian) {
		OpeningHour openingHour = new OpeningHour();
        openingHour.setDate(date);
        openingHour.setStartTime(startTime);
        openingHour.setEndTime(endTime);
        openingHour.setHeadLibrarian(headLibrarian);
        //following set id in tutorial
        openingHour.setId(headLibrarian.getName().hashCode());
		openingHourRepository.save(openingHour);
		return openingHour;
	}

	@Transactional
	public OpeningHour getOpeningHour(int id) {
		OpeningHour openingHour = openingHourRepository.findOpeningHourById(id);
		return openingHour;
	}

	@Transactional
	public List<OpeningHour> getAllOpeningHours() {
		return toList(openingHourRepository.findAll());
	}

	@Transactional
	public Shift createShift(Date date, Time startTime, Time endTime, HeadLibrarian headLibrarian, int shiftID, HashSet<Librarian> librarian) {
		Shift shift = new Shift();
        shift.setDate(date);
        shift.setStartTime(startTime);
        shift.setEndTime(endTime);
        shift.setHeadLibrarian(headLibrarian);
        shift.setShiftID(headLibrarian.getName().hashCode());
        shift.setLibrarian(librarian);
		shiftRepository.save(shift);
		return shift;
	}

	@Transactional
	public Shift getShift(int id) {
		Shift shift = shiftRepository.findShiftByShiftID(id);
		return shift;
	}

	@Transactional
	public List<Shift> getAllShift() {
		return toList(shiftRepository.findAll());
	}

	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}