package ca.mcgill.ecse321.librarysystem.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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

                //Set<Media> medias = new HashSet<Media>();
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

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(accountDao.save(any(Online.class))).thenAnswer(returnParameterAsAnswer);
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
    public void testCreateOnlineInvalidId(){
        assertEquals(0, onlineService.getAllOnlines().size());

        int id = 0;
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
        assertEquals(error, "Online Account id cannot be 0.");
    }
    @Test
    public void testCreateOnlineInvalidAddress(){
        assertEquals(0, onlineService.getAllOnlines().size());

        int id = 34234;
        String address = null;
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
        assertEquals(error, "Online Account must have an address.");
    }

    @Test
    public void testCreateOnlineInvalidName(){
        assertEquals(0, onlineService.getAllOnlines().size());

        int id = 234234;
        String address = "Badeu C-TYPEDTHRFL street";
        String name = null;
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
        assertEquals(error, "Online Account must have a name.");
    }
    @Test
    public void testCreateOnlineInvalidAccountCategory(){
        assertEquals(0, onlineService.getAllOnlines().size());

        int id = 234234;
        String address = "Badeu C-TYPEDTHRFL street";
        String name = "LazyPurple";
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
        assertEquals(error, "Online account must be of type online.");
    }
    @Test
    public void testCreateOnlineInvalidlocal(){
        assertEquals(0, onlineService.getAllOnlines().size());

        int id = 234234;
        String address = "Badeu C-TYPEDTHRFL street";
        String name = "LazyPurple";
        AccountCategory accountCategory = AccountCategory.Online;
        boolean isLocal = false;
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
        assertEquals(error, "Online Account must be a local");
    }
    @Test
    public void testCreateOnlineInvalidUsername(){
        assertEquals(0, onlineService.getAllOnlines().size());

        int id = 234234;
        String address = "Badeu C-TYPEDTHRFL street";
        String name = "LazyPurple";
        AccountCategory accountCategory = AccountCategory.Online;
        boolean isLocal = true;
        int numChecked = 4;
        Online online = null;
        String username = null;
        String password = "Scottish Cyclops";
        String email = "Crazed@Gunman.tf2";
        String error = "";
        try {
            online = onlineService.createOnline(id, address, name, accountCategory, isLocal, numChecked, username, password, email);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(online);
        assertEquals(error, "Online Account must have a username");
    }
    
    @Test
    public void testCreateOnlineInvalidPassword(){
        assertEquals(0, onlineService.getAllOnlines().size());

        int id = 234234;
        String address = "Badeu C-TYPEDTHRFL street";
        String name = "LazyPurple";
        AccountCategory accountCategory = AccountCategory.Online;
        boolean isLocal = true;
        int numChecked = 4;
        Online online = null;
        String username = "DemoKnight";
        String password = null;
        String email = "Crazed@Gunman.tf2";
        String error = "";
        try {
            online = onlineService.createOnline(id, address, name, accountCategory, isLocal, numChecked, username, password, email);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(online);
        assertEquals(error, "Online Account must have a password");
    }
    @Test
    public void testCreateOnlineInvalidEmail(){
        assertEquals(0, onlineService.getAllOnlines().size());

        int id = 234234;
        String address = "Badeu C-TYPEDTHRFL street";
        String name = "LazyPurple";
        AccountCategory accountCategory = AccountCategory.Online;
        boolean isLocal = true;
        int numChecked = 4;
        Online online = null;
        String username = "bushwacka";
        String password = "the jarate";
        String email = null;
        String error = "";
        try {
            online = onlineService.createOnline(id, address, name, accountCategory, isLocal, numChecked, username, password, email);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(online);
        assertEquals(error, "Online Account must have an email");
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
    public void testOnlineLoginInvalidID(){
        Online online = null;
        String error = null;
        try{
            online = (Online) accountService.login(0, ONLINE_PASSWORD);
        } catch(IllegalArgumentException e) {
			error = e.getMessage();
		} 
        assertNull(online);
        assertEquals(error, "Invalid Id");
    }
    @Test
    public void testOnlineLoginInvalidPassword(){
        Online online = null;
        String error = null;
        try{
            online = (Online) accountService.login(0, "source filmmaker kekw");
        } catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
        assertNull(online);
        assertEquals(error, "Invalid Id");
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
    public void testGetOnlineInvalidId(){
        assertEquals(0, onlineService.getAllOnlines().size());
        Online online = null;
        String error = null;
        try {
         online = onlineService.getOnline(0);
        }catch(IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(online);
        assertEquals(error, "Offline Account id does not exist.");
    }	

    @Test
    public void testOnlineUpdate(){
        Online online = null;
        try{
            online = onlineService.updateOnline(ONLINE_ID, "Ascension to Heaven HDDTHR" , "Merami", 2, "vaxei", "can", "fc flamewall");
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
    public void testOnlineUpdateInvalidID(){
        Online online = null;
        String error = null;
        try{
            online = onlineService.updateOnline(0, "Ascension to Heaven HDDTHR" , "Merami", 2, "vaxei", "can", "fc flamewall");
        } catch(IllegalArgumentException e) {
			error = e.getMessage();
		} 
        assertNull(online);
        assertEquals(error, "Offline Account id does not exist.");
    }
    @Test
    public void testOnlineUpdateInvalidAddress(){
        Online online = null;
        String error = null;
        try{
            online = onlineService.updateOnline(ONLINE_ID, "", "Merami", 2, "vaxei", "can", "fc flamewall");
        } catch(IllegalArgumentException e) {
			error = e.getMessage();
		} 
        assertNull(online);
        assertEquals(error, "address cannot be empty");
    }
    @Test
    public void testOnlineUpdateInvalidName(){
        Online online = null;
        String error = null;
        try{
            online = onlineService.updateOnline(ONLINE_ID, "spah sappin mah sentreh" , "", 2, "vaxei", "can", "fc flamewall");
        } catch(IllegalArgumentException e) {
			error = e.getMessage();
		} 
        assertNull(online);
        assertEquals(error, "name cannot be empty");
    }
    @Test
    public void testOnlineUpdateInvalidItemsChecked(){
        Online online = null;
        String error = null;
        try{
            online = onlineService.updateOnline(ONLINE_ID, "spah sappin mah sentreh" , "I am the spy", -23421, "vaxei", "can", "fc flamewall");
        } catch(IllegalArgumentException e) {
			error = e.getMessage();
		} 
        assertNull(online);
        assertEquals(error, "items checked cannot be less than 0");
    }
    @Test
    public void testOnlineUpdateInvalidUsername(){
        Online online = null;
        String error = null;
        try{
            online = onlineService.updateOnline(ONLINE_ID, "spah sappin mah sentreh" , "I am the spy", 12, "", "can", "fc flamewall");
        } catch(IllegalArgumentException e) {
			error = e.getMessage();
		} 
        assertNull(online);
        assertEquals(error, "username is not valid");
    }
    @Test
    public void testOnlineUpdateInvalidPassword(){
        Online online = null;
        String error = null;
        try{
            online = onlineService.updateOnline(ONLINE_ID, "spah sappin mah sentreh" , "I am the spy", 12, "Vsauced", "", "fc flamewall");
        } catch(IllegalArgumentException e) {
			error = e.getMessage();
		} 
        assertNull(online);
        assertEquals(error, "must have a password");
    }
    @Test
    public void testOnlineUpdateInvalidEmail(){
        Online online = null;
        String error = null;
        try{
            online = onlineService.updateOnline(ONLINE_ID, "spah sappin mah sentreh" , "I am the spy", 12, "Vsauced", "Sandvich", "");
        } catch(IllegalArgumentException e) {
			error = e.getMessage();
		} 
        assertNull(online);
        assertEquals(error, "email must not be empty");
    }
    @Test
    public void testOnlineCheckout(){
        Online online = null;

        CheckOutItem mediaTest = new CheckOutItem();
        try {
            online = onlineService.checkoutAnItem(CHECKOUTITEM_ID, ONLINE_ID);
            for(Media media : online.getMedias()){
                if (media.getID()==CHECKOUTITEM_ID){
                    mediaTest = (CheckOutItem) media;
                }
            }
        } catch(IllegalArgumentException e) {
            fail();
		} 
        assertEquals(2, online.getMedias().size());
        assertEquals(2, online.getNumChecked());
    }

    @Test
    public void testOnlineReturn(){
        Online online = null;
        String error = "";
        try {
            online = onlineService.returnAnItem(CHECKOUTITEM_ID2, ONLINE_ID);
        } catch(IllegalArgumentException e) {
            fail();
		} 
        assertEquals(0, online.getMedias().size());
        assertEquals(0, online.getNumChecked());
    }

    @Test
    public void testOnlineReserve(){
        CheckOutItem mediaTest = null;
        String error = "";
        try{
            mediaTest = (CheckOutItem) onlineService.reserveAnItem(CHECKOUTITEM_ID);
        } catch(IllegalArgumentException e) {
            fail();
		} 
        assertEquals(true, ((CheckOutItem) mediaTest).getIsReserved());
    }

    @Test
    public void testOnlineCheck(){
        CheckOutItem mediaTest = null;
        String error = "";
        try{
            mediaTest = (CheckOutItem) onlineService.checkAnItem(CHECKOUTITEM_ID);
        } catch(IllegalArgumentException e) {
            fail();
		} 
        assertEquals(true, ((CheckOutItem) mediaTest).getIsCheckedOut());
    }

    @Test
    public void testOnlineBookEvent(){
        Online online = null;
        try {
            online = (Online) accountService.bookEvent(EVENT_NAME, ONLINE_ID);
        } catch(IllegalArgumentException e) {
            fail();
		} 
        assertEquals(1, online.getEvents().size());
    }
}
