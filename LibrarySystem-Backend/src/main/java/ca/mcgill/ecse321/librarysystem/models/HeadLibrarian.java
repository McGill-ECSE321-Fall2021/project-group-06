/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem.models;


import java.sql.Time;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;

import java.sql.Date;

// line 45 "model.ump"
// line 139 "model.ump"
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class HeadLibrarian extends Librarian
{

  //HeadLibrarian Associations
  private Set<OpeningHour> openingHour;
  private Set<Shift> shift;
  
  /* Code from template association_GetMany */
  @OneToMany(cascade={CascadeType.ALL})
  public Set<OpeningHour> getOpeningHour()
  {
    return this.openingHour;
  }
  public void setOpeningHour(Set<OpeningHour> openingHour) {
	  this.openingHour = openingHour;
  }
  
  @ManyToMany(cascade={CascadeType.ALL})
  public Set<Shift> getShifts()
  {
    return this.shift;
  }
  public void setShifts(Set<Shift> shift) {
	  this.shift = shift;
  }
}