package ca.mcgill.ecse321.librarysystem.models;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;

@Entity
public class OpeningHour
{

  //The OpeningHour class is a class that holds a date which the Library will be open.
  //Only the HeadLibrarian is associated to this class, since they are the only ones
  //Who should be able to set Opening Hours for the library.

  //OpeningHours Attributes
  private int id;
  private Date date;
  private Time startTime;
  private Time endTime;
  //private Set<Date> holiday;
  //The Holiday class is one that should have been written down, but since it is not specified, it will be written later on.

  //OpeningHours Associations
  private HeadLibrarian headLibrarian;

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
  //Each opening hour will be identified with an ID for simplicity
  //alone. It could have been date, since the library should only open once
  //per day, but as a team we decided that int is far more easy to work with.
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

//  public Set<Date> getHoliday()
//  {
//    return this.holiday;
//  }

//  public void setHoliday(Set<Date> holiday){
//    this.holiday=holiday;
//  }

//This part was not working since Holiday was not a separate java class. It will be implemented later.

  //optional=false here because opening hours should not be created without a headlibrarian.
  @ManyToOne(optional=false)
  public HeadLibrarian getHeadLibrarian()
  {
    return (HeadLibrarian) headLibrarian;
  }
  
  public void setHeadLibrarian(HeadLibrarian headLibrarian) {
	  this.headLibrarian = (HeadLibrarian) headLibrarian;
  }
}