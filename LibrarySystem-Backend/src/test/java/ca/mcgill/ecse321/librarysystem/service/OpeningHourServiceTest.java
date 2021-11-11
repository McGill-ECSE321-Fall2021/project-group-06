package ca.mcgill.ecse321.librarysystem.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import java.sql.Time;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.librarysystem.dao.OpeningHourRepository;
import ca.mcgill.ecse321.librarysystem.models.OpeningHour;
import ca.mcgill.ecse321.librarysystem.models.Shift.DayOfWeek;
/**
 * Author Niels Mainville
 */
@ExtendWith(MockitoExtension.class)
public class OpeningHourServiceTest {
    @Mock
    private OpeningHourRepository openingHourDao;
    @InjectMocks
    private OpeningHourService openingHourService;

    private static final int ID_KEY = 10;
    private static final int NON_ID_KEY = 20;
    private static final DayOfWeek DAY_OF_WEEK = DayOfWeek.Monday;
    private static final Time START_TIME = java.sql.Time.valueOf(LocalTime.of(8,05));
    private static final Time END_TIME = java.sql.Time.valueOf(LocalTime.of(18,05));

    private static final int ID_KEY_2 = 5;

    private static final int ID_KEY_N = 0;
    private static final DayOfWeek DAY_OF_WEEK_N = null;
    private static final Time START_TIME_N = null;
    private static final Time END_TIME_N = null;

    private static final DayOfWeek DAY_OF_WEEK_U = DayOfWeek.Tuesday;
    private static final Time START_TIME_U = java.sql.Time.valueOf(LocalTime.of(9,05));
    private static final Time END_TIME_U = java.sql.Time.valueOf(LocalTime.of(19,05));


    @BeforeEach
    public void setMockOutput() {
        lenient().when(openingHourDao.findOpeningHourById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ID_KEY)) {
				OpeningHour oH = new OpeningHour();
                oH.setId(ID_KEY);
                oH.setDayOfWeek(DAY_OF_WEEK);
                oH.setStartTime(START_TIME);
                oH.setEndTime(END_TIME);
				return oH;
			} else {
				return null;
			}
		});

        // Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(openingHourDao.save(any(OpeningHour.class))).thenAnswer(returnParameterAsAnswer);
    }
    //Test Create Opening Hour
    @Test
    public void testCreateOpeningHour() {

        int id = 5;
        DayOfWeek day = DayOfWeek.Friday;
        Time start = java.sql.Time.valueOf(LocalTime.of(7,05));
        Time end = java.sql.Time.valueOf(LocalTime.of(19,05));
        OpeningHour oH = null;
        try{
            oH = openingHourService.createOpeningHour(id, day, start, end);
        } catch (IllegalArgumentException e){
            fail();
        }
        assertNotNull(oH);
        assertEquals(id, oH.getId());
        assertEquals(day, oH.getDayOfWeek());
        assertEquals(start, oH.getStartTime());
        assertEquals(end, oH.getEndTime());
    }
    @Test
    public void testCreateOpeningHourEmptyID(){
        OpeningHour oH = null;
        String error = null;
        try{
            oH = openingHourService.createOpeningHour(ID_KEY_N, DAY_OF_WEEK, START_TIME, END_TIME);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(oH);
        assertEquals("Opening Hour Id cannot be 0", error);
    }
    @Test
    public void testCreateOpeningHourEmptyDay(){
        OpeningHour oH = null;
        String error = null;
        try{
            oH = openingHourService.createOpeningHour(ID_KEY_2, DAY_OF_WEEK_N, START_TIME, END_TIME);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(oH);
        assertEquals("Opening Hour day cannot be empty", error);
    }
    @Test
    public void testCreateOpeningHourEmptyStartTime(){
        OpeningHour oH = null;
        String error = null;
        try{
            oH = openingHourService.createOpeningHour(ID_KEY_2, DAY_OF_WEEK, START_TIME_N, END_TIME);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(oH);
        assertEquals("Opening Hour starting time cannot be empty", error);
    }
    @Test
    public void testCreateOpeningHourEmptyEndTime(){
        OpeningHour oH = null;
        String error = null;
        try{
            oH = openingHourService.createOpeningHour(ID_KEY_2, DAY_OF_WEEK, START_TIME, END_TIME_N);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(oH);
        assertEquals("Opening Hour ending time cannot be empty", error);
    }    @Test
    public void testCreateOpeningHourExistingID(){
        OpeningHour oH = null;
        String error = null;
        try{
            oH = openingHourService.createOpeningHour(ID_KEY, DAY_OF_WEEK, START_TIME, END_TIME);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(oH);
        assertEquals("Opening Hour Id already exists", error);
    }
    //Done Test Create Opening Hour
    @Test
    public void testGetOpeningHourIDis0(){
        OpeningHour oH = null;
        String error = null;
        try{
            oH = openingHourService.getOpeningHour(0);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(oH);
        assertEquals("OpeningHour cannot be found!", error);
    }
    @Test
    public void testDeleteExistingOpeningHour(){
        assertNotNull(openingHourService.getOpeningHour(ID_KEY));
        assertTrue(openingHourService.deleteOpeningHour(ID_KEY));
    }
    @Test
    public void testDeleteNonExistingOpeningHour(){
        OpeningHour oH = null;
        //Boolean oHDel = false;
        String error = null;
        try{
            openingHourService.deleteOpeningHour(NON_ID_KEY);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(oH);
        assertEquals("Opening Hour Id does not exist", error);
    }
    //Test Update Opening Hour
    @Test
    public void testUpdateOpeningHours(){
        assertNotNull(openingHourService.getOpeningHour(ID_KEY));
        OpeningHour oH = openingHourService.updateOpeningHours(ID_KEY,DAY_OF_WEEK_U, START_TIME_U, END_TIME_U);
        assertEquals(ID_KEY, oH.getId());
        assertEquals(DAY_OF_WEEK_U, oH.getDayOfWeek());
        assertEquals(START_TIME_U, oH.getStartTime());
        assertEquals(END_TIME_U, oH.getEndTime());

    }
    @Test
    public void testUpdateOpeningHoursEmptyID(){
        OpeningHour oH = null;
        String error = null;
        try{
            oH = openingHourService.updateOpeningHours(ID_KEY_N, DAY_OF_WEEK, START_TIME, END_TIME);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(oH);
        assertEquals("Opening Hour Id does not exist", error);
    }
    @Test
    public void testUpdateOpeningHoursEmptyDay(){
        OpeningHour oH = null;
        String error = null;
        try{
            oH = openingHourService.updateOpeningHours(ID_KEY, DAY_OF_WEEK_N, START_TIME, END_TIME);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(oH);
        assertEquals("Opening Hour day cannot be empty", error);
    }
    @Test
    public void testUpdateOpeningHoursEmptyStartTime(){
        OpeningHour oH = null;
        String error = null;
        try{
            oH = openingHourService.updateOpeningHours(ID_KEY, DAY_OF_WEEK, START_TIME_N, END_TIME);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(oH);
        assertEquals("Opening Hour starting time cannot be empty", error);
    }
    @Test
    public void testUpdateOpeningHourEmptyEndTime(){
        OpeningHour oH = null;
        String error = null;
        try{
            oH = openingHourService.updateOpeningHours(ID_KEY, DAY_OF_WEEK, START_TIME, END_TIME_N);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(oH);
        assertEquals("Opening Hour ending time cannot be empty", error);
    }
    //Done testing Update Opening Hour
    @Test
    public void testGetExistingOpeningHour(){
        assertEquals(ID_KEY, openingHourService.getOpeningHour(ID_KEY).getId());
    }
    @Test
    public void testGetNonExistingOpeningHour(){
        OpeningHour oH = null;
        String error = null;
        try{
            oH = openingHourService.getOpeningHour(NON_ID_KEY);
        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(oH);
        assertEquals("OpeningHour cannot be found!", error);
    }
}
