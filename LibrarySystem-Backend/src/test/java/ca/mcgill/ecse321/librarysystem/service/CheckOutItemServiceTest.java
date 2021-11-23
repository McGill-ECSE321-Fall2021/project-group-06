package ca.mcgill.ecse321.librarysystem.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import java.sql.Date;

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
import ca.mcgill.ecse321.librarysystem.models.Media.Item;

           /**
     * unit test for CheckOutItemService class
     * @author Howard Yu
     */

@ExtendWith(MockitoExtension.class)
public class CheckOutItemServiceTest {
    @Mock 
    private MediaRepository mediaDao;
    
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
    private static final String MEDIA_NAME = "Gain";

    @BeforeEach
    public void setMockOutput() {
            lenient().when(mediaDao.findMediaByID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(MEDIA_ID)) {
                CheckOutItem media = new CheckOutItem();
                media.setStartDate(START_DATE);
                media.setType(MEDIA_TYPE);
                media.setName(MEDIA_NAME);
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
            checkOutItem = checkOutItemService.createCheckOutItem(mediaType, mediaID, "example", isCheckedOut, isReserved, borrowingPeriod, startDate);

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
	public void testCreateCheckOutItemEmptyID() {
		assertEquals(0, mediaService.getAllMedias().size());
        
        CheckOutItem checkOutItem = null;
        Item mediaType = Item.Book;
        int mediaID = 0;
        boolean isCheckedOut = false;
        boolean isReserved = false;
        int borrowingPeriod = 0;
        String error = null;
        Date startDate = Date.valueOf("2021-09-11");
		try {
            checkOutItem = checkOutItemService.createCheckOutItem(mediaType, mediaID, "example", isCheckedOut, isReserved, borrowingPeriod, startDate);

		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(checkOutItem);
        assertTrue(error.contains("Media Id cannot be 0"));
	}

    @Test
	public void testCreateCheckOutItemNegativeBorrowingPeriod() {
		assertEquals(0, mediaService.getAllMedias().size());
        
        CheckOutItem checkOutItem = null;
        Item mediaType = Item.Book;
        int mediaID = 123123123;
        boolean isCheckedOut = false;
        boolean isReserved = false;
        int borrowingPeriod = -11;
        String error = null;
        Date startDate = Date.valueOf("2021-09-11");
		try {
            checkOutItem = checkOutItemService.createCheckOutItem(mediaType, mediaID, "example", isCheckedOut, isReserved, borrowingPeriod, startDate);

		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(checkOutItem);
		assertEquals("borrowingPeriod must be more that 0", error);
        assertTrue(error.contains("borrowingPeriod must be more that 0"));
	}

    @Test
	public void testCreateCheckOutItemEmptyType() {
		assertEquals(0, mediaService.getAllMedias().size());
        
        CheckOutItem checkOutItem = null;
        Item mediaType = null;
        int mediaID = 123123123;
        boolean isCheckedOut = false;
        boolean isReserved = false;
        int borrowingPeriod = 0;
        String error = null;
        Date startDate = Date.valueOf("2021-09-11");
		try {
            checkOutItem = checkOutItemService.createCheckOutItem(mediaType, mediaID, "example", isCheckedOut, isReserved, borrowingPeriod, startDate);

		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(checkOutItem);
        assertTrue(error.contains("Media type invalid!"));
	}
    @Test
	public void testCreateCheckOutItemExistingCheckOutItem() {
		assertEquals(0, mediaService.getAllMedias().size());
        
        CheckOutItem checkOutItem = null;
        Item mediaType = Item.Book;
        int mediaID = MEDIA_ID;
        boolean isCheckedOut = false;
        boolean isReserved = false;
        int borrowingPeriod = 0;
        String error = null;
        Date startDate = Date.valueOf("2021-09-11");
		try {
            checkOutItem = checkOutItemService.createCheckOutItem(mediaType, mediaID, "example", isCheckedOut, isReserved, borrowingPeriod, startDate);

		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(checkOutItem);
		assertTrue(error.contains("Media Id already exists"));
	}
    @Test
	public void testCreateCheckOutItemInvalidStartDate() {
		assertEquals(0, mediaService.getAllMedias().size());
        
        CheckOutItem checkOutItem = null;
        Item mediaType = Item.Book;
        int mediaID = MEDIA_ID;
        boolean isCheckedOut = false;
        boolean isReserved = false;
        int borrowingPeriod = 0;
        String error = null;
        Date startDate = null;
		try {
            checkOutItem = checkOutItemService.createCheckOutItem(mediaType, mediaID, "example", isCheckedOut, isReserved, borrowingPeriod, startDate);

		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(checkOutItem);
		assertTrue(error.contains("startDate cannot be null"));
	}

    @Test
    public void testCreateCheckOutItemEmptyName(){
        CheckOutItem checkOutItem = null;
        Item mediaType = Item.Book;
        int mediaID = MEDIA_ID;
        boolean isCheckedOut = false;
        boolean isReserved = false;
        int borrowingPeriod = 0;
        String error = null;
        Date startDate = Date.valueOf("2021-09-11");
		try {
            checkOutItem = checkOutItemService.createCheckOutItem(mediaType, mediaID, "", isCheckedOut, isReserved, borrowingPeriod, startDate);

		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(checkOutItem);
        assertTrue(error.contains("Media name cannot be empty!"));
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
        String newName = "slain";
		try {
            checkOutItem = checkOutItemService.updateCheckOutItem(mediaID, newMediaType, newName, newIsCheckedOut, newIsReserved, newBorrowingPeriod, newStartDate);

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
        assertEquals(newName, checkOutItem.getName());
        //assertEquals(5, 6);
    }
    @Test
	public void testupdateCheckOutItemInvalidMedia() {
		assertEquals(0, mediaService.getAllMedias().size());
        String error = null;
        CheckOutItem checkOutItem = null;
        Item newMediaType = Item.Movie;
        int mediaID = 0;
        boolean newIsCheckedOut = false;
        boolean newIsReserved = false;
        int newBorrowingPeriod = 69;
        Date newStartDate = Date.valueOf("2021-09-11");
        String newName = "slain";
		try {
            checkOutItem = checkOutItemService.updateCheckOutItem(mediaID, newMediaType, newName, newIsCheckedOut, newIsReserved, newBorrowingPeriod, newStartDate);

		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(checkOutItem);
		assertTrue(error.contains("Media Id does not exist"));
	}
    @Test
	public void testupdateCheckOutItemInvalidMediaType() {
		assertEquals(0, mediaService.getAllMedias().size());
        String error = null;
        CheckOutItem checkOutItem = null;
        Item newMediaType = Item.Archive;
        int mediaID = MEDIA_ID;
        boolean newIsCheckedOut = false;
        boolean newIsReserved = false;
        int newBorrowingPeriod = 69;
        Date newStartDate = Date.valueOf("2021-09-11");
        String newName = "slain";
		try {
            checkOutItem = checkOutItemService.updateCheckOutItem(mediaID, newMediaType, newName, newIsCheckedOut, newIsReserved, newBorrowingPeriod, newStartDate);

		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(checkOutItem);
		assertTrue(error.contains("Media type invalid!"));
	}
    @Test
	public void testupdateCheckOutItemInvalidBorrowingPeriod() {
		assertEquals(0, mediaService.getAllMedias().size());
        String error = null;
        CheckOutItem checkOutItem = null;
        Item newMediaType = Item.Book;
        int mediaID = MEDIA_ID;
        boolean newIsCheckedOut = false;
        boolean newIsReserved = false;
        int newBorrowingPeriod = -12;
        Date newStartDate = Date.valueOf("2021-09-11");
        String newName = "more pain";
		try {
            checkOutItem = checkOutItemService.updateCheckOutItem(mediaID, newMediaType, newName, newIsCheckedOut, newIsReserved, newBorrowingPeriod, newStartDate);

		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(checkOutItem);
		assertTrue(error.contains("borrowingPeriod must be more that 0"));
	}
    @Test
	public void testupdateCheckOutItemInvalidStartDate() {
		assertEquals(0, mediaService.getAllMedias().size());
        String error = null;
        CheckOutItem checkOutItem = null;
        Item newMediaType = Item.Book;
        int mediaID = MEDIA_ID;
        boolean newIsCheckedOut = false;
        boolean newIsReserved = false;
        int newBorrowingPeriod = 23;
        Date newStartDate = null;
        String newName = "more pain";
		try {
            checkOutItem = checkOutItemService.updateCheckOutItem(mediaID, newMediaType, newName, newIsCheckedOut, newIsReserved, newBorrowingPeriod, newStartDate);

		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(checkOutItem);
		assertTrue(error.contains("startDate cannot be null"));
	}

    @Test
    public void testUpdateCheckOutItemEmptyName(){
        CheckOutItem checkOutItem = null;
        Item newMediaType = Item.Book;
        int mediaID = MEDIA_ID;
        boolean newIsCheckedOut = false;
        boolean newIsReserved = false;
        int newBorrowingPeriod = 13;
        String error = null;
        Date newStartDate = Date.valueOf("2021-09-12");
        String newName = "";
		try {
            checkOutItem = checkOutItemService.updateCheckOutItem(mediaID, newMediaType, newName, newIsCheckedOut, newIsReserved, newBorrowingPeriod, newStartDate);

		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(checkOutItem);
        assertTrue(error.contains("Media name cannot be empty!"));
    }

    @Test
    public void testGetExistingCheckOutItem(){
        assertEquals(MEDIA_ID, mediaService.getMedia(MEDIA_ID).getID());
    }
    @Test
	public void testGetNonExistingCheckOutItem() {
		assertEquals(0, mediaService.getAllMedias().size());
        String error = null;
        int mediaID = 434324;
        CheckOutItem checkOutItem = null;
		try {
            checkOutItem = (CheckOutItem)mediaService.getMedia(mediaID);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(checkOutItem);
		assertTrue(error.contains("Media ID cannot be found!"));
	}
}
