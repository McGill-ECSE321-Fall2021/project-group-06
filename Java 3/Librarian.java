/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.sql.Time;
import java.util.*;

// line 38 "model.ump"
// line 129 "model.ump"
public class Librarian extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Librarian Attributes
  private Time startShift;
  private Time endShift;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Librarian(int aId, String aAddress, String aName, AccountType aAccountType, boolean aIsLocal, int aNumChecked, LibrarySystem aLibrarySystem, Time aStartShift, Time aEndShift)
  {
    super(aId, aAddress, aName, aAccountType, aIsLocal, aNumChecked, aLibrarySystem);
    startShift = aStartShift;
    endShift = aEndShift;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartShift(Time aStartShift)
  {
    boolean wasSet = false;
    startShift = aStartShift;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndShift(Time aEndShift)
  {
    boolean wasSet = false;
    endShift = aEndShift;
    wasSet = true;
    return wasSet;
  }

  public Time getStartShift()
  {
    return startShift;
  }

  public Time getEndShift()
  {
    return endShift;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startShift" + "=" + (getStartShift() != null ? !getStartShift().equals(this)  ? getStartShift().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endShift" + "=" + (getEndShift() != null ? !getEndShift().equals(this)  ? getEndShift().toString().replaceAll("  ","    ") : "this" : "null");
  }
}