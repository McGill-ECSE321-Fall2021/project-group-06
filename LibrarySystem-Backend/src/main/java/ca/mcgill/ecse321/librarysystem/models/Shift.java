package ca.mcgill.ecse321.librarysystem.models;
import java.sql.Date;
import java.sql.Time;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;


@Entity
public class Shift {
    //The Shift class is simply a class which holds librarians, a date, and two times.
    //It simply states which librarians should work when.
    //It is assigned by the HeadLibrarian.

    //Since there are multiple shifts per day, multiple librarians per shift, and the same
    //HeadLibrarian can assign multiple shifts, we decided to differentiate the shifts
    //with an ID integer.
    private int shiftID;
    public void setShiftID(int shiftID)
    {
        this.shiftID = shiftID;
    }
    @Id
    public int getShiftID()
    {
        return this.shiftID;
    }
    
    private HeadLibrarian headLibrarian;
    
    @ManyToOne(optional=false)
    public HeadLibrarian getHeadLibrarian(){
      return this.headLibrarian;
    }
    public void setHeadLibrarian(HeadLibrarian headLibrarian){
      this.headLibrarian = headLibrarian;
    }
    
    private Set<Librarian> librarian;
    
    @ManyToMany(cascade={CascadeType.ALL})
    public Set<Librarian> getLibrarian(){
      return this.librarian;
    }
    public void setLibrarian(Set<Librarian> librarian){
      this.librarian = librarian;
    }
    
    private Date date;
    public void setDate(Date date){
        this.date = date;
    }

    public Date getDate(){
        return this.date;
    }

    private Time startTime;
    public void setStartTime(Time startTime){
        this.startTime = startTime;
    }

    public Time getStartTime(){
        return this.startTime;
    }

    private Time endTime;
    public void setEndTime(Time endTime){
        this.endTime = endTime;
    }

    public Time getEndTime(){
        return this.endTime;
    }
}
