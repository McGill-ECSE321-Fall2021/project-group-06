package ca.mcgill.ecse321.librarysystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.librarysystem.dao.*;
import ca.mcgill.ecse321.librarysystem.models.CheckOutItem;
import ca.mcgill.ecse321.librarysystem.models.Event;
import ca.mcgill.ecse321.librarysystem.models.Media;
import ca.mcgill.ecse321.librarysystem.models.Offline;
import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;
import ca.mcgill.ecse321.librarysystem.models.Media.Item;
//author David Hu
@ExtendWith(MockitoExtension.class)
public class OfflineServiceTest {
    @Mock
    private AccountRepository accountDao;
    @Mock
    private EventRepository eventDao;
    @Mock
    private LibrarianRepository librarianDao;
    @Mock 
    private MediaRepository mediaDao;
    @Mock 
    private OpeningHourRepository openingHourDao; 
    @Mock 
    private ShiftRepository shiftDao;

    @InjectMocks
    private OfflineService offlineService;

    @InjectMocks
    private AccountService accountService;

    @InjectMocks
    private MediaService mediaService;

    @InjectMocks
    private CheckOutItemService checkOutItemService;

    private static final int OFFLINE_ID = 727;
    private static final String OFFLINE_ADDRESS = "727 bluezenithhdhr street";
    private static final String OFFLINE_NAME = "WhiteCat";
    private static final AccountCategory OFFLINE_ACCOUNTCATEGORY = AccountCategory.Offline;
    private static final boolean OFFLINE_ISLOCAL = true;
    private static final int OFFLINE_ITEMSCHECKED = 1;

    private static final int CHECKOUTITEM_ID = 6969;
    private static final boolean CHECKOUTITEM_ISCHECKEDOUT = true;
    private static final boolean CHECKOUTITEM_ISRESERVED = false;
    private static final Item CHECKOUTITEM_ITEM = Item.Book;
    private static final String STRING_DATE = "2021-09-11";
    private static final Date CHECKOUTITEM_DATE = Date.valueOf(STRING_DATE);
    private static final int CHECKOUTITEM_BORROWINGPERIOD = 797;

    private static final Date EVENT_DATE = Date.valueOf(STRING_DATE);
    private static final String EVENT_START_STRING = "10:00:00";
    private static final String EVENT_END_STRING = "18:00:00";
    private static final Time EVENT_STARTTIME = Time.valueOf(EVENT_START_STRING);
    private static final Time EVENT_ENDTIME = Time.valueOf(EVENT_END_STRING);
    private static final String EVENT_NAME = "MREKK DOES NOT DESERVE #1";

    
    @BeforeEach
    public void setMockOutput() {

        lenient().when(accountDao.findAccountById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(OFFLINE_ID)) {
				Offline offline = new Offline();
				offline.setId(OFFLINE_ID);
                offline.setAddress(OFFLINE_ADDRESS);
                offline.setName(OFFLINE_NAME);
                offline.setAccountCategory(OFFLINE_ACCOUNTCATEGORY);
                offline.setIsLocal(OFFLINE_ISLOCAL);
                offline.setNumChecked(OFFLINE_ITEMSCHECKED);

                Set<Media> medias = new HashSet<Media>();
                CheckOutItem checkOutItem = new CheckOutItem();
				checkOutItem.setID(CHECKOUTITEM_ID);
                checkOutItem.setBorrowingPeriod(CHECKOUTITEM_BORROWINGPERIOD);
                checkOutItem.setType(CHECKOUTITEM_ITEM);
                checkOutItem.setIsCheckedOut(CHECKOUTITEM_ISCHECKEDOUT);
                checkOutItem.setIsReserved(CHECKOUTITEM_ISRESERVED);
                checkOutItem.setStartDate(CHECKOUTITEM_DATE);
                medias.add(checkOutItem);
                offline.setMedias(medias);

                Set<Event> events = new HashSet<Event>();
                Event event = new Event();
                event.setDate(EVENT_DATE);
                event.setEventEnd(EVENT_ENDTIME);
                event.setEventStart(EVENT_STARTTIME);
                event.setName(EVENT_NAME);
                events.add(event);
                offline.setEvents(events);

				return offline;
			} else {
				return null;
			}
		});

        

        // Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(accountDao.save(any(Offline.class))).thenAnswer(returnParameterAsAnswer);
		//lenient().when(eventDao.save(any(Event.class))).thenAnswer(returnParameterAsAnswer);
		//lenient().when(registrationDao.save(any(Registration.class))).thenAnswer(returnParameterAsAnswer);
    }
    
    @Test
	public void testCreateOffline() {
		assertEquals(0, offlineService.getAllOfflines().size());
        
        int id = 726;
        String address = "Badeu C-TYPEDTHRFL street";
        String name = "RyuK";
        AccountCategory accountCategory = AccountCategory.Offline;
        boolean isLocal = true;
        int numChecked = 4;
        Offline offline = null;
		try {
			offline = offlineService.createOffline(id, address, name, accountCategory, isLocal, numChecked);

		}catch(IllegalArgumentException e) {
			fail();
		}

		assertNotNull(offline);
		assertEquals(id, offline.getId());
		assertEquals(address, offline.getAddress());
        assertEquals(name, offline.getName());
        assertEquals(accountCategory, offline.getAccountCategory());
        assertEquals(isLocal, offline.getIsLocal());
        assertEquals(numChecked, offline.getNumChecked());
        //assertEquals(5, 6);
	}

    @Test
    public void testCreateOfflineTakenId(){
        assertEquals(0, offlineService.getAllOfflines().size());

        int id = OFFLINE_ID;
        String address = "Badeu C-TYPEDTHRFL street";
        String name = "RyuK";
        AccountCategory accountCategory = AccountCategory.Offline;
        boolean isLocal = true;
        int numChecked = 4;
        Offline offline = null;
        String error = "";
        try {
            offline = offlineService.createOffline(id, address, name, accountCategory, isLocal, numChecked);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(offline);
        assertEquals(error, "This Id already exists");
    }

    @Test
    public void testOfflineLogin(){
        Offline offline = null;
        try{
            offline = (Offline) accountService.login(OFFLINE_ID, "wtv");
        } catch(IllegalArgumentException e) {
			fail();
		} 
        assertNotNull(offline);
    }

    @Test
    public void testOfflineGet(){
        Offline offline = null;
        try{
            offline = offlineService.getOffline(OFFLINE_ID);
        } catch(IllegalArgumentException e) {
			fail();
		} 
        assertNotNull(offline);
    }

    @Test
    public void testOfflineUpdate(){
        Offline offline = null;
        try{
            offline = offlineService.updateOffline(OFFLINE_ID, "Ascension to Heaven HDDTHR" , "Merami", 1140);
        } catch(IllegalArgumentException e) {
			fail();
		} 
        assertEquals("Ascension to Heaven HDDTHR", offline.getAddress());
        assertEquals("Merami", offline.getName());
        assertEquals(1140, offline.getNumChecked());
    }

    @Test
    public void testOfflineDelete(){
        boolean wasDeleted;
        try{
            offlineService.deleteOffline(OFFLINE_ID);
            wasDeleted = true;
        } catch(IllegalArgumentException e) {
			wasDeleted = false;
		} 
        assertTrue(wasDeleted);
    }

    @Test
    public void testOfflineCheckout(){
        CheckOutItem coi = new CheckOutItem();
        int id = 72769;
        boolean isCheckedOut = false;
        boolean isReserved = false;
        Item item = Item.Book;
        String STRING_DATE = "2021-08-15";
        Date startDate = Date.valueOf(STRING_DATE);
        int borrowingPeriod = 797;

        coi.setID(id);
        coi.setBorrowingPeriod(borrowingPeriod);
        coi.setIsCheckedOut(isCheckedOut);
        coi.setIsReserved(isReserved);
        coi.setType(item);
        coi.setStartDate(startDate);
        //CheckOutItem media = new CheckOutItem();
        String error = "";
        try {
            mediaDao.save(coi);
            offlineService.checkoutAnItem(id, OFFLINE_ID);
        } catch(IllegalArgumentException e) {
			error = e.getMessage();
		} 
        //assertEquals(5, 5);
        //assertNotNull(accountDao.findAccountById(OFFLINE_ID));
        //assertEquals(1, accountDao.findAccountById(OFFLINE_ID).getMedias().size());
        assertEquals("This media Id is non-existent!", error);

    }

}
