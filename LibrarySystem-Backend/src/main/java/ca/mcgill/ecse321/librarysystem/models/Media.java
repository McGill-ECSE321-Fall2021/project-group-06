/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

// line 66 "model.ump"
// line 145 "model.ump"
@Entity
public abstract class Media
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Item { Book, Movie, Music, Newspaper, Archive }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Media Attributes
  private Item mediaType;
  private int mediaID;

  //Media Associations
  // private LibrarySystem librarySystem;
  private Account account;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  /*public Media(Item aMediaType, int aMediaID, LibrarySystem aLibrarySystem, Account aAccount)
  {
    mediaType = aMediaType;
    mediaID = aMediaID;
    boolean didAddLibrarySystem = setLibrarySystem(aLibrarySystem);
    if (!didAddLibrarySystem)
    {
      throw new RuntimeException("Unable to create media due to librarySystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddAccount = setAccount(aAccount);
    if (!didAddAccount)
    {
      throw new RuntimeException("Unable to create media due to account. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }*/

  //------------------------
  // INTERFACE
  //------------------------

  public void setMediaType(Item aMediaType)
  {
    this.mediaType = aMediaType;
  }

  public void setMediaID(int aMediaID)
  {
    this.mediaID = aMediaID;
  }

  public Item getMediaType()
  {
    return this.mediaType;
  }
  @Id
  public int getMediaID()
  {
    return this.mediaID;
  }
  /* Code from template association_GetOne */
  /*public LibrarySystem getLibrarySystem()
  {
    return librarySystem;
  }*/
  /* Code from template association_GetOne */
  @ManyToOne
  public Account getAccount()
  {
    return this.account;
  }

  public void setAccount(Account account){
    this.account=account;
  }
  /* Code from template association_SetOneToMany */

  /*public boolean setLibrarySystem(LibrarySystem aLibrarySystem)
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
      existingLibrarySystem.removeMedia(this);
    }
    librarySystem.addMedia(this);
    wasSet = true;
    return wasSet;
  }*/
  /* Code from template association_SetOneToAtMostN */
  
  /*public boolean setAccount(Account aAccount)
  {
    boolean wasSet = false;
    //Must provide account to media
    if (aAccount == null)
    {
      return wasSet;
    }

    //account already at maximum (5)
    if (aAccount.numberOfMedias() >= Account.maximumNumberOfMedias())
    {
      return wasSet;
    }
    
    Account existingAccount = account;
    account = aAccount;
    if (existingAccount != null && !existingAccount.equals(aAccount))
    {
      boolean didRemove = existingAccount.removeMedia(this);
      if (!didRemove)
      {
        account = existingAccount;
        return wasSet;
      }
    }
    account.addMedia(this);
    wasSet = true;
    return wasSet;
  }*/

  /*public void delete()
  {
    LibrarySystem placeholderLibrarySystem = librarySystem;
    this.librarySystem = null;
    if(placeholderLibrarySystem != null)
    {
      placeholderLibrarySystem.removeMedia(this);
    }
    Account placeholderAccount = account;
    this.account = null;
    if(placeholderAccount != null)
    {
      placeholderAccount.removeMedia(this);
    }
  }*/


  /*public String toString()
  {
    return super.toString() + "["+
            "mediaID" + ":" + getMediaID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "mediaType" + "=" + (getMediaType() != null ? !getMediaType().equals(this)  ? getMediaType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "librarySystem = "+(getLibrarySystem()!=null?Integer.toHexString(System.identityHashCode(getLibrarySystem())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "account = "+(getAccount()!=null?Integer.toHexString(System.identityHashCode(getAccount())):"null");
  }*/
}