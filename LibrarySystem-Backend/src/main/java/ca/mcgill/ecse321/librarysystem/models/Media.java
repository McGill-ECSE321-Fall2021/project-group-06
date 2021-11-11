package ca.mcgill.ecse321.librarysystem.models;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Media
{

public enum Item { Book, Movie, Music, Newspaper, Archive }

  //Media Attributes
  private Item mediaType;
  private int mediaID;
  //NEEDS DATE OF CHECKOUT, HOWARD!

  //Media Associations
  //private Account account;

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

  //Media uses ID as primary key. It is the only one that is unique.
  @Id
  public int getID()
  {
    return this.mediaID;
  }

  //Each media does not require to be borrowed to be created
  // @ManyToOne(optional=true)
  // public Account getAccount()
  // {
  //   return this.account;
  // }

  // public void setAccount(Account account){
  //   this.account=account;
  // }

}