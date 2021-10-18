/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem.models;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

// line 66 "model.ump"
// line 145 "model.ump"
//@Entity
//
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Media
{

//  //------------------------
//  // ENUMERATIONS
//  //------------------------
//
public enum Item { Book, Movie, Music, Newspaper, Archive }
//
//  //------------------------
//  // MEMBER VARIABLES
//  //------------------------
//
//  //Media Attributes
  private Item mediaType;
  private int mediaID;
//
//  //Media Associations
//  // private LibrarySystem librarySystem;
  private Account account;
//
  
//
  public void setID(int aMediaID)
  {
    this.mediaID = aMediaID;
  }
@Enumerated
  public Item getType()
  {
    return this.mediaType;
  }
	public void setType(Item aMediaType)
	{
		this.mediaType = aMediaType;
	}
  @Id
  public int getID()
  {
    return this.mediaID;
  }

  @ManyToOne(optional=true)
  public Account getAccount()
  {
    return this.account;
  }

  public void setAccount(Account account){
    this.account=account;
  }

}