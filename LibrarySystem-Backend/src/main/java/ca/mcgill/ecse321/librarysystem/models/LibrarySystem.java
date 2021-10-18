package ca.mcgill.ecse321.librarysystem.models;

import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;

//import java.sql.Date;
//import java.sql.Time;

// line 2 "model.ump"
// line 103 "model.ump"
@Entity
public class LibrarySystem
{
  private Set<Account> accounts;
  private OpeningHour openingHour;
  private Set<Event> events;
  private Set<Media> medias;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  /*public LibrarySystem(OpeningHours aOpeningHours)
  {
    accounts = new ArrayList<Account>();
    if (aOpeningHours == null || aOpeningHours.getLibrarySystem() != null)
    {
      throw new RuntimeException("Unable to create LibrarySystem due to aOpeningHours. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    openingHours = aOpeningHours;
    events = new ArrayList<Event>();
    medias = new ArrayList<Media>();
  }

  public LibrarySystem(Date aDateForOpeningHours, Time aStartTimeForOpeningHours, Time aEndTimeForOpeningHours, HeadLibrarian aHeadLibrarianForOpeningHours)
  {
    accounts = new ArrayList<Account>();
    openingHours = new OpeningHours(aDateForOpeningHours, aStartTimeForOpeningHours, aEndTimeForOpeningHours, this, aHeadLibrarianForOpeningHours);
    events = new ArrayList<Event>();
    medias = new ArrayList<Media>();
  }*/

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  /*@Id
  public Account getAccount(int index)
  {
    Account aAccount = accounts.get(index);
    return aAccount;
  }*/
  @OneToMany(cascade = {CascadeType.ALL})
  public Set<Account> getAccounts()
  {
    return this.accounts;
  }

  public void setAccounts(Set<Account> accounts)
  {
    this.accounts = accounts;
  }
  /*
  public int numberOfAccounts()
  {
    int number = accounts.size();
    return number;
  }

  public boolean hasAccounts()
  {
    boolean has = accounts.size() > 0;
    return has;
  }

  public int indexOfAccount(Account aAccount)
  {
    int index = accounts.indexOf(aAccount);
    return index;
  }*/
  /* Code from template association_GetOne */
  @OneToOne
  public OpeningHour getOpeningHour()
  {
    return this.openingHour;
  }

  public void setOpeningHour(OpeningHour openingHours)
  {
    this.openingHour = openingHours;
  }

  /* Code from template association_GetMany */
  
  /*public Event getEvent(int index)
  {
    Event aEvent = events.get(index);
    return aEvent;
  }*/
  @OneToMany(cascade = {CascadeType.ALL})
  public Set<Event> getEvents()
  {
    return this.events;
  }

  public void setEvents(Set<Event> events)
  {
    this.events = events;
  }
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
  /*
  public int numberOfEvents()
  {
    int number = events.size();
    return number;
  }

  public boolean hasEvents()
  {
    boolean has = events.size() > 0;
    return has;
  }

  public int indexOfEvent(Event aEvent)
  {
    int index = events.indexOf(aEvent);
    return index;
  }*/
  /* Code from template association_GetMany */
  /*public Media getMedia(int index)
  {
    Media aMedia = medias.get(index);
    return aMedia;
  }*/
  @OneToMany(cascade = {CascadeType.ALL})
  public Set<Media> getMedias()
  {
    return this.medias;
  }

  public void setMedias(Set<Media> medias)
  {
    this.medias = medias;
  }
  /*
  public int numberOfMedias()
  {
    int number = medias.size();
    return number;
  }

  public boolean hasMedias()
  {
    boolean has = medias.size() > 0;
    return has;
  }

  public int indexOfMedia(Media aMedia)
  {
    int index = medias.indexOf(aMedia);
    return index;
  }*/
  /* Code from template association_MinimumNumberOfMethod */
  /*public static int minimumNumberOfAccounts()
  {
    return 0;
  }*/
  /* Code from template association_AddManyToOne */

  /*
  public boolean addAccount(Account aAccount)
  {
    boolean wasAdded = false;
    if (accounts.contains(aAccount)) { return false; }
    LibrarySystem existingLibrarySystem = aAccount.getLibrarySystem();
    boolean isNewLibrarySystem = existingLibrarySystem != null && !this.equals(existingLibrarySystem);
    if (isNewLibrarySystem)
    {
      aAccount.setLibrarySystem(this);
    }
    else
    {
      accounts.add(aAccount);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAccount(Account aAccount)
  {
    boolean wasRemoved = false;
    //Unable to remove aAccount, as it must always have a librarySystem
    if (!this.equals(aAccount.getLibrarySystem()))
    {
      accounts.remove(aAccount);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  /*
  public boolean addAccountAt(Account aAccount, int index)
  {  
    boolean wasAdded = false;
    if(addAccount(aAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAccounts()) { index = numberOfAccounts() - 1; }
      accounts.remove(aAccount);
      accounts.add(index, aAccount);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAccountAt(Account aAccount, int index)
  {
    boolean wasAdded = false;
    if(accounts.contains(aAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAccounts()) { index = numberOfAccounts() - 1; }
      accounts.remove(aAccount);
      accounts.add(index, aAccount);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAccountAt(aAccount, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  /*
  public static int minimumNumberOfEvents()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  /*
  public Event addEvent(Date aDate, Time aEventStart, Time aEventEnd, Account aAccount)
  {
    return new Event(aDate, aEventStart, aEventEnd, this, aAccount);
  }

  public boolean addEvent(Event aEvent)
  {
    boolean wasAdded = false;
    if (events.contains(aEvent)) { return false; }
    LibrarySystem existingLibrarySystem = aEvent.getLibrarySystem();
    boolean isNewLibrarySystem = existingLibrarySystem != null && !this.equals(existingLibrarySystem);
    if (isNewLibrarySystem)
    {
      aEvent.setLibrarySystem(this);
    }
    else
    {
      events.add(aEvent);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEvent(Event aEvent)
  {
    boolean wasRemoved = false;
    //Unable to remove aEvent, as it must always have a librarySystem
    if (!this.equals(aEvent.getLibrarySystem()))
    {
      events.remove(aEvent);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  /*
  public boolean addEventAt(Event aEvent, int index)
  {  
    boolean wasAdded = false;
    if(addEvent(aEvent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEvents()) { index = numberOfEvents() - 1; }
      events.remove(aEvent);
      events.add(index, aEvent);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEventAt(Event aEvent, int index)
  {
    boolean wasAdded = false;
    if(events.contains(aEvent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEvents()) { index = numberOfEvents() - 1; }
      events.remove(aEvent);
      events.add(index, aEvent);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEventAt(aEvent, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  /*
  public static int minimumNumberOfMedias()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */

  /*
  public boolean addMedia(Media aMedia)
  {
    boolean wasAdded = false;
    if (medias.contains(aMedia)) { return false; }
    LibrarySystem existingLibrarySystem = aMedia.getLibrarySystem();
    boolean isNewLibrarySystem = existingLibrarySystem != null && !this.equals(existingLibrarySystem);
    if (isNewLibrarySystem)
    {
      aMedia.setLibrarySystem(this);
    }
    else
    {
      medias.add(aMedia);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMedia(Media aMedia)
  {
    boolean wasRemoved = false;
    //Unable to remove aMedia, as it must always have a librarySystem
    if (!this.equals(aMedia.getLibrarySystem()))
    {
      medias.remove(aMedia);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  /*
  public boolean addMediaAt(Media aMedia, int index)
  {  
    boolean wasAdded = false;
    if(addMedia(aMedia))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMedias()) { index = numberOfMedias() - 1; }
      medias.remove(aMedia);
      medias.add(index, aMedia);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMediaAt(Media aMedia, int index)
  {
    boolean wasAdded = false;
    if(medias.contains(aMedia))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMedias()) { index = numberOfMedias() - 1; }
      medias.remove(aMedia);
      medias.add(index, aMedia);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMediaAt(aMedia, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (accounts.size() > 0)
    {
      Account aAccount = accounts.get(accounts.size() - 1);
      aAccount.delete();
      accounts.remove(aAccount);
    }
    
    OpeningHours existingOpeningHours = openingHours;
    openingHours = null;
    if (existingOpeningHours != null)
    {
      existingOpeningHours.delete();
    }
    while (events.size() > 0)
    {
      Event aEvent = events.get(events.size() - 1);
      aEvent.delete();
      events.remove(aEvent);
    }
    
    while (medias.size() > 0)
    {
      Media aMedia = medias.get(medias.size() - 1);
      aMedia.delete();
      medias.remove(aMedia);
    }
    
  }
  */
  private int id;
  public void setId(int value){
    this.id = value;
  }
  @Id
  public int getId(){
    return this.id;
  }
}