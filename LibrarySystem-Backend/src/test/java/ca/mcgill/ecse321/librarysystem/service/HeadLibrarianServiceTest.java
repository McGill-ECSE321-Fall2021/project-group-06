package ca.mcgill.ecse321.librarysystem.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;
import ca.mcgill.ecse321.librarysystem.dao.LibrarianRepository;
import ca.mcgill.ecse321.librarysystem.dao.OpeningHourRepository;
import ca.mcgill.ecse321.librarysystem.dao.ShiftRepository;
import ca.mcgill.ecse321.librarysystem.models.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem.models.Librarian;
import ca.mcgill.ecse321.librarysystem.models.OpeningHour;
import ca.mcgill.ecse321.librarysystem.models.Shift;
import ca.mcgill.ecse321.librarysystem.models.Shift.DayOfWeek;

@ExtendWith(MockitoExtension.class)
public class HeadLibrarianServiceTest {

	@Mock
	private LibrarianRepository librRepo;
	@Mock
	private OpeningHourRepository opRepo;
	@Mock
	private ShiftRepository shiftRepo;
	
	@InjectMocks
	private HeadLibrarianService headService;
	@InjectMocks
	private LibrarianService librService;
	@InjectMocks
	private OpeningHourService opService;
	@InjectMocks
	private ShiftService shiftService;
	
	private static final int HEAD_ID_KEY=5;
	private static final int HEAD_NON_EXIST_KEY=10;
	private static final int LIBR_ID=15;
	private static final int LIBR_NON_EXIST_KEY=20;
	
	private static final int OH_ID=30;
	private static final int OH_NON_EXIST_ID=35;
	private static final int SHIFT_ID=40;
	private static final int SHIFT_NON_EXIST_ID=45;
	private static final DayOfWeek WEEKDAY=DayOfWeek.Thursday;
	private static final Time START=Time.valueOf("13:00:00");
	private static final Time END=Time.valueOf("19:00:00");
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(librRepo.findLibrarianById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(HEAD_ID_KEY)) {
				HeadLibrarian head=new HeadLibrarian();
				head.setId(HEAD_ID_KEY);
				return head;
			}
			else if (invocation.getArgument(0).equals(LIBR_ID)) {
				Librarian libr=new Librarian();
				libr.setId(LIBR_ID);
				return libr;
			}
			else return null;
		});
		lenient().when(librRepo.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(HEAD_ID_KEY)) {
				HeadLibrarian head=new HeadLibrarian();
				head.setId(HEAD_ID_KEY);
				return head;
			}
			else return null;
		});
		lenient().when(opRepo.findOpeningHourById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(OH_ID)) {
				OpeningHour opHours=new OpeningHour();
				opHours.setId(OH_ID);
				opHours.setDayOfWeek(WEEKDAY);
				opHours.setStartTime(START);
				opHours.setEndTime(END);
				return opHours;
			}
			else return null;
		});
		lenient().when(shiftRepo.findShiftByShiftID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
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

		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(librRepo.save(any(Librarian.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(opRepo.save(any(OpeningHour.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(shiftRepo.save(any(Shift.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@Test
	public void testCreateHeadLibrSuccessfully() {
		//assertEquals(0, headService.getHeadLibrarians().size());
		
		int id=1;
		HeadLibrarian h=null;
		try {
			h=headService.createHeadLibrarian(id);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertEquals(id, h.getId());
	}
	
	@Test
	public void testCreateHeadLibrIdZero() {
		String error=null;
		int id=0;
		HeadLibrarian h=null;
		try {
			h=headService.createHeadLibrarian(id);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(h);
		assertTrue(error.contains("Head Librarian id cannot be 0."));
	}
	
	@Test
	public void testCreateHeadLibrTakenId() {
		String error=null;
		HeadLibrarian h=null;
		try {
			h=headService.createHeadLibrarian(HEAD_ID_KEY);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(h);
		assertTrue(error.contains("Librarian id already exists"));
	}
	
	@Test
	public void testGetExistingHeadLibr() {
		assertEquals(HEAD_ID_KEY, headService.getHeadLibrarian(HEAD_ID_KEY).getId());
	}
	
	@Test
	public void testGetNonExistingHeadLibr() {
		String error=null;
		HeadLibrarian h=null;
		try {
			h=headService.getHeadLibrarian(HEAD_NON_EXIST_KEY);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertTrue(error.contains("Librarian id does not exist"));
	}
	
	@Test
	public void testHireLibrSuccessfully() {
		int id=3;
		Librarian l=null;
		try {
			l=headService.hireLibrarian(id);
		} catch (IllegalArgumentException e){
			fail();
		}
		assertEquals(id, l.getId());
	}
	
	@Test
	public void testHireLibrIdZero() {
		String error=null;
		int id=0;
		Librarian l=null;
		try {
			l=headService.hireLibrarian(id);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(l);
		assertTrue(error.contains("Librarian id cannot be 0."));
	}
	
	@Test
	public void testHireLibrTakenId() {
		String error=null;
		Librarian l=null;
		try {
			l=headService.hireLibrarian(LIBR_ID);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(l);
		assertTrue(error.contains("Librarian id exists"));
	}
	
	@Test
	public void testCreateOPHoursSuccessfully() {
		int opId=25;
		DayOfWeek day=DayOfWeek.Friday;
		Time startTime=Time.valueOf("08:00:00");
		Time endTime=Time.valueOf("13:00:00");
		OpeningHour oh=null;
		try {
			oh=headService.createOpeningHour(opId, day, startTime, endTime);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertEquals(opId, oh.getId());
		assertEquals(day, oh.getDayOfWeek());
		assertEquals(startTime, oh.getStartTime());
		assertEquals(endTime, oh.getEndTime());
	}
	
	@Test
	public void testCreateOPHoursTakenId() {
		String error=null;
		OpeningHour oh=null;
		try {
			oh=headService.createOpeningHour(OH_ID, WEEKDAY, START, END);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(oh);
		assertTrue(error.contains("opening hour exists"));
	}
	
	@Test
	public void testCreateOPHoursIdZero() {
		String error=null;
		int opId=0;
		DayOfWeek day=DayOfWeek.Friday;
		Time startTime=Time.valueOf("08:00:00");
		Time endTime=Time.valueOf("13:00:00");
		OpeningHour oh=null;
		try {
			oh=headService.createOpeningHour(opId, day, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(oh);
		assertTrue(error.contains("opening hour id cannot be 0"));
	}
	
	@Test
	public void testCreateOPHoursDayNull() {
		String error=null;
		int opId=25;
		DayOfWeek day=null;
		Time startTime=Time.valueOf("08:00:00");
		Time endTime=Time.valueOf("13:00:00");
		OpeningHour oh=null;
		try {
			oh=headService.createOpeningHour(opId, day, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(oh);
		assertTrue(error.contains("day of week cannot be null"));
	}
	
	@Test
	public void testCreateOPHoursStartNull() {
		String error=null;
		int opId=25;
		DayOfWeek day=DayOfWeek.Friday;
		Time startTime=null;
		Time endTime=Time.valueOf("13:00:00");
		OpeningHour oh=null;
		try {
			oh=headService.createOpeningHour(opId, day, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(oh);
		assertTrue(error.contains("startTime cannot be null"));
	}
	
	@Test
	public void testCreateOPHoursEndNull() {
		String error=null;
		int opId=25;
		DayOfWeek day=DayOfWeek.Friday;
		Time startTime=Time.valueOf("08:00:00");
		Time endTime=null;
		OpeningHour oh=null;
		try {
			oh=headService.createOpeningHour(opId, day, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(oh);
		assertTrue(error.contains("endTime cannot be null"));
	}
	
	@Test
	public void testUpdateOPHoursSuccessfully() {
		assertNotNull(opService.getOpeningHour(OH_ID));
		DayOfWeek day=DayOfWeek.Thursday;
		Time startTime=Time.valueOf("08:00:00");
		Time endTime=Time.valueOf("13:00:00");
		OpeningHour oH=null;
		try {
			oH = headService.updateOpeningHour(OH_ID, day, startTime, endTime);
		} catch (IllegalArgumentException e) {
			fail();
		}
        assertEquals(OH_ID, oH.getId());
        assertEquals(day, oH.getDayOfWeek());
        assertEquals(startTime, oH.getStartTime());
        assertEquals(endTime, oH.getEndTime());
	}
	
	@Test
	public void testUpdateNonExistingOPHours() {
		String error=null;
		OpeningHour oh=null;
		try {
			oh=headService.updateOpeningHour(OH_NON_EXIST_ID, WEEKDAY, START, END);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(oh);
		assertTrue(error.contains("opening hour does not exist"));
	}
	
	@Test
	public void testUpdateOPHoursDayNull() {
		String error=null;
		DayOfWeek day=null;
		Time startTime=Time.valueOf("08:00:00");
		Time endTime=Time.valueOf("13:00:00");
		OpeningHour oh=null;
		try {
			oh=headService.updateOpeningHour(OH_ID, day, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(oh);
		assertTrue(error.contains("day of week cannot be null"));
	}
	
	@Test
	public void testUpdateOPHoursStartNull() {
		String error=null;
		DayOfWeek day=DayOfWeek.Friday;
		Time startTime=null;
		Time endTime=Time.valueOf("13:00:00");
		OpeningHour oh=null;
		try {
			oh=headService.updateOpeningHour(OH_ID, day, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(oh);
		assertTrue(error.contains("startTime cannot be null"));
	}
	
	@Test
	public void testUpdateOPHoursEndNull() {
		String error=null;
		DayOfWeek day=DayOfWeek.Friday;
		Time startTime=Time.valueOf("08:00:00");
		Time endTime=null;
		OpeningHour oh=null;
		try {
			oh=headService.updateOpeningHour(OH_ID, day, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(oh);
		assertTrue(error.contains("endTime cannot be null"));
	}
	
	@Test
	public void testCreateShiftSuccessfully() {
		int shiftId=50;
		DayOfWeek day=DayOfWeek.Friday;
		Time startTime=Time.valueOf("08:00:00");
		Time endTime=Time.valueOf("13:00:00");
		Shift oh=null;
		try {
			oh=headService.createShift(shiftId, day, startTime, endTime);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertEquals(shiftId, oh.getShiftID());
		assertEquals(day, oh.getDayOfWeek());
		assertEquals(startTime, oh.getStartTime());
		assertEquals(endTime, oh.getEndTime());
	}
	
	@Test
	public void testCreateShiftTakenId() {
		String error=null;
		Shift oh=null;
		try {
			oh=headService.createShift(SHIFT_ID, WEEKDAY, START, END);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(oh);
		assertTrue(error.contains("shift exists"));
	}
	
	@Test
	public void testCreateShiftIdZero() {
		String error=null;
		int shiftId=0;
		DayOfWeek day=DayOfWeek.Friday;
		Time startTime=Time.valueOf("08:00:00");
		Time endTime=Time.valueOf("13:00:00");
		Shift oh=null;
		try {
			oh=headService.createShift(shiftId, day, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(oh);
		assertTrue(error.contains("shift cannot be 0"));
	}
	
	@Test
	public void testCreateShiftDayNull() {
		String error=null;
		int shiftId=45;
		DayOfWeek day=null;
		Time startTime=Time.valueOf("08:00:00");
		Time endTime=Time.valueOf("13:00:00");
		Shift oh=null;
		try {
			oh=headService.createShift(shiftId, day, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(oh);
		assertTrue(error.contains("day of week cannot be null"));
	}
	
	@Test
	public void testCreateShiftStartNull() {
		String error=null;
		int shiftId=45;
		DayOfWeek day=DayOfWeek.Friday;
		Time startTime=null;
		Time endTime=Time.valueOf("13:00:00");
		Shift oh=null;
		try {
			oh=headService.createShift(shiftId, day, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(oh);
		assertTrue(error.contains("startTime cannot be null"));
	}
	
	@Test
	public void testCreateShiftEndNull() {
		String error=null;
		int shiftId=45;
		DayOfWeek day=DayOfWeek.Friday;
		Time startTime=Time.valueOf("08:00:00");
		Time endTime=null;
		Shift oh=null;
		try {
			oh=headService.createShift(shiftId, day, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(oh);
		assertTrue(error.contains("endTime cannot be null"));
	}
	
	@Test
	public void testUpdateShiftSuccessfully() {
		assertNotNull(opService.getOpeningHour(OH_ID));
		DayOfWeek day=DayOfWeek.Thursday;
		Time startTime=Time.valueOf("08:00:00");
		Time endTime=Time.valueOf("13:00:00");
		Shift oh=null;
		try {
			oh=headService.updateShift(SHIFT_ID, day, startTime, endTime);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertEquals(day, oh.getDayOfWeek());
		assertEquals(startTime, oh.getStartTime());
		assertEquals(endTime, oh.getEndTime());
	}
	
	@Test
	public void testUpdateNonExistingShift() {
		String error=null;
		Shift oh=null;
		try {
			oh=headService.updateShift(SHIFT_NON_EXIST_ID, WEEKDAY, START, END);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(oh);
		assertTrue(error.contains("shift does not exist"));
	}
	
	@Test
	public void testUpdateShiftDayNull() {
		String error=null;
		DayOfWeek day=null;
		Time startTime=Time.valueOf("08:00:00");
		Time endTime=Time.valueOf("13:00:00");
		Shift oh=null;
		try {
			oh=headService.updateShift(SHIFT_ID, day, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(oh);
		assertTrue(error.contains("day of week cannot be null"));
	}
	
	@Test
	public void testUpdateShiftStartNull() {
		String error=null;
		DayOfWeek day=DayOfWeek.Friday;
		Time startTime=null;
		Time endTime=Time.valueOf("13:00:00");
		Shift oh=null;
		try {
			oh=headService.updateShift(SHIFT_ID, day, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(oh);
		assertTrue(error.contains("startTime cannot be null"));
	}
	
	@Test
	public void testUpdateShiftEndNull() {
		String error=null;
		DayOfWeek day=DayOfWeek.Friday;
		Time startTime=Time.valueOf("08:00:00");
		Time endTime=null;
		Shift oh=null;
		try {
			oh=headService.updateShift(SHIFT_ID, day, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(oh);
		assertTrue(error.contains("endTime cannot be null"));
	}
	
	@Test
	public void testAssignSuccessfully() {
		Librarian l=null;
		Shift s=null;
		Set<Shift> allS=null;
		try {
			l=librService.getLibrarian(LIBR_ID);
			s=shiftService.getShift(SHIFT_ID);
			allS=headService.assignShift(LIBR_ID, SHIFT_ID);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertEquals(allS, librRepo.findLibrarianById(LIBR_ID).getShift());
	}
	
	@Test
	public void testAssignNonExistingLibr() {
		String error=null;
		Set<Shift> s=null;
		try {
			s=headService.assignShift(LIBR_NON_EXIST_KEY, SHIFT_ID);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertTrue(error.contains("librarian does not exist"));
	}
	
	@Test
	public void testAssignNonExistingShift() {
		String error=null;
		Set<Shift> s=null;
		try {
			s=headService.assignShift(LIBR_ID, SHIFT_NON_EXIST_ID);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertTrue(error.contains("shift does not exist"));
	}

}
