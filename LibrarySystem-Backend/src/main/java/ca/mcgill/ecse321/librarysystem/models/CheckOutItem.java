package ca.mcgill.ecse321.librarysystem.models;

import java.sql.Date;

import javax.persistence.Entity;

@Entity
public class CheckOutItem extends Media
{
  //The checkoutitem class is simply one of the two possible medias.
  //It inherits the mediaID as a primary key.

  //CheckOutItem Attributes
  private boolean isCheckedOut;
  private boolean isReserved;
  private int borrowingPeriod;
  private Date startDate;
  private int userReserving;

  public void setIsCheckedOut(boolean aIsCheckedOut)
  {
    this.isCheckedOut = aIsCheckedOut;
  }

  public void setStartDate(Date startDate){
    this.startDate = startDate;
  }

  public void setIsReserved(boolean aIsReserved)
  {
    this.isReserved = aIsReserved;
  }

  //Some of the setters will have this format.
  //This is because we initially generate the code.
  //We tested and the setters work even with this syntax.
  public boolean setBorrowingPeriod(int aBorrowingPeriod)
  {
    boolean wasSet = false;
    borrowingPeriod = aBorrowingPeriod;
    wasSet = true;
    return wasSet;
  }

  public boolean getIsCheckedOut()
  {
    return isCheckedOut;
  }

  public int getUserReserving(){
    return this.userReserving;
  }

  public void setUserReserving(int userID){
    this.userReserving = userID;
  }

  public boolean getIsReserved()
  {
    return isReserved;
  }

  public int getBorrowingPeriod()
  {
    return borrowingPeriod;
  }

  public Date getStartDate(){
    return this.startDate;
  }
}