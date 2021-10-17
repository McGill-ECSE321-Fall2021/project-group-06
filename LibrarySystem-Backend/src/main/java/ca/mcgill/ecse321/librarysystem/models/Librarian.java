/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem.models;


import java.sql.Time;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

// line 38 "model.ump"
// line 129 "model.ump"
@Entity
public class Librarian extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Librarian Attributes
  private Shift shift;
  //Librarian Associations
  private Set<Shift> shifts; 

  //------------------------
  // CONSTRUCTOR
  //------------------------

  // public Librarian(int aId, String aAddress, String aName, AccountCategory aAccountCategory, boolean aIsLocal, int aNumChecked, LibrarySystem aLibrarySystem, Time aStartShift, Time aEndShift)
  // {
  //   //super(aId, aAddress, aName, aAccountCategory, aIsLocal, aNumChecked, aLibrarySystem);
  //   startShift = aStartShift;
  //   endShift = aEndShift;
  // }

  //------------------------
  // INTERFACE
  //------------------------
  public void setShift(Shift aShift) {
	  shift = aShift;
  }

  @Id
  public Shift getShift() {
	  return shift;
  }
  @ManyToMany
  public Set<Shift> getShifts() {
	  return this.shifts;
  }


  // public void delete()
  // {
  //   //super.delete();
  // }


  // public String toString()
  // {
  //   return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
  //           "  " + "startShift" + "=" + (getStartShift() != null ? !getStartShift().equals(this)  ? getStartShift().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
  //           "  " + "endShift" + "=" + (getEndShift() != null ? !getEndShift().equals(this)  ? getEndShift().toString().replaceAll("  ","    ") : "this" : "null");
  // }
}