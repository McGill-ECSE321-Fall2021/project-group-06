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
import ca.mcgill.ecse321.librarysystem.models.Online;
import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;
import ca.mcgill.ecse321.librarysystem.models.Media.Item;
//author David Hu
@ExtendWith(MockitoExtension.class)
public class OnlineServiceTest {
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
    private OnlineService onlineService;

    @InjectMocks
    private AccountService accountService;

    @InjectMocks
    private MediaService mediaService;

    @InjectMocks
    private CheckOutItemService checkOutItemService;

    private static final int ONLINE_ID = 727;
    private static final String ONLINE_ADDRESS = "1273 Rockefeller Street";
    private static final String ONLINE_NAME = "Haywood Jablommy";
    private static final AccountCategory ONLINE_ACCOUNTCATEGORY = AccountCategory.Online;
    private static final boolean ONLINE_ISLOCAL = true;
    private static final int ONLINE_ITEMSCHECKED = 1;
    private static final String ONLINE_EMAIL= "scout@b.gone";
    private static final String ONLINE_PASSWORD= "Force a Nature";
    private static final String ONLINE_USERNAME= "scottish resistance";

    private static final int CHECKOUTITEM_ID = 6969;
    private static final int CHECKOUTITEM_ID2 = 69696;
    private static final boolean CHECKOUTITEM_ISCHECKEDOUT = false;
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

        lenient().when(eventDao.findEventByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(EVENT_NAME)) {

                //Set<Media> medias = new HashSet<Media>();
                Event event = new Event();
                event.setDate(EVENT_DATE);
                event.setEventEnd(EVENT_ENDTIME);
                event.setEventStart(EVENT_STARTTIME);
                event.setName(EVENT_NAME);
                
                //medias.add(checkOutItem);
                //offline.setMedias(medias);

                return event;
            } else {
                return null;
            }
        });

        lenient().when(mediaDao.findMediaByID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(CHECKOUTITEM_ID)) {

                //Set<Media> medias = new HashSet<Media>();
                CheckOutItem checkOutItem1 = new CheckOutItem();
				checkOutItem1.setID(CHECKOUTITEM_ID);
                checkOutItem1.setBorrowingPeriod(CHECKOUTITEM_BORROWINGPERIOD);
                checkOutItem1.setType(CHECKOUTITEM_ITEM);
                checkOutItem1.setIsCheckedOut(CHECKOUTITEM_ISCHECKEDOUT);
                checkOutItem1.setIsReserved(CHECKOUTITEM_ISRESERVED);
                checkOutItem1.setStartDate(CHECKOUTITEM_DATE);
                //medias.add(checkOutItem);
                //offline.setMedias(medias);

                return checkOutItem1;
			} if (invocation.getArgument(0).equals(CHECKOUTITEM_ID2)) {

                //Set<Media> medias = new HashSet<Media>();
                CheckOutItem checkOutItem = new CheckOutItem();
				checkOutItem.setID(CHECKOUTITEM_ID2);
                checkOutItem.setBorrowingPeriod(CHECKOUTITEM_BORROWINGPERIOD);
                checkOutItem.setType(CHECKOUTITEM_ITEM);
                checkOutItem.setIsCheckedOut(CHECKOUTITEM_ISCHECKEDOUT);
                checkOutItem.setIsReserved(CHECKOUTITEM_ISRESERVED);
                checkOutItem.setStartDate(CHECKOUTITEM_DATE);
                //medias.add(checkOutItem);
                //offline.setMedias(medias);

                return checkOutItem;
			} else {
				return null;
			}
		});

        
        lenient().when(accountDao.findAccountById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ONLINE_ID)) {
				Online online = new Online();
				online.setId(ONLINE_ID);
                online.setAddress(ONLINE_ADDRESS);
                online.setName(ONLINE_NAME);
                online.setAccountCategory(ONLINE_ACCOUNTCATEGORY);
                online.setIsLocal(ONLINE_ISLOCAL);
                online.setNumChecked(ONLINE_ITEMSCHECKED);
                online.setEmail(ONLINE_EMAIL);
                online.setPassword(ONLINE_PASSWORD);
                online.setUsername(ONLINE_USERNAME);

                Set<Media> medias = new HashSet<Media>();
                CheckOutItem checkOutItem = new CheckOutItem();
				checkOutItem.setID(CHECKOUTITEM_ID2);
                checkOutItem.setBorrowingPeriod(CHECKOUTITEM_BORROWINGPERIOD);
                checkOutItem.setType(CHECKOUTITEM_ITEM);
                checkOutItem.setIsCheckedOut(CHECKOUTITEM_ISCHECKEDOUT);
                checkOutItem.setIsReserved(CHECKOUTITEM_ISRESERVED);
                checkOutItem.setStartDate(CHECKOUTITEM_DATE);
                medias.add(checkOutItem);
                online.setMedias(medias);

                Set<Event> events = new HashSet<Event>();
                online.setEvents(events);
                
				return online;
			} else {
				return null;
			}
		});

        // Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(accountDao.save(any(Online.class))).thenAnswer(returnParameterAsAnswer);
		//lenient().when(eventDao.save(any(Event.class))).thenAnswer(returnParameterAsAnswer);
		//lenient().when(registrationDao.save(any(Registration.class))).thenAnswer(returnParameterAsAnswer);
    }

    @Test
	public void testCreateOnline() {
		assertEquals(0, onlineService.getAllOnlines().size());
        
        int id = 34234;
        String address = "Badeu C-TYPEDTHRFL street";
        String name = "RyuK";
        AccountCategory accountCategory = AccountCategory.Online;
        boolean isLocal = true;
        int numChecked = 4;
        Online online = null;
        String username = "Atomizer";
        String password = "Scottish Cyclops";
        String email = "Crazed@Gunman.tf2";
		try {
			online = onlineService.createOnline(id, address, name, accountCategory, isLocal, numChecked, username, password, email);

		}catch(IllegalArgumentException e) {
			fail();
		}

		assertNotNull(online);
		assertEquals(id, online.getId());
		assertEquals(address, online.getAddress());
        assertEquals(name, online.getName());
        assertEquals(accountCategory, online.getAccountCategory());
        assertEquals(isLocal, online.getIsLocal());
        assertEquals(numChecked, online.getNumChecked());
        assertEquals(username, online.getUsername());
        assertEquals(password, online.getPassword());
        assertEquals(email, online.getEmail());
        //assertEquals(5, 6);
	}

    @Test
    public void testCreateOnlineTakenId(){
        assertEquals(0, onlineService.getAllOnlines().size());

        int id = ONLINE_ID;
        String address = "Badeu C-TYPEDTHRFL street";
        String name = "RyuK";
        AccountCategory accountCategory = AccountCategory.Offline;
        boolean isLocal = true;
        int numChecked = 4;
        Online online = null;
        String username = "Atomizer";
        String password = "Scottish Cyclops";
        String email = "Crazed@Gunman.tf2";
        String error = "";
        try {
            online = onlineService.createOnline(id, address, name, accountCategory, isLocal, numChecked, username, password, email);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(online);
        assertEquals(error, "Online Account id already exists.");
    }

    @Test
    public void testOnlineLogin(){
        Online online = null;
        try{
            online = (Online) accountService.login(ONLINE_ID, ONLINE_PASSWORD);
        } catch(IllegalArgumentException e) {
			fail();
		} 
        assertNotNull(online);
    }

    @Test
    public void testGetOnline(){
        assertEquals(0, onlineService.getAllOnlines().size());
        Online online = null;
        try {
         online = onlineService.getOnline(ONLINE_ID);
        }catch(IllegalArgumentException e) {
            fail();
        }
        assertNotNull(online);
        assertEquals(online.getUsername(), ONLINE_USERNAME);
        assertEquals(online.getPassword(), ONLINE_PASSWORD);
    }	

    @Test
    public void testOnlineDelete(){
        boolean wasDeleted;
        try{
            onlineService.deleteOnline(ONLINE_ID);
            wasDeleted = true;
        } catch(IllegalArgumentException e) {
			wasDeleted = false;
		} 
        assertTrue(wasDeleted);
    }

    @Test
    public void testOfflineUpdate(){
        Online online = null;
        try{
            online = onlineService.updateOnline(ONLINE_ID, ONLINE_ID, "Ascension to Heaven HDDTHR" , "Merami", 2, "vaxei", "can", "fc flamewall");
        } catch(IllegalArgumentException e) {
			fail();
		} 
        assertEquals("Ascension to Heaven HDDTHR", online.getAddress());
        assertEquals("Merami", online.getName());
        assertEquals(2, online.getNumChecked());
        assertEquals("vaxei", online.getUsername());
        assertEquals("can", online.getPassword());
        assertEquals("fc flamewall", online.getEmail());
    }

    @Test
    public void testOnlineCheckout(){
        Online online = null;

        CheckOutItem mediaTest = new CheckOutItem();
        try {
            //media = checkOutItemService.createCheckOutItem(item, id, isCheckedOut, isReserved, borrowingPeriod, startDate);
            online = onlineService.checkoutAnItem(CHECKOUTITEM_ID, ONLINE_ID);
            for(Media media : online.getMedias()){
                if (media.getID()==CHECKOUTITEM_ID){
                    mediaTest = (CheckOutItem) media;
                }
            }
            //offline = offlineService.getOffline(OFFLINE_ID);
        } catch(IllegalArgumentException e) {
			//error = e.getMessage();
            fail();
		} 
        //assertEquals(5, 5);
        //assertNotNull(bruh);
        assertEquals(2, online.getMedias().size());
        assertEquals(2, online.getNumChecked());
        //assertEquals(true, mediaTest.getIsCheckedOut());
        //assertEquals("This media Id is non-existent!", error);

        

    }

    @Test
    public void testOfflineReturn(){
        Online online = null;

        //CheckOutItem mediaTest = new CheckOutItem();
        String error = "";
        try {
            //media = checkOutItemService.createCheckOutItem(item, id, isCheckedOut, isReserved, borrowingPeriod, startDate);
            //offline = offlineService.checkoutAnItem(CHECKOUTITEM_ID2, OFFLINE_ID);

            online = onlineService.returnAnItem(CHECKOUTITEM_ID2, ONLINE_ID);
            
            //offline = offlineService.getOffline(OFFLINE_ID);
        } catch(IllegalArgumentException e) {
			//error = e.getMessage();
            fail();
		} 
        //assertEquals(5, 5);
        //assertNotNull(bruh);
        //assertEquals("This account does not exist!", error);
        assertEquals(0, online.getMedias().size());
        assertEquals(0, online.getNumChecked());
    }

    @Test
    public void testOnlineReseerve(){
        CheckOutItem mediaTest = null;
        String error = "";
        try{
            mediaTest = (CheckOutItem) onlineService.reserveAnItem(CHECKOUTITEM_ID);
        } catch(IllegalArgumentException e) {
			//error = e.getMessage();
            fail();
		} 
        assertEquals(true, ((CheckOutItem) mediaTest).getIsReserved());
        //assertEquals("This medi]", error);
    }

    @Test
    public void testOnlineCheck(){
        CheckOutItem mediaTest = null;
        String error = "";
        try{
            mediaTest = (CheckOutItem) onlineService.checkAnItem(CHECKOUTITEM_ID);
        } catch(IllegalArgumentException e) {
			//error = e.getMessage();
            fail();
		} 
        assertEquals(true, ((CheckOutItem) mediaTest).getIsCheckedOut());
        //assertEquals("This medi]", error);
    }

    @Test
    public void testOnlineBookEvent(){
        Online online = null;

        //CheckOutItem mediaTest = new CheckOutItem();
        try {
            //media = checkOutItemService.createCheckOutItem(item, id, isCheckedOut, isReserved, borrowingPeriod, startDate);
            online = (Online) accountService.bookEvent(EVENT_NAME, ONLINE_ID);
            //offline = offlineService.getOffline(OFFLINE_ID);
        } catch(IllegalArgumentException e) {
			//error = e.getMessage();
            fail();
		} 
        //assertEquals(5, 5);
        //assertNotNull(bruh);
        assertEquals(1, online.getEvents().size());
        //assertEquals(2, offline.getNumChecked());
        //assertEquals(true, mediaTest.getIsCheckedOut());
        //assertEquals("This media Id is non-existent!", error);
    }
}
