package ca.mcgill.ecse321.librarysystem.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CheckOutItem extends Media
{
  //The checkoutitem class is simply one of the two possible medias.
  //It inherits the mediaID as a primary key.

  //CheckOutItem Attributes
  private boolean isCheckedOut;
  private boolean isReserved;
  private int borrowingPeriod;

  public void setIsCheckedOut(boolean aIsCheckedOut)
  {
    boolean wasSet = false;
    isCheckedOut = aIsCheckedOut;
    wasSet = true;
  }

  public void setIsReserved(boolean aIsReserved)
  {
    boolean wasSet = false;
    isReserved = aIsReserved;
    wasSet = true;
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

  public boolean getIsReserved()
  {
    return isReserved;
  }

  public int getBorrowingPeriod()
  {
    return borrowingPeriod;
  }
}