package ca.mcgill.ecse321.librarysystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

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
import ca.mcgill.ecse321.librarysystem.models.NonCheckOutItem;
import ca.mcgill.ecse321.librarysystem.models.Media.Item;
           /**
     * unit test for NonCheckOutItemService class
     * @author Howard Yu
     */
@ExtendWith(MockitoExtension.class)
public class NonCheckOutItemServiceTest {
    @Mock 
    private MediaRepository mediaDao;

    @InjectMocks
    private MediaService mediaService;

    @InjectMocks
    private NonCheckOutItemService nonCheckOutItemService;

    private static final int MEDIA_ID = 123;
    private static final Item MEDIA_TYPE = Item.Book;
	private static final String MEDIA_NAME = "Pain";

    
    @BeforeEach
    public void setMockOutput() {
            lenient().when(mediaDao.findMediaByID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(MEDIA_ID)) {
                NonCheckOutItem media = new NonCheckOutItem();
                media.setType(MEDIA_TYPE);
                media.setID(MEDIA_ID);
				media.setName(MEDIA_NAME);
                return media;
            } else {
                return null;
            }
        });
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(mediaDao.save(any(CheckOutItem.class))).thenAnswer(returnParameterAsAnswer);
    }

    @Test
	public void testCreateNonCheckOutItem() {
		assertEquals(0, mediaService.getAllMedias().size());
        
        NonCheckOutItem nonCheckOutItem = null;
        Item mediaType = Item.Archive;
        int mediaID = 123123123;
		try {
            nonCheckOutItem = nonCheckOutItemService.createNonCheckOutItem(mediaType, mediaID, "example");

		}catch(IllegalArgumentException e) {
			fail();
		}

		assertNotNull(nonCheckOutItem);
		assertEquals(mediaID, nonCheckOutItem.getID());
		assertEquals(mediaType,nonCheckOutItem.getType());
	}

    @Test
	public void testCreateNonCheckOutItemInvalidMediaType() {
		assertEquals(0, mediaService.getAllMedias().size());
        String error = null;
        NonCheckOutItem nonCheckOutItem = null;
        Item mediaType = Item.Book;
        int mediaID = 123123123;
		try {
            nonCheckOutItem = nonCheckOutItemService.createNonCheckOutItem(mediaType, mediaID, "example");

		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(nonCheckOutItem);
		assertTrue(error.contains("Media type invalid!"));
	}

    @Test
	public void testCreateNonCheckOutItemEmptyID() {
		//assertEquals(0, mediaService.getAllMedias().size());
        String error = null;
        NonCheckOutItem nonCheckOutItem = null;
        Item mediaType = Item.Archive;
        int mediaID = 0;
		try {
            nonCheckOutItem = nonCheckOutItemService.createNonCheckOutItem(mediaType, mediaID, "example");

		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(nonCheckOutItem);
		assertTrue(error.contains("Media ID invalid!"));
	}

    @Test
	public void testCreateNonCheckOutItemInvalidID() {
		assertEquals(0, mediaService.getAllMedias().size());
        String error = null;
        NonCheckOutItem nonCheckOutItem = null;
        Item mediaType = Item.Archive;
        int mediaID = MEDIA_ID;
		try {
            nonCheckOutItem = nonCheckOutItemService.createNonCheckOutItem(mediaType, mediaID, "example");

		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(nonCheckOutItem);
		assertTrue(error.contains("Media ID invalid!"));
	}

	@Test
	public void testCreateNonCheckOutItemEmptyName() {
		assertEquals(0, mediaService.getAllMedias().size());
        String error = null;
        NonCheckOutItem nonCheckOutItem = null;
        Item mediaType = Item.Archive;
        int mediaID = 1341;
		try {
            nonCheckOutItem = nonCheckOutItemService.createNonCheckOutItem(mediaType, mediaID, "");

		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(nonCheckOutItem);
		assertTrue(error.contains("Media name cannot be empty!"));
	}

    @Test
	public void testUpdateNonCheckOutItem() {
		assertEquals(0, mediaService.getAllMedias().size());
        
        NonCheckOutItem nonCheckOutItem = null;
        Item mediaType = Item.Newspaper;
        int mediaID = MEDIA_ID;
		String newName = "example";
		try {
            nonCheckOutItem = nonCheckOutItemService.updateNonCheckOutItem(mediaType, mediaID, newName);

		}catch(IllegalArgumentException e) {
			fail();
		}

		assertNotNull(nonCheckOutItem);
		assertEquals(mediaID, nonCheckOutItem.getID());
		assertEquals(mediaType,nonCheckOutItem.getType());
		assertEquals(newName, nonCheckOutItem.getName());
	}

    @Test
	public void testUpdateNonCheckOutItemInvalidID() {
		assertEquals(0, mediaService.getAllMedias().size());
        String error = null;
        NonCheckOutItem nonCheckOutItem = null;
        Item mediaType = Item.Archive;
		String newName = "example";
        int mediaID = 0;
		try {
            nonCheckOutItem = nonCheckOutItemService.updateNonCheckOutItem(mediaType, mediaID, newName);

		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(nonCheckOutItem);
		assertTrue(error.contains("Media does not exist"));
	}

    @Test
	public void testUpdateNonCheckOutItemInvalidType() {
		assertEquals(0, mediaService.getAllMedias().size());
        String error = null;
        NonCheckOutItem nonCheckOutItem = null;
        Item mediaType = Item.Book;
        int mediaID = MEDIA_ID;
		String newName = "example";
		try {
            nonCheckOutItem = nonCheckOutItemService.updateNonCheckOutItem(mediaType, mediaID, newName);

		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(nonCheckOutItem);
		assertTrue(error.contains("Media type invalid!"));
	}

	@Test
	public void testUpdateNonCheckOutItemEmptyName() {
		assertEquals(0, mediaService.getAllMedias().size());
        String error = null;
        NonCheckOutItem nonCheckOutItem = null;
        Item mediaType = MEDIA_TYPE;
        int mediaID = MEDIA_ID;
		try {
            nonCheckOutItem = nonCheckOutItemService.updateNonCheckOutItem(mediaType, mediaID, "");

		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(nonCheckOutItem);
		assertTrue(error.contains("Media name cannot be empty!"));
	}

    @Test
    public void testGetExistingNonCheckOutItem(){
        assertEquals(MEDIA_ID, mediaService.getMedia(MEDIA_ID).getID());
    }
    @Test
	public void testGetNonExistingNonCheckOutItem() {
		assertEquals(0, mediaService.getAllMedias().size());
        String error = null;
        int mediaID = 434324;
        NonCheckOutItem nonCheckOutItem = null;
		try {
            nonCheckOutItem = (NonCheckOutItem)mediaService.getMedia(mediaID);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(nonCheckOutItem);
		assertTrue(error.contains("Media ID cannot be found!"));
	}
}