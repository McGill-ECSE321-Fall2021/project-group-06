package ca.mcgill.ecse321.librarysystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import ca.mcgill.ecse321.librarysystem.models.Shift.DayOfWeek;

/**
 * Author: Niels Mainville
 */
@ExtendWith(MockitoExtension.class)
public class LibrarianServiceTest {
    @Mock
    private AccountRepository accountDao;
    @Mock
    private LibrarianRepository librarianDao;
    @Mock
    private ShiftRepository shiftDao;
    @InjectMocks
    private AccountService accountService;
	@InjectMocks
	private OfflineService offlineService;
    @InjectMocks
    private LibrarianService librarianService;
    @InjectMocks
    private ShiftService shiftService;

    private static final int LIBR_ID=15;
	private static final int LIBR_NON_EXIST_KEY=20;

	private static final int LIBR_ID_2 =5;

	private static final int LIBR_ID_N = 0;

	private static final int OFFLINE_ID = 727;
	private static final String OFFLINE_ADDRESS = "727 bluezenithhdhr street";
    private static final String OFFLINE_NAME = "WhiteCat";
    private static final AccountCategory OFFLINE_ACCOUNTCATEGORY = AccountCategory.Offline;
    private static final boolean OFFLINE_ISLOCAL = true;
    private static final int OFFLINE_ITEMSCHECKED = 1;

    private static final int CHECKOUTITEM_ID2 = 69696;
    private static final boolean CHECKOUTITEM_ISCHECKEDOUT = false;
    private static final boolean CHECKOUTITEM_ISRESERVED = false;
    private static final Item CHECKOUTITEM_ITEM = Item.Book;
    private static final String STRING_DATE = "2021-09-11";
    private static final Date CHECKOUTITEM_DATE = Date.valueOf(STRING_DATE);
    private static final int CHECKOUTITEM_BORROWINGPERIOD = 797;

    private static final int SHIFT_ID=40;
	private static final DayOfWeek WEEKDAY=DayOfWeek.Thursday;
	private static final Time START=Time.valueOf("13:00:00");
	private static final Time END=Time.valueOf("19:00:00");

    @BeforeEach
	public void setMockOutput() {
		lenient().when(librarianDao.findLibrarianById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(LIBR_ID)) {
				Librarian lib=new Librarian();
				lib.setId(LIBR_ID);
				return lib;
			}
			else return null;
		});
		lenient().when(shiftDao.findShiftByShiftID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(SHIFT_ID)) {
				Shift shift=new Shift();
				shift.setShiftID(SHIFT_ID);
				shift.setDayOfWeek(WEEKDAY);
				shift.setStartTime(START);
				shift.setEndTime(END);
				return shift;
			}
			else return null;
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
		lenient().when(librarianDao.save(any(Librarian.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(shiftDao.save(any(Shift.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(accountDao.save(any(Offline.class))).thenAnswer(returnParameterAsAnswer);
	}
	@Test
	public void testCreateLibrarian(){
		Librarian lib = null;
		try{
			lib = librarianService.createLibrarian(LIBR_ID_2);
		} catch (IllegalArgumentException e){
			fail();
		}
		assertNotNull(lib);
		assertEquals(LIBR_ID_2, lib.getId());
	}
	@Test
	public void testCreateLibrarianIDis0(){
		int id = 0;
		Librarian lib = null;
		String error = null;
		try{
			lib = librarianService.createLibrarian(id);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertNull(lib);
		assertEquals("Librarian id cannot be 0.", error);
	}
	public void testCreateLibrarianExistingID(){
		Librarian lib = null;
		String error = null;
		try{
			lib = librarianService.createLibrarian(LIBR_ID);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertNull(lib);
		assertEquals("Librarian id already exist", error);
	}
	@Test
	public void testViewPersonalShift(){
		Librarian lib = librarianDao.findLibrarianById(LIBR_ID);
		assertNotNull(lib);
	}
	@Test
	public void testViewPersonalShiftIDNull(){
		Set<Shift> shifts = null;
		String error = null;
		try{
			shifts = librarianService.viewPersonalShift(LIBR_ID_N);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertNull(shifts);
		assertEquals("Librarian id does not exist", error);
	}
	@Test
	public void testGetAllLibrarians() {
		List<Librarian> allLib=new ArrayList<>();
		allLib = librarianService.getAllLibrarians();
		assertNotNull(allLib);
	}
	// @Test
	// public void testGetAllLibrariansEmpty(){

	// }
	@Test
	public void testGetLibrarian(){
		assertEquals(LIBR_ID, librarianService.getLibrarian(LIBR_ID).getId());
	}
	@Test
	public void testGetNonExistingLibrarian(){
		Librarian lib = null;
		String error = null;
		try{
			lib = librarianService.getLibrarian(LIBR_NON_EXIST_KEY);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertNull(lib);
		assertEquals("Librarian id does not exist", error);
	}

	//Tests for Offline Account
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
}
