package ca.mcgill.ecse321.librarysystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;
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
import ca.mcgill.ecse321.librarysystem.models.*;
import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;
import ca.mcgill.ecse321.librarysystem.models.Media.Item;
           /**
     * unit test for CheckOutItemService class
     * @author Howard Yu, David
     */
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
    private CheckOutItemService checkOutItemService;

    private static final int OFFLINE_ID = 727;
    private static final String OFFLINE_ADDRESS = "727 bluezenithhdhr street";
    private static final String OFFLINE_NAME = "WhiteCat";
    private static final AccountCategory OFFLINE_ACCOUNTCATEGORY = AccountCategory.Offline;
    private static final boolean OFFLINE_ISLOCAL = true;
    private static final int OFFLINE_ITEMSCHECKED = 1;

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

                Event event = new Event();
                event.setDate(EVENT_DATE);
                event.setEventEnd(EVENT_ENDTIME);
                event.setEventStart(EVENT_STARTTIME);
                event.setName(EVENT_NAME);

                return event;
            } else {
                return null;
            }
        });

        lenient().when(mediaDao.findMediaByID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(CHECKOUTITEM_ID)) {

                CheckOutItem checkOutItem1 = new CheckOutItem();
				checkOutItem1.setID(CHECKOUTITEM_ID);
                checkOutItem1.setBorrowingPeriod(CHECKOUTITEM_BORROWINGPERIOD);
                checkOutItem1.setType(CHECKOUTITEM_ITEM);
                checkOutItem1.setIsCheckedOut(CHECKOUTITEM_ISCHECKEDOUT);
                checkOutItem1.setIsReserved(CHECKOUTITEM_ISRESERVED);
                checkOutItem1.setStartDate(CHECKOUTITEM_DATE);

                return checkOutItem1;
			} if (invocation.getArgument(0).equals(CHECKOUTITEM_ID2)) {


                CheckOutItem checkOutItem = new CheckOutItem();
				checkOutItem.setID(CHECKOUTITEM_ID2);
                checkOutItem.setBorrowingPeriod(CHECKOUTITEM_BORROWINGPERIOD);
                checkOutItem.setType(CHECKOUTITEM_ITEM);
                checkOutItem.setIsCheckedOut(CHECKOUTITEM_ISCHECKEDOUT);
                checkOutItem.setIsReserved(CHECKOUTITEM_ISRESERVED);
                checkOutItem.setStartDate(CHECKOUTITEM_DATE);
                return checkOutItem;
			} else {
				return null;
			}
		});

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
				checkOutItem.setID(CHECKOUTITEM_ID2);
                checkOutItem.setBorrowingPeriod(CHECKOUTITEM_BORROWINGPERIOD);
                checkOutItem.setType(CHECKOUTITEM_ITEM);
                checkOutItem.setIsCheckedOut(CHECKOUTITEM_ISCHECKEDOUT);
                checkOutItem.setIsReserved(CHECKOUTITEM_ISRESERVED);
                checkOutItem.setStartDate(CHECKOUTITEM_DATE);
                medias.add(checkOutItem);
                offline.setMedias(medias);

                Set<Event> events = new HashSet<Event>();
                
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
        lenient().when(eventDao.save(any(Event.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(mediaDao.save(any(Media.class))).thenAnswer(returnParameterAsAnswer);
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
        assertEquals(error, "Offline Account id already exists.");
    }

    @Test
    public void testCreateOfflineInvalidId(){
        assertEquals(0, offlineService.getAllOfflines().size());

        int id = 0;
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
        assertEquals(error, "Offline Account id cannot be 0.");
    }

    @Test
    public void testCreateOfflineInvalidAddress(){
        assertEquals(0, offlineService.getAllOfflines().size());

        int id = 726;
        String address = null;
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
        assertEquals(error, "Offline Account must have an address.");
    }

    @Test
    public void testCreateOfflineInvalidName(){
        assertEquals(0, offlineService.getAllOfflines().size());

        int id = 726;
        String address = "Badeu C-TYPEDTHRFL street";
        String name = null;
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
        assertEquals(error, "Offline Account must have a name.");
    }
    
    @Test
    public void testCreateOfflineEmptyAccountCategory(){
        assertEquals(0, offlineService.getAllOfflines().size());

        int id = 98;
        String address = "Badeu C-TYPEDTHRFL street";
        String name = "spy";
        AccountCategory accountCategory = null;
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
        assertEquals(error, "account must have a type");
    }

    @Test
    public void testCreateOfflineInvalidAccountCategory(){
        assertEquals(0, offlineService.getAllOfflines().size());

        int id = 726;
        String address = "Badeu C-TYPEDTHRFL street";
        String name = "spy";
        AccountCategory accountCategory = AccountCategory.Online;
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
        assertEquals(error, "Offline account must be of type offline.");
    }
    @Test
    public void testCreateOfflineInvalidIsLocal(){
        assertEquals(0, offlineService.getAllOfflines().size());

        int id = 726;
        String address = "Badeu C-TYPEDTHRFL street";
        String name = "spy";
        AccountCategory accountCategory = AccountCategory.Offline;
        boolean isLocal = false;
        int numChecked = 4;
        Offline offline = null;
        String error = "";
        try {
            offline = offlineService.createOffline(id, address, name, accountCategory, isLocal, numChecked);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(offline);
        assertEquals(error, "Offline Account must be a local");
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
    public void testOfflineLoginWrongID(){
        Offline offline = null;
        String error = null;
        try{
            offline = (Offline) accountService.login(0, "wtv");
        } catch(IllegalArgumentException e) {
			error = e.getMessage();
		} 
        assertNull(offline);
        assertEquals(error, "Invalid Id");
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
    public void testOfflineGetInvalidID(){
        Offline offline = null;
        String error = null;
        try{
            offline = offlineService.getOffline(0);
        } catch(IllegalArgumentException e) {
			error = e.getMessage();
		} 
        assertNull(offline);
        assertEquals(error, "Offline Account id does not exist.");
    }

    @Test
    public void testOfflineUpdate(){
        Offline offline = null;
        try{
            offline = offlineService.updateOffline(OFFLINE_ID, "Ascension to Heaven HDDTHR" , "Merami", 2);
        } catch(IllegalArgumentException e) {
			fail();
		} 
        assertEquals("Ascension to Heaven HDDTHR", offline.getAddress());
        assertEquals("Merami", offline.getName());
        assertEquals(2, offline.getNumChecked());
    }

    @Test
    public void testOfflineUpdateInvalidID(){
        Offline offline = null;
        String error = null;
        try{
            offline = offlineService.updateOffline(0, "Ascension to Heaven HDDTHR" , "Merami", 2);
        } catch(IllegalArgumentException e) {
			error = e.getMessage();
		} 
        assertNull(offline);
        assertEquals(error, "Offline Account id does not exist.");
    }

    @Test
    public void testOfflineUpdateInvalidAddress(){
        Offline offline = null;
        String error = null;
        try{
            offline = offlineService.updateOffline(OFFLINE_ID, null, "Merami", 2);
        } catch(IllegalArgumentException e) {
			error = e.getMessage();
		} 
        assertNull(offline);
        assertEquals(error, "address cannot be empty");
    }

    @Test
    public void testOfflineUpdateInvalidName(){
        Offline offline = null;
        String error = null;
        try{
            offline = offlineService.updateOffline(OFFLINE_ID, "Ascension to Heaven HDDTHR" , null, 2);
        } catch(IllegalArgumentException e) {
			error = e.getMessage();
		} 
        assertNull(offline);
        assertEquals(error, "name cannot be empty");
    }

    @Test
    public void testOfflineUpdateInvalidItemsChecked(){
        Offline offline = null;
        String error = null;
        try{
            offline = offlineService.updateOffline(OFFLINE_ID, "Ascension to Heaven HDDTHR" , "something", -12323);
        } catch(IllegalArgumentException e) {
			error = e.getMessage();
		} 
        assertNull(offline);
        assertEquals(error, "items checked cannot be less than 0");
    }
    @Test
    public void testOfflineCheckout(){
        Offline offline = null;
        CheckOutItem mediaTest = new CheckOutItem();
        try {
            
            offline = offlineService.checkoutAnItem(CHECKOUTITEM_ID, OFFLINE_ID);
            for(Media media : offline.getMedias()){
                if (media.getID()==CHECKOUTITEM_ID){
                    mediaTest = (CheckOutItem) media;
                }
            }
        
        } catch(IllegalArgumentException e) {
			
            fail();
		} 
        assertEquals(2, offline.getMedias().size());
        assertEquals(2, offline.getNumChecked());

    }

    @Test
    public void testOfflineReturn(){
        Offline offline = null;
        try {
            offline = offlineService.returnAnItem(CHECKOUTITEM_ID2, OFFLINE_ID);       
        } catch(IllegalArgumentException e) {
            fail();
		} 
        assertEquals(0, offline.getMedias().size());
        assertEquals(0, offline.getNumChecked());
    }

    @Test
    public void testOfflineReserve(){
        CheckOutItem mediaTest = null;
        try{
            mediaTest = (CheckOutItem) offlineService.reserveAnItem(CHECKOUTITEM_ID);
        } catch(IllegalArgumentException e) {
            fail();
		} 
        assertEquals(true, ((CheckOutItem) mediaTest).getIsReserved());
    }
    @Test
    public void testOfflineCheck(){
        CheckOutItem mediaTest = null;
        try{
            mediaTest = (CheckOutItem) offlineService.checkAnItem(CHECKOUTITEM_ID);
        } catch(IllegalArgumentException e) {
            fail();
		} 
        assertEquals(true, ((CheckOutItem) mediaTest).getIsCheckedOut());
    }

    @Test
    public void testOfflineBookEvent(){
        Offline offline = null;
        try {
            offline = (Offline) accountService.bookEvent(EVENT_NAME, OFFLINE_ID);
        } catch(IllegalArgumentException e) {
            fail();
		} 
        assertEquals(1, offline.getEvents().size());
    }
}
