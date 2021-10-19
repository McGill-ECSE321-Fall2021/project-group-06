package ca.mcgill.ecse321.librarysystem.models;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Event
{
//The event class is simply an event booked by an account
//We have decided that the events will simply be open to everyone who would like
//To come. So the library will simply just be a space.
  private Date date;
  private Time eventStart;
  private Time eventEnd;
  private String name;

  private Account account;
  //we will use name as ID since it was done in the tutorial.
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

  public Time getEventStart(){
    return this.eventStart;
  }

  public void setEventEnd(Time time){
    this.eventEnd = time;
  }

  public Time getEventEnd(){
    return this.eventEnd;
  }

  //Each event MUST be booked by an account, so optional is false
  @ManyToOne(optional=false)
  public Account getAccount(){
    return this.account;
  }
  public void setAccount(Account account){
    this.account = account;
  }
}