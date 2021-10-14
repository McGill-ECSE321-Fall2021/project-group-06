/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 73 "model.ump"
// line 150 "model.ump"
public class CheckOutItem extends Media
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CheckOutItem Attributes
  private boolean isCheckedOut;
  private boolean isReserved;
  private int borrowingPeriod;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CheckOutItem(Item aMediaType, int aMediaID, LibrarySystem aLibrarySystem, Account aAccount, boolean aIsCheckedOut, boolean aIsReserved, int aBorrowingPeriod)
  {
    super(aMediaType, aMediaID, aLibrarySystem, aAccount);
    isCheckedOut = aIsCheckedOut;
    isReserved = aIsReserved;
    borrowingPeriod = aBorrowingPeriod;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setIsCheckedOut(boolean aIsCheckedOut)
  {
    boolean wasSet = false;
    isCheckedOut = aIsCheckedOut;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsReserved(boolean aIsReserved)
  {
    boolean wasSet = false;
    isReserved = aIsReserved;
    wasSet = true;
    return wasSet;
  }

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
  /* Code from template attribute_IsBoolean */
  public boolean isIsCheckedOut()
  {
    return isCheckedOut;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsReserved()
  {
    return isReserved;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "isCheckedOut" + ":" + getIsCheckedOut()+ "," +
            "isReserved" + ":" + getIsReserved()+ "," +
            "borrowingPeriod" + ":" + getBorrowingPeriod()+ "]";
  }
}