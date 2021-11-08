package ca.mcgill.ecse321.librarysystem.models;
import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Shift {
    //The Shift class is simply a class which holds librarians, a date, and two times.
    //It simply states which librarians should work when.
    //It is assigned by the HeadLibrarian.
    public enum DayOfWeek {Monday, Tuesday, Wednesday, Thursday, Friday};
    //Since there are multiple shifts per day, multiple librarians per shift, and the same
    //HeadLibrarian can assign multiple shifts, we decided to differentiate the shifts
    //with an ID integer.

    private int shiftID;
    private Librarian headLibrarian;
    private Librarian librarian;
    private DayOfWeek DayOfWeek;
    private Time startTime;
    private Time endTime;
    
    public void setShiftID(int shiftID)
    {
        this.shiftID = shiftID;
    }
    @Id
    public int getShiftID()
    {
        return this.shiftID;
    }
    

    
    @ManyToOne(optional=false)
    public Librarian getHeadLibrarian(){
      return this.headLibrarian;
    }
    public void setHeadLibrarian(Librarian headLibrarian){
      this.headLibrarian = headLibrarian;
    }
    

    
    @ManyToOne(optional=false)
    public Librarian getLibrarian(){
      return this.librarian;
    }
    public void setLibrarian(Librarian librarian){
      this.librarian = librarian;
    }
    

    public void setDayOfWeek(DayOfWeek DayOfWeek){
        this.DayOfWeek = DayOfWeek;
    }

    public DayOfWeek getDayOfWeek(){
        return this.DayOfWeek;
    }


    public void setStartTime(Time startTime){
        this.startTime = startTime;
    }

    public Time getStartTime(){
        return this.startTime;
    }


    public void setEndTime(Time endTime){
        this.endTime = endTime;
    }

    public Time getEndTime(){
        return this.endTime;
    }
}
