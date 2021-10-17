/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem.models;


import java.sql.Time;
import java.util.*;

import javax.persistence.Entity;

import java.sql.Date;

// line 45 "model.ump"
// line 139 "model.ump"
@Entity
public class HeadLibrarian extends Librarian
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HeadLibrarian Associations
  private OpeningHour openingHour;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  // public HeadLibrarian(int aId, String aAddress, String aName, AccountCategory aAccountCategory, boolean aIsLocal, int aNumChecked, LibrarySystem aLibrarySystem, Time aStartShift, Time aEndShift, OpeningHour aOpeningHour)
  // {
  //   super(aId, aAddress, aName, aAccountCategory, aIsLocal, aNumChecked, aLibrarySystem, aStartShift, aEndShift);
  //   if (aOpeningHour == null || aOpeningHour.getHeadLibrarian() != null)
  //   {
  //     throw new RuntimeException("Unable to create HeadLibrarian due to aOpeningHours. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
  //   }
  //   openingHour = aOpeningHour;
  // }

  // public HeadLibrarian(int aId, String aAddress, String aName, AccountCategory aAccountCategory, boolean aIsLocal, int aNumChecked, LibrarySystem aLibrarySystem, Time aStartShift, Time aEndShift, Date aDateForOpeningHours, Time aStartTimeForOpeningHours, Time aEndTimeForOpeningHours, LibrarySystem aLibrarySystemForOpeningHours)
  // {
  //   super(aId, aAddress, aName, aAccountCategory, aIsLocal, aNumChecked, aLibrarySystem, aStartShift, aEndShift);
  //   openingHour = new OpeningHour(aDateForOpeningHours, aStartTimeForOpeningHours, aEndTimeForOpeningHours, aLibrarySystemForOpeningHours, this);
  // }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public OpeningHour getOpeningHour()
  {
    return openingHour;
  }

  // public void delete()
  // {
  //   OpeningHour existingOpeningHours = openingHour;
  //   openingHour = null;
  //   if (existingOpeningHours != null)
  //   {
  //     existingOpeningHours.delete();
  //   }
  //   super.delete();
  // }

}