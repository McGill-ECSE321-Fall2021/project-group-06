/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.sql.Time;
import java.util.*;
import java.sql.Date;

// line 45 "model.ump"
// line 139 "model.ump"
public class HeadLibrarian extends Librarian
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HeadLibrarian Associations
  private OpeningHours openingHours;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HeadLibrarian(int aId, String aAddress, String aName, AccountType aAccountType, boolean aIsLocal, int aNumChecked, LibrarySystem aLibrarySystem, Time aStartShift, Time aEndShift, OpeningHours aOpeningHours)
  {
    super(aId, aAddress, aName, aAccountType, aIsLocal, aNumChecked, aLibrarySystem, aStartShift, aEndShift);
    if (aOpeningHours == null || aOpeningHours.getHeadLibrarian() != null)
    {
      throw new RuntimeException("Unable to create HeadLibrarian due to aOpeningHours. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    openingHours = aOpeningHours;
  }

  public HeadLibrarian(int aId, String aAddress, String aName, AccountType aAccountType, boolean aIsLocal, int aNumChecked, LibrarySystem aLibrarySystem, Time aStartShift, Time aEndShift, Date aDateForOpeningHours, Time aStartTimeForOpeningHours, Time aEndTimeForOpeningHours, LibrarySystem aLibrarySystemForOpeningHours)
  {
    super(aId, aAddress, aName, aAccountType, aIsLocal, aNumChecked, aLibrarySystem, aStartShift, aEndShift);
    openingHours = new OpeningHours(aDateForOpeningHours, aStartTimeForOpeningHours, aEndTimeForOpeningHours, aLibrarySystemForOpeningHours, this);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public OpeningHours getOpeningHours()
  {
    return openingHours;
  }

  public void delete()
  {
    OpeningHours existingOpeningHours = openingHours;
    openingHours = null;
    if (existingOpeningHours != null)
    {
      existingOpeningHours.delete();
    }
    super.delete();
  }

}