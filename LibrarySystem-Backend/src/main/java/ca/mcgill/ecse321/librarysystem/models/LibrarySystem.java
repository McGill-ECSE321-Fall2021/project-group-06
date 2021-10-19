package ca.mcgill.ecse321.librarysystem.models;

import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
import javax.persistence.CascadeType;

@Entity
public class LibrarySystem
{
  private Set<Account> accounts;
  private Set<OpeningHour> openingHour;
  private Set<Event> events;
  private Set<Media> medias;

  //There will be multiple accounts per library
  @OneToMany(cascade = {CascadeType.ALL})
  public Set<Account> getAccounts()
  {
    return this.accounts;
  }

  public void setAccounts(Set<Account> accounts)
  {
    this.accounts = accounts;
  }
  
  //Each library will open more than once
  @OneToMany(cascade = {CascadeType.ALL})
  public Set<OpeningHour> getOpeningHour()
  {
    return this.openingHour;
  }

  public void setOpeningHour(Set<OpeningHour> openingHours)
  {
    this.openingHour = openingHours;
  }

  //Each library will have multiple events
  @OneToMany(cascade = {CascadeType.ALL})
  public Set<Event> getEvents()
  {
    return this.events;
  }

  public void setEvents(Set<Event> events)
  {
    this.events = events;
  }

  //Each library will have multiple shifts.
  private Set<Shift> shift;
  @OneToMany(cascade = {CascadeType.ALL})
  public Set<Shift> getShift()
  {
    return this.shift;
  }

  public void setShift(Set<Shift> shift)
  {
    this.shift = shift;
  }

  //Each library will have multiple medias.
  @OneToMany(cascade = {CascadeType.ALL})
  public Set<Media> getMedias()
  {
    return this.medias;
  }

  public void setMedias(Set<Media> medias)
  {
    this.medias = medias;
  }

  //This is only here because it was mimicked from the tutorial.
  //EventRegistration had no attribute in the UML, but still had this.
  private int id;
  public void setId(int value){
    this.id = value;
  }
  @Id
  public int getId(){
    return this.id;
  }
}