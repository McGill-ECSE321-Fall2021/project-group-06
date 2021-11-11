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
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.test.context.TestPropertySource;
import org.yaml.snakeyaml.events.Event.ID;

import ca.mcgill.ecse321.librarysystem.dao.ShiftRepository;
import ca.mcgill.ecse321.librarysystem.models.Shift;
import ca.mcgill.ecse321.librarysystem.models.Shift.DayOfWeek;

@ExtendWith(MockitoExtension.class)
public class ShiftServiceTest {
    @Mock
    private ShiftRepository shiftDao;
    @InjectMocks
    private ShiftService shiftService;

    private static final int ID = 10;
    private static final int NON_ID = 20;
    private static final DayOfWeek DAY_OF_WEEK = DayOfWeek.Monday;
    private static final Time START_TIME = java.sql.Time.valueOf(LocalTime.of(8,05));
    private static final Time END_TIME = java.sql.Time.valueOf(LocalTime.of(18,05));
    private static final int ID_KEY_2 = 5;

    private static final int ID_KEY_N = 0;
    private static final int NON_ID_KEY_N = 0;
    private static final DayOfWeek DAY_OF_WEEK_N = null;
    private static final Time START_TIME_N = null;
    private static final Time END_TIME_N = null;

    private static final int ID_KEY_U = 15;
    private static final int NON_ID_KEY_U = 25;
    private static final DayOfWeek DAY_OF_WEEK_U = DayOfWeek.Tuesday;
    private static final Time START_TIME_U = java.sql.Time.valueOf(LocalTime.of(9,05));
    private static final Time END_TIME_U = java.sql.Time.valueOf(LocalTime.of(19,05));

    @BeforeEach
    public void setMockOutput() {
        lenient().when(shiftDao.findShiftByShiftID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ID)) {
				Shift shift = new Shift();
                shift.setShiftID(ID);
                shift.setDayOfWeek(DAY_OF_WEEK);
                shift.setStartTime(START_TIME);
                shift.setEndTime(END_TIME);
                // shiftDao.save(shift);
				return shift;
			} else {
				return null;
			}
		});

        // Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(shiftDao.save(any(Shift.class))).thenAnswer(returnParameterAsAnswer);
    }
    //Create Shift Tests
    @Test
    public void testCreateShift(){
        int id = 5;
        DayOfWeek day = DayOfWeek.Friday;
        Time start = java.sql.Time.valueOf(LocalTime.of(7,05));
        Time end = java.sql.Time.valueOf(LocalTime.of(19,05));
        Shift shift = null;
        try{
            shift = shiftService.createShift(id, day, start, end);
        } catch (IllegalArgumentException e){
            fail();
        }
        assertNotNull(shift);
        assertEquals(id, shift.getShiftID());
        assertEquals(day, shift.getDayOfWeek());
        assertEquals(start, shift.getStartTime());
        assertEquals(end, shift.getEndTime());
    }
    @Test
    public void testCreateShiftEmptyID(){
        Shift shift = null;
        String error = null;
        try{
            shift = shiftService.createShift(ID_KEY_N, DAY_OF_WEEK, START_TIME, END_TIME);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(shift);
        assertEquals("Shift Id cannot be 0", error);
    }
    @Test
    public void testCreateShiftEmptyDay(){
        Shift shift = null;
        String error = null;
        try{
            shift = shiftService.createShift(ID_KEY_2, DAY_OF_WEEK_N, START_TIME, END_TIME);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(shift);
        assertEquals("Shift day cannot be empty", error);
    }
    @Test
    public void testCreateShiftEmptyStartingTime(){
        Shift shift = null;
        String error = null;
        try{
            shift = shiftService.createShift(ID_KEY_2, DAY_OF_WEEK, START_TIME_N, END_TIME);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(shift);
        assertEquals("Shift starting time cannot be empty", error);
    }
    @Test
    public void testCreateShiftEmptyEndingTime(){
        Shift shift = null;
        String error = null;
        try{
            shift = shiftService.createShift(ID_KEY_2, DAY_OF_WEEK, START_TIME, END_TIME_N);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(shift);
        assertEquals("Shift ending time cannot be empty", error);
    }
    @Test
    public void testCreateShiftExistingID(){
        Shift shift = null;
        String error = null;
        try{
            shift = shiftService.createShift(ID, DAY_OF_WEEK, START_TIME, END_TIME);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(shift);
        assertEquals("Shift Id already exists", error);
    }
    //Done Create Shift Tests
    // @Test
    // public void testEditAssignedSchedules(){
    //     assertNotNull(shiftService.getShift(ID));
    //     shiftService.EditAssignedSchedules(ID, DAY_OF_WEEK_U, START_TIME_U, END_TIME_U);
    //     // assertNotNull(shiftDao.findShiftByShiftID(ID));
    //     // Shift shift = shiftDao.findShiftByShiftID(ID);
    //     Shift shift = shiftService.getShift(ID);
    //     assertEquals(ID, shift.getShiftID());
    //     assertEquals(DAY_OF_WEEK_U, shift.getDayOfWeek());
    //     assertEquals(START_TIME_U, shift.getStartTime());
    //     assertEquals(END_TIME_U, shift.getEndTime());
    // }
    //Update Shift Tests
    @Test
    public void testUpdateShift(){
        assertNotNull(shiftService.getShift(ID));
        Shift shift = shiftService.updateShift(ID, DAY_OF_WEEK_U, START_TIME_U, END_TIME_U);
        assertEquals(ID, shift.getShiftID());
        assertEquals(DAY_OF_WEEK_U, shift.getDayOfWeek());
        assertEquals(START_TIME_U, shift.getStartTime());
        assertEquals(END_TIME_U, shift.getEndTime());
    }
    @Test
    public void testUpdateShiftEmptyID(){
        Shift shift = null;
        String error = null;
        try{
            shift = shiftService.updateShift(ID_KEY_N, DAY_OF_WEEK, START_TIME, END_TIME);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(shift);
        assertEquals("Shift Id cannot be 0", error);
    }
    @Test
    public void testUpdateShiftEmptyDay(){
        Shift shift = null;
        String error = null;
        try{
            shift = shiftService.updateShift(ID, DAY_OF_WEEK_N, START_TIME, END_TIME);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(shift);
        assertEquals("Shift day cannot be empty", error);
    }
    @Test
    public void testUpdateShiftEmptyStartingTime(){
        Shift shift = null;
        String error = null;
        try{
            shift = shiftService.updateShift(ID, DAY_OF_WEEK, START_TIME_N, END_TIME);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(shift);
        assertEquals("Shift starting time cannot be empty", error);
    }
    @Test
    public void testUpdateShiftEmptyEndingTime(){
        Shift shift = null;
        String error = null;
        try{
            shift = shiftService.updateShift(ID, DAY_OF_WEEK, START_TIME, END_TIME_N);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(shift);
        assertEquals("Shift ending time cannot be empty", error);
    }
    //Done Update Shift Tests
    @Test
    public void testViewSchedule(){
        assertNotNull(shiftService.getShift(ID));
        assertEquals(ID, shiftService.getShift(ID).getShiftID());
    }
    @Test
    public void testViewScheduleEmpty(){
        Shift shift = null;
        String error = null;
        try{
            shift = shiftService.getShift(ID_KEY_2);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(shift);
        assertEquals("Shift Id does not exist", error);
    }
    @Test
    public void testGetShift(){
        assertNotNull(shiftService.getShift(ID));
        assertEquals(ID, shiftService.getShift(ID).getShiftID());
    }
    @Test
    public void testGetShiftEmpty(){
        Shift shift = null;
        String error = null;
        try{
            shift = shiftService.getShift(ID_KEY_2);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(shift);
        assertEquals("Shift Id does not exist", error);
    }
    @Test
    public void testGetShifts(){
        assertNotNull(shiftDao.findAll());
    }
    // @Test
    // public void testGetShiftsEmpty(){
    //     shiftDao.deleteAll();
    //     List<Shift> shifts = null;
    //     String error = null;
    //     try{
    //         shifts = (List<Shift>) shiftDao.findAll();
    //     } catch (IllegalArgumentException e){
    //         error = e.getMessage();
    //     }
    //     assertEquals("Shift list cannot be empty", error);
    // }
}
