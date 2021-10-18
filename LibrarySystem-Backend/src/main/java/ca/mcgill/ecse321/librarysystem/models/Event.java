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

//  //------------------------
//  // MEMBER VARIABLES
//  //------------------------
//
//  //Event Attributes
  private Date date;
  private Time eventStart;
  private Time eventEnd;
  private String name;
//
//  //Event Associations
//  //private LibrarySystem librarySystem;
  private Account account;
//  
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
  
}