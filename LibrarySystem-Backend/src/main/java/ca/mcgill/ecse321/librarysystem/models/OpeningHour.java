/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem.models;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;

// line 51 "model.ump"
// line 160 "model.ump"
@Entity
public class OpeningHour
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OpeningHours Attributes
  private int id;
  private Date date;
  private Time startTime;
  private Time endTime;
  //private Set<Date> holidays;

  //OpeningHours Associations
  //private LibrarySystem librarySystem;
  private HeadLibrarian headLibrarian;

  

  //------------------------
  // INTERFACE
  //------------------------

  
  public void setDate(Date aDate)
  {
    date = aDate;
  }

  public void setStartTime(Time aStartTime)
  {
    startTime = aStartTime;
  }

  public void setEndTime(Time aEndTime)
  {
    endTime = aEndTime;
  }
  public void setId(int id) {
	  this.id = id;
  }
  @Id
  public int getId() {
	  return id;
  }

  public Date getDate()
  {
    return date;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public Time getEndTime()
  {
    return endTime;
  }
  /* Code from template attribute_GetMany */
//  public Date getHoliday(int index)
//  {
//    Date aHoliday = holidays.get(index);
//    return aHoliday;
//  }

//  public Set<Date> getHolidays()
//  {
//    return holidays;
//  }

  // public int numberOfHolidays()
  // {
  //   int number = holidays.size();
  //   return number;
  // }

  // public boolean hasHolidays()
  // {
  //   boolean has = holidays.size() > 0;
  //   return has;
  // }

  // public int indexOfHoliday(Date aHoliday)
  // {
  //   int index = holidays.indexOf(aHoliday);
  //   return index;
  // }
  /* Code from template association_GetOne */
//  public LibrarySystem getLibrarySystem()
//  {
//    return librarySystem;
//  }
  /* Code from template association_GetOne */
  @ManyToOne(optional=false)
  public HeadLibrarian getHeadLibrarian()
  {
    return (HeadLibrarian) headLibrarian;
  }
  
  public void setHeadLibrarian(HeadLibrarian headLibrarian) {
	  this.headLibrarian = (HeadLibrarian) headLibrarian;
  }

  
}