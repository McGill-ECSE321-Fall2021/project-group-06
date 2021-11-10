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
import ca.mcgill.ecse321.librarysystem.models.Media.Item;
import ca.mcgill.ecse321.librarysystem.models.Offline;
import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;
import ca.mcgill.ecse321.librarysystem.models.Media.Item;
//author David Hu
@ExtendWith(MockitoExtension.class)
public class CheckOutItemServiceTest {
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

    private static final int MEDIA_ID = 123;
    private static final Date START_DATE = Date.valueOf("2021-09-11");
    private static final Item MEDIA_TYPE = Item.Book;
    private static final int BORROWING_PERIOD = 123;
    private static final boolean IS_CHECKEDOUT = false;
    private static final boolean IS_RESERVED = false;


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
            lenient().when(mediaDao.findMediaByID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(MEDIA_ID)) {
                CheckOutItem media = new CheckOutItem();
                media.setStartDate(START_DATE);
                media.setType(MEDIA_TYPE);
                media.setBorrowingPeriod(BORROWING_PERIOD);
                media.setID(MEDIA_ID);
                media.setIsCheckedOut(IS_CHECKEDOUT);
                media.setIsReserved(IS_RESERVED);
                return media;
            } else {
                return null;
            }
        });

        // Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(mediaDao.save(any(CheckOutItem.class))).thenAnswer(returnParameterAsAnswer);
    }
    
    @Test
	public void testCreateCheckOutItem() {
		assertEquals(0, mediaService.getAllMedias().size());
        
        CheckOutItem checkOutItem = null;
        Item mediaType = Item.Book;
        int mediaID = 123123123;
        boolean isCheckedOut = false;
        boolean isReserved = false;
        int borrowingPeriod = 0;
        Date startDate = Date.valueOf("2021-09-11");
		try {
            checkOutItem = checkOutItemService.createCheckOutItem(mediaType, mediaID, isCheckedOut, isReserved, borrowingPeriod, startDate);

		}catch(IllegalArgumentException e) {
			fail();
		}

		assertNotNull(checkOutItem);
		assertEquals(mediaID, checkOutItem.getID());
		assertEquals(mediaType,checkOutItem.getType());
        assertEquals(isCheckedOut,checkOutItem.getIsCheckedOut());
        assertEquals(isReserved, checkOutItem.getIsReserved());
        assertEquals(borrowingPeriod, checkOutItem.getBorrowingPeriod());
        assertEquals(startDate, checkOutItem.getStartDate());
	}

    @Test
    public void testUpdateCheckOutItem(){
        assertEquals(0, mediaService.getAllMedias().size());
        
        CheckOutItem checkOutItem = null;
        Item newMediaType = Item.Movie;
        int mediaID = MEDIA_ID;
        boolean newIsCheckedOut = false;
        boolean newIsReserved = false;
        int newBorrowingPeriod = 69;
        Date newStartDate = Date.valueOf("2021-09-11");
		try {
            checkOutItem = checkOutItemService.updateCheckOutItem(mediaID, newMediaType, newIsCheckedOut, newIsReserved, newBorrowingPeriod, newStartDate);

		}catch(IllegalArgumentException e) {
			fail();
		}

		assertNotNull(checkOutItem);
		assertEquals(mediaID, checkOutItem.getID());
		assertEquals(newMediaType,checkOutItem.getType());
        assertEquals(newIsCheckedOut,checkOutItem.getIsCheckedOut());
        assertEquals(newIsReserved, checkOutItem.getIsReserved());
        assertEquals(newBorrowingPeriod, checkOutItem.getBorrowingPeriod());
        assertEquals(newStartDate, checkOutItem.getStartDate());
        //assertEquals(5, 6);
    }
}
