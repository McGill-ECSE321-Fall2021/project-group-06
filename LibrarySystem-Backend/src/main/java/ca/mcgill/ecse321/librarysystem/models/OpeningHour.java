/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem.models;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;;

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
  private Set<Date> holidays;

  //OpeningHours Associations
  //private LibrarySystem librarySystem;
  private HeadLibrarian headLibrarian;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  // public OpeningHour(Date aDate, Time aStartTime, Time aEndTime, LibrarySystem aLibrarySystem, HeadLibrarian aHeadLibrarian)
  // {
  //   date = aDate;
  //   startTime = aStartTime;
  //   endTime = aEndTime;
  //   holidays = new ArrayList<Date>();
  //   // if (aLibrarySystem == null || aLibrarySystem.getOpeningHour() != null)
  //   // {
  //   //   throw new RuntimeException("Unable to create OpeningHours due to aLibrarySystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
  //   // }
  //   librarySystem = aLibrarySystem;
  //   // if (aHeadLibrarian == null || aHeadLibrarian.getOpeningHour() != null)
  //   // {
  //   //   throw new RuntimeException("Unable to create OpeningHours due to aHeadLibrarian. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
  //   // }
  //   headLibrarian = aHeadLibrarian;
  // }

  // public OpeningHour(Date aDate, Time aStartTime, Time aEndTime, int aIdForHeadLibrarian, String aAddressForHeadLibrarian, String aNameForHeadLibrarian, AccountCategory aAccountCategoryForHeadLibrarian, boolean aIsLocalForHeadLibrarian, int aNumCheckedForHeadLibrarian, LibrarySystem aLibrarySystemForHeadLibrarian, Time aStartShiftForHeadLibrarian, Time aEndShiftForHeadLibrarian)
  // {
  //   date = aDate;
  //   startTime = aStartTime;
  //   endTime = aEndTime;
  //   holidays = new ArrayList<Date>();
  //   //librarySystem = new LibrarySystem(this);
  //   //headLibrarian = new HeadLibrarian(aIdForHeadLibrarian, aAddressForHeadLibrarian, aNameForHeadLibrarian, aAccountCategoryForHeadLibrarian, aIsLocalForHeadLibrarian, aNumCheckedForHeadLibrarian, aLibrarySystemForHeadLibrarian, aStartShiftForHeadLibrarian, aEndShiftForHeadLibrarian, this);
  // }

  //------------------------
  // INTERFACE
  //------------------------

  public void setId(int id) {
	  this.id = id;
  }
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
  // /* Code from template attribute_SetMany */
  // public boolean addHoliday(Date aHoliday)
  // {
  //   boolean wasAdded = false;
  //   wasAdded = holidays.add(aHoliday);
  //   return wasAdded;
  // }

  // public boolean removeHoliday(Date aHoliday)
  // {
  //   boolean wasRemoved = false;
  //   wasRemoved = holidays.remove(aHoliday);
  //   return wasRemoved;
  // }
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

  public Set<Date> getHolidays()
  {
    return holidays;
  }

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
    return headLibrarian;
  }

  // public void delete()
  // {
  //   LibrarySystem existingLibrarySystem = librarySystem;
  //   librarySystem = null;
  //   if (existingLibrarySystem != null)
  //   {
  //     //existingLibrarySystem.delete();
  //   }
  //   HeadLibrarian existingHeadLibrarian = headLibrarian;
  //   headLibrarian = null;
  //   if (existingHeadLibrarian != null)
  //   {
  //     existingHeadLibrarian.delete();
  //   }
  // }


  // public String toString()
  // {
  //   return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
  //           "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
  //           "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
  //           "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
  //           "  " + "librarySystem = "+(getLibrarySystem()!=null?Integer.toHexString(System.identityHashCode(getLibrarySystem())):"null") + System.getProperties().getProperty("line.separator") +
  //           "  " + "headLibrarian = "+(getHeadLibrarian()!=null?Integer.toHexString(System.identityHashCode(getHeadLibrarian())):"null");
  // }
}