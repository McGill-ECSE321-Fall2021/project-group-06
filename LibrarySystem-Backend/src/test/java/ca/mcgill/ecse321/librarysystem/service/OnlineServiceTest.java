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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.librarysystem.dao.*;
import ca.mcgill.ecse321.librarysystem.models.Online;
import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;

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

    private static final int ONLINE_ID = 727;
    private static final String ONLINE_ADDRESS = "1273 Rockefeller Street";
    private static final String ONLINE_NAME = "Haywood Jablommy";
    private static final AccountCategory ONLINE_ACCOUNTCATEGORY = AccountCategory.Online;
    private static final boolean ONLINE_ISLOCAL = true;
    private static final int ONLINE_ITEMSCHECKED = 1241;
    private static final String ONLINE_EMAIL= "scout@b.gone";
    private static final String ONLINE_PASSWORD= "Force a Nature";
    private static final String ONLINE_USERNAME= "scottish resistance";
    
    @BeforeEach
    public void setMockOutput() {
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
}
