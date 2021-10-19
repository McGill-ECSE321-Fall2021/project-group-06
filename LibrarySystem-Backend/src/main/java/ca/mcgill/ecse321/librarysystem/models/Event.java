/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem.models;


import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

// line 59 "model.ump"
// line 134 "model.ump"
@Entity
public class Event
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Event Attributes
  private Date date;
  private Time eventStart;
  private Time eventEnd;
  private String name;

  //Event Associations
  //private LibrarySystem librarySystem;
  private Account account;
  
  public void setName(String name){
    this.name = name;
  }
  @Id
  public String getName(){
    return this.name;
  }
  
  public void setDate(Date date){
    this.date = date;
  }

  public Date getDate(){
    return this.date;
  }

  public void setEventStart(Time time){
    this.eventStart = time;
  }

  public Time getEventStart(Time time){
    return this.eventStart;
  }

  public void setEventEnd(Time time){
    this.eventEnd = time;
  }

  public Time getEventEnd(Time time){
    return this.eventEnd;
  }

  @ManyToOne(optional=false)
  public Account getAccount(){
    return this.account;
  }

  public void setAccount(Account account){
    this.account = account;
  }
  //------------------------
  // CONSTRUCTOR
  //------------------------

  /*public Event(Date aDate, Time aEventStart, Time aEventEnd, LibrarySystem aLibrarySystem, Account aAccount)
  {
    date = aDate;
    eventStart = aEventStart;
    eventEnd = aEventEnd;
    boolean didAddLibrarySystem = setLibrarySystem(aLibrarySystem);
    if (!didAddLibrarySystem)
    {
      throw new RuntimeException("Unable to create event due to librarySystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddAccount = setAccount(aAccount);
    if (!didAddAccount)
    {
      throw new RuntimeException("Unable to create event due to account. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }*/

  //------------------------
  // INTERFACE
  //------------------------
  /*
  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setEventStart(Time aEventStart)
  {
    boolean wasSet = false;
    eventStart = aEventStart;
    wasSet = true;
    return wasSet;
  }
  
  public boolean setEventEnd(Time aEventEnd)
  {
    boolean wasSet = false;
    eventEnd = aEventEnd;
    wasSet = true;
    return wasSet;
  }
  */
//@Id
  /*
  public Date getDate()
  {
    return date;
  }

  public Time getEventStart()
  {
    return eventStart;
  }

  public Time getEventEnd()
  {
    return eventEnd;
  }*/
  /* Code from template association_GetOne */
  /*public LibrarySystem getLibrarySystem()
  {
    return librarySystem;
  }
  /* Code from template association_GetOne */
  /*public Account getAccount()
  {
    return account;
  }
  /* Code from template association_SetOneToMany */
  /*@ManyToOne
  public boolean setLibrarySystem(LibrarySystem aLibrarySystem)
  {
    boolean wasSet = false;
    if (aLibrarySystem == null)
    {
      return wasSet;
    }

    LibrarySystem existingLibrarySystem = librarySystem;
    librarySystem = aLibrarySystem;
    if (existingLibrarySystem != null && !existingLibrarySystem.equals(aLibrarySystem))
    {
      existingLibrarySystem.removeEvent(this);
    }
    librarySystem.addEvent(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  /*public boolean setAccount(Account aAccount)
  {
    boolean wasSet = false;
    if (aAccount == null)
    {
      return wasSet;
    }

    Account existingAccount = account;
    account = aAccount;
    if (existingAccount != null && !existingAccount.equals(aAccount))
    {
      existingAccount.removeEvent(this);
    }
    account.addEvent(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    LibrarySystem placeholderLibrarySystem = librarySystem;
    this.librarySystem = null;
    if(placeholderLibrarySystem != null)
    {
      placeholderLibrarySystem.removeEvent(this);
    }
    Account placeholderAccount = account;
    this.account = null;
    if(placeholderAccount != null)
    {
      placeholderAccount.removeEvent(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "eventStart" + "=" + (getEventStart() != null ? !getEventStart().equals(this)  ? getEventStart().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "eventEnd" + "=" + (getEventEnd() != null ? !getEventEnd().equals(this)  ? getEventEnd().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "librarySystem = "+(getLibrarySystem()!=null?Integer.toHexString(System.identityHashCode(getLibrarySystem())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "account = "+(getAccount()!=null?Integer.toHexString(System.identityHashCode(getAccount())):"null");
  }*/
}