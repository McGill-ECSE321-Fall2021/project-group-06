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
import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;
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
	public Online createOnline(AccountCategory aAccountCategory, String address, String aEmail, HashSet<Event> events, int id, boolean aIsLocal, HashSet<Media> medias, String aName, int aNumChecked, String aPassword, String aUsername) {
		Online online = new Online();
		online.setAccountCategory(aAccountCategory);
		online.setAddress(address);
		online.setEmail(aEmail);
		online.setEvents(events);
		online.setId(id);
		online.setIsLocal(aIsLocal);
		online.setMedias(medias);
		online.setName(aName);
		online.setNumChecked(aNumChecked);
		online.setPassword(aPassword);
		online.setUsername(aUsername);
		accountRepository.save(online);
		return online;
	}

	@Transactional
	public Online getOnline(int id) {
		Online online = (Online) accountRepository.findAccountById(id);
		return online;
	}

	@Transactional
	public List<Online> getAllOnline() {
        List<Account> something = toList(accountRepository.findAll());
        List<Online> onlines = new ArrayList<Online>();
        for(Account online: something){
            if (online instanceof Online){
                onlines.add((Online) online);
            }
        }
		return onlines;
	}

	@Transactional
	public Offline createOffline(AccountCategory aAccountCategory, String address, HashSet<Event> events, int id, boolean aIsLocal, HashSet<Media> medias, String aName, int aNumChecked) {
		Offline offline = new Offline();
		offline.setAccountCategory(aAccountCategory);
		offline.setAddress(address);
		offline.setEvents(events);
		offline.setId(id);
		offline.setIsLocal(aIsLocal);
		offline.setMedias(medias);
		offline.setName(aName);
		offline.setNumChecked(aNumChecked);
		accountRepository.save(offline);
		return offline;
	}

	@Transactional
	public Offline getOffline(int id) {
		Offline offline = (Offline) accountRepository.findAccountById(id);
		return offline;
	}

	@Transactional
	public List<Offline> getAllOffline() {
        List<Account> something = toList(accountRepository.findAll());
        List<Offline> offlines = new ArrayList<Offline>();
        for(Account offline: something){
            if (offline instanceof Offline){
                offlines.add((Offline) offline);
            }
        }
		return offlines;
	}

	@Transactional
	public Librarian createLibrarian(AccountCategory aAccountCategory, String address, HashSet<Event> events, int id, boolean aIsLocal, HashSet<Media> medias, String aName, int aNumChecked) {
		Librarian librarian = new Librarian();
		librarian.setAccountCategory(aAccountCategory);
		librarian.setAddress(address);
		librarian.setEvents(events);
		librarian.setId(id);
		librarian.setIsLocal(aIsLocal);
		librarian.setMedias(medias);
		librarian.setName(aName);
		librarian.setNumChecked(aNumChecked);
		accountRepository.save(librarian);
		return librarian;
	}

	@Transactional
	public Librarian getLibrarian(int id) {
		Librarian librarian = (Librarian) accountRepository.findAccountById(id);
		return librarian;
	}

	@Transactional
	public List<Librarian> getAllLibrarian() {
        List<Account> something = toList(accountRepository.findAll());
        List<Librarian> librarians = new ArrayList<Librarian>();
        for(Account librarian: something){
            if (librarian instanceof Librarian){
                librarians.add((Librarian) librarian);
            }
        }
		return librarians;
	}

	@Transactional
	public HeadLibrarian createHeadLibrarian(AccountCategory aAccountCategory, String address, HashSet<Event> events, int id, boolean aIsLocal, HashSet<Media> medias, String aName, int aNumChecked) {
		HeadLibrarian headLibrarian = new HeadLibrarian();
		headLibrarian.setAccountCategory(aAccountCategory);
		headLibrarian.setAddress(address);
		headLibrarian.setEvents(events);
		headLibrarian.setId(id);
		headLibrarian.setIsLocal(aIsLocal);
		headLibrarian.setMedias(medias);
		headLibrarian.setName(aName);
		headLibrarian.setNumChecked(aNumChecked);
		accountRepository.save(headLibrarian);
		return headLibrarian;
	}

	@Transactional
	public HeadLibrarian getHeadLibrarian(int id) {
		HeadLibrarian headLibrarian = (HeadLibrarian) accountRepository.findAccountById(id);
		return headLibrarian;
	}

	@Transactional
	public List<HeadLibrarian> getAllHeadLibrarian() {
        List<Account> something = toList(accountRepository.findAll());
        List<HeadLibrarian> headLibrarians = new ArrayList<HeadLibrarian>();
        for(Account headLibrarian: something){
            if (headLibrarian instanceof HeadLibrarian){
                headLibrarians.add((HeadLibrarian) headLibrarian);
            }
        }
		return headLibrarians;
	}

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

	@Transactional
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
            if (media instanceof NonCheckOutItem){
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
	public OpeningHour createOpeningHour(int id, Date date, Time startTime, Time endTime, HeadLibrarian headLibrarian) {
		OpeningHour openingHour = new OpeningHour();
        openingHour.setDate(date);
        openingHour.setStartTime(startTime);
        openingHour.setEndTime(endTime);
        openingHour.setHeadLibrarian(headLibrarian);
        openingHour.setId(id);
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