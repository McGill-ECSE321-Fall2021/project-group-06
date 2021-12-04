package ca.mcgill.ecse321.librarysystem.models;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;



@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Account
{
  public enum AccountCategory { Online, Offline, Librarian, HeadLibrarian }

  //All the Private Attributes to Account, ID is the primary key
  private int id;
  private String address;
  private String name;
  private AccountCategory accountCategory;
  private boolean isLocal;
  //This is not necessary, but may facilitate readability later
  private int numChecked;

  //Account Associations
  private Set<Event> events;
  private Set<Media> medias;

  //Identify all accounts with an ID
  public void setId(int id){
    this.id = id;
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

  public int getNumChecked()
  {
    return numChecked;
  }

  public void setNumChecked(int aNumChecked)
  {
    this.numChecked = aNumChecked;
  }

  //Associations
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
}