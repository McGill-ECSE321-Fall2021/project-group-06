/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem.models;


import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.sql.Date;
import java.sql.Time;

// line 11 "model.ump"
// line 112 "model.ump"
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS) //Account has a table
//@MappedSuperclass (Account does not have a table)
public abstract class Account
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum AccountCategory { Online, Offline }

  //------------------------
  // STATIC VARIABLES
  //------------------------
  

  //private static Map<Integer, Account> accountsById = new HashMap<Integer, Account>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Account Attributes
  private int id;
  private String address;
  private String name;
  private AccountCategory accountCategory;
  private boolean isLocal;
  private Set<Media> checkedOutItems;
  private int numChecked;

  //Account Associations
  private Set<Event> events;
  private Set<Media> medias;
  //private LibrarySystem librarySystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  /*public Account(int aId, String aAddress, String aName, AccountCategory aAccountCategory, boolean aIsLocal, int aNumChecked, LibrarySystem aLibrarySystem)
  {
    address = aAddress;
    name = aName;
    accountCategory = aAccountCategory;
    isLocal = aIsLocal;
    checkedOutItems = new ArrayList<Media>();
    numChecked = aNumChecked;
    if (!setId(aId))
    {
      throw new RuntimeException("Cannot create due to duplicate id. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    events = new ArrayList<Event>();
    medias = new ArrayList<Media>();
    boolean didAddLibrarySystem = setLibrarySystem(aLibrarySystem);
    if (!didAddLibrarySystem)
    {
      throw new RuntimeException("Unable to create account due to librarySystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }*/

  //------------------------
  // INTERFACE
  //------------------------

  public void setId(int value){
    this.id = value;
  }
  @Id
  public int getId(){
    return this.id;
  }

  public String getAddress()
  {
    return address;
  }

  public void setAddress(String address)
  {
    this.address = address;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String aName)
  {
    this.name = aName;
  }
  
@Enumerated
  public AccountCategory getAccountCategory()
  {
    return accountCategory;
  }

  public void setAccountCategory(AccountCategory aAccountCategory)
  {
    this.accountCategory = aAccountCategory;
  }

  public boolean getIsLocal()
  {
    return isLocal;
  }

  public void setIsLocal(boolean aIsLocal)
  {
    this.isLocal = aIsLocal;
  }

  public Set<Media> getCheckedOutItem()
  {
    return this.checkedOutItems;
  }

  public int getNumChecked()
  {
    return numChecked;
  }

  public void setNumChecked(int aNumChecked)
  {
    this.numChecked = aNumChecked;
  }

  @OneToMany(mappedBy = "Account")
  public Set<Event> getEvents(){
    return this.events;
  }

  public void setEvents(Set<Event> events){
    this.events=events;
  }

  @OneToMany(mappedBy = "Account")
  public Set<Media> getMedias(){
    return this.medias;
  }

  public void setMedias(Set<Media> medias){
    this.medias=medias;
  }

  /*public boolean setId(int aId)
  {
    boolean wasSet = false;
    Integer anOldId = getId();
    if (anOldId != null && anOldId.equals(aId)) {
      return true;
    }
    if (hasWithId(aId)) {
      return wasSet;
    }
    id = aId;
    wasSet = true;
    if (anOldId != null) {
      accountsById.remove(anOldId);
    }
    accountsById.put(aId, this);
    return wasSet;
  }*/
  
  /* Code from template attribute_SetMany */
  /*public boolean addCheckedOutItem(Media aCheckedOutItem)
  {
    boolean wasAdded = false;
    wasAdded = checkedOutItems.add(aCheckedOutItem);
    return wasAdded;
  }

  public boolean removeCheckedOutItem(Media aCheckedOutItem)
  {
    boolean wasRemoved = false;
    wasRemoved = checkedOutItems.remove(aCheckedOutItem);
    return wasRemoved;
  }

  
  /* Code from template attribute_GetUnique */
  /*public static Account getWithId(int aId)
  {
    return accountsById.get(aId);
  }
  /* Code from template attribute_HasUnique */
  /*public static boolean hasWithId(int aId)
  {
    return getWithId(aId) != null;
  }*/

  

  

  

  
  /* Code from template attribute_GetMany */
  
/*
  public Media[] getCheckedOutItems()
  {
    Media[] newCheckedOutItems = checkedOutItems.toArray(new Media[checkedOutItems.size()]);
    return newCheckedOutItems;
  }

  public int numberOfCheckedOutItems()
  {
    int number = checkedOutItems.size();
    return number;
  }

  public boolean hasCheckedOutItems()
  {
    boolean has = checkedOutItems.size() > 0;
    return has;
  }

  public int indexOfCheckedOutItem(Media aCheckedOutItem)
  {
    int index = checkedOutItems.indexOf(aCheckedOutItem);
    return index;
  }
*/
  
  /* Code from template attribute_IsBoolean */
  /*public boolean isIsLocal()
  {
    return isLocal;
  }*/
  /* Code from template association_GetMany */
  /*public Event getEvent(int index)
  {
    Event aEvent = events.get(index);
    return aEvent;
  }

  public List<Event> getEvents()
  {
    List<Event> newEvents = Collections.unmodifiableList(events);
    return newEvents;
  }*/

  /*public int numberOfEvents()
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

  /*public List<Media> getMedias()
  {
    List<Media> newMedias = Collections.unmodifiableList(medias);
    return newMedias;
  }*/

  /*public int numberOfMedias()
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
  /* Code from template association_GetOne */
  /*public LibrarySystem getLibrarySystem()
  {
    return librarySystem;
  }*/
  /* Code from template association_MinimumNumberOfMethod */
  /*public static int minimumNumberOfEvents()
  {
    return 0;
  }*/
  /* Code from template association_AddManyToOne */
  /*public Event addEvent(Date aDate, Time aEventStart, Time aEventEnd, LibrarySystem aLibrarySystem)
  {
    return new Event(aDate, aEventStart, aEventEnd, aLibrarySystem, this);
  }*/

  /*public boolean addEvent(Event aEvent)
  {
    boolean wasAdded = false;
    if (events.contains(aEvent)) { return false; }
    Account existingAccount = aEvent.getAccount();
    boolean isNewAccount = existingAccount != null && !this.equals(existingAccount);
    if (isNewAccount)
    {
      aEvent.setAccount(this);
    }
    else
    {
      events.add(aEvent);
    }
    wasAdded = true;
    return wasAdded;
  }*/

  /*public boolean removeEvent(Event aEvent)
  {
    boolean wasRemoved = false;
    //Unable to remove aEvent, as it must always have a account
    if (!this.equals(aEvent.getAccount()))
    {
      events.remove(aEvent);
      wasRemoved = true;
    }
    return wasRemoved;
  }*/
  /* Code from template association_AddIndexControlFunctions */
  /*public boolean addEventAt(Event aEvent, int index)
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
  /*public static int minimumNumberOfMedias()
  {
    return 0;
  }*/
  /* Code from template association_MaximumNumberOfMethod */
  /*public static int maximumNumberOfMedias()
  {
    return 5;
  }*/
  /* Code from template association_AddOptionalNToOne */


  /*public boolean addMedia(Media aMedia)
  {
    boolean wasAdded = false;
    if (medias.contains(aMedia)) { return false; }
    if (numberOfMedias() >= maximumNumberOfMedias())
    {
      return wasAdded;
    }

    Account existingAccount = aMedia.getAccount();
    boolean isNewAccount = existingAccount != null && !this.equals(existingAccount);
    if (isNewAccount)
    {
      aMedia.setAccount(this);
    }
    else
    {
      medias.add(aMedia);
    }
    wasAdded = true;
    return wasAdded;
  }*/

  /*public boolean removeMedia(Media aMedia)
  {
    boolean wasRemoved = false;
    //Unable to remove aMedia, as it must always have a account
    if (!this.equals(aMedia.getAccount()))
    {
      medias.remove(aMedia);
      wasRemoved = true;
    }
    return wasRemoved;
  }*/
  /* Code from template association_AddIndexControlFunctions */
  /*public boolean addMediaAt(Media aMedia, int index)
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
  }*/

  /*public boolean addOrMoveMediaAt(Media aMedia, int index)
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
  }*/
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
      existingLibrarySystem.removeAccount(this);
    }
    librarySystem.addAccount(this);
    wasSet = true;
    return wasSet;
  }*/

  /*public void delete()
  {
    accountsById.remove(getId());
    for(int i=events.size(); i > 0; i--)
    {
      Event aEvent = events.get(i - 1);
      aEvent.delete();
    }
    for(int i=medias.size(); i > 0; i--)
    {
      Media aMedia = medias.get(i - 1);
      aMedia.delete();
    }
    LibrarySystem placeholderLibrarySystem = librarySystem;
    this.librarySystem = null;
    if(placeholderLibrarySystem != null)
    {
      placeholderLibrarySystem.removeAccount(this);
    }
  }*/


  /*public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "address" + ":" + getAddress()+ "," +
            "name" + ":" + getName()+ "," +
            "isLocal" + ":" + getIsLocal()+ "," +
            "numChecked" + ":" + getNumChecked()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "accountCategory" + "=" + (getAccountCategory() != null ? !getAccountCategory().equals(this)  ? getAccountCategory().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "librarySystem = "+(getLibrarySystem()!=null?Integer.toHexString(System.identityHashCode(getLibrarySystem())):"null");
  }*/
}