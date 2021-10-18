/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem.models;


import java.util.*;

import javax.persistence.CascadeType;
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
//@Entity
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS) //Account has a table
//@MappedSuperclass (Account does not have a table)
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Account
{

//  //------------------------
//  // ENUMERATIONS
//  //------------------------
//
  public enum AccountCategory { Online, Offline }
//
//  //------------------------
//  // STATIC VARIABLES
//  //------------------------
//  
//
//  //private static Map<Integer, Account> accountsById = new HashMap<Integer, Account>();
//
//  //------------------------
//  // MEMBER VARIABLES
//  //------------------------
//
//  //Account Attributes
  private int id;
  private String address;
  private String name;
  private AccountCategory accountCategory;
  private boolean isLocal;
  private Set<Media> checkedOutItems;
  private int numChecked;
//
//  //Account Associations
  private Set<Event> events;
  private Set<Media> medias;
//  //private LibrarySystem librarySystem;
//
//  
//
//  ----------------------
//
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
//
  public boolean getIsLocal()
  {
    return isLocal;
  }

  public void setIsLocal(boolean aIsLocal)
  {
    this.isLocal = aIsLocal;
  }
//
//  public Set<Media> getCheckedOutItem()
//  {
//    return this.checkedOutItems;
//  }
//  public void setCheckedOutItem(Set<Media> checkedOutItems) {
//	  this.checkedOutItems = checkedOutItems;
//  }
//
  public int getNumChecked()
  {
    return numChecked;
  }
//
  public void setNumChecked(int aNumChecked)
  {
    this.numChecked = aNumChecked;
  }

  @OneToMany(cascade={CascadeType.ALL})
  public Set<Event> getEvents(){
    return this.events;
  }

  public void setEvents(Set<Event> events){
    this.events=events;
  }

  @OneToMany(cascade={CascadeType.ALL})
  public Set<Media> getMedias(){
    return this.medias;
  }
  public void setMedias(Set<Media> medias){
    this.medias=medias;
  }

//  
//  
//
//  
//
//  
//
//  
//
//  
//
//  
//
// 
//
// 
//
// 
//  
//
//  
//
//
//  
}