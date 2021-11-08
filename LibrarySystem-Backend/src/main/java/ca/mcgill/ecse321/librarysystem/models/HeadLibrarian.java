package ca.mcgill.ecse321.librarysystem.models;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class HeadLibrarian extends Librarian
{

  //HeadLibrarian Associations
  private Set<OpeningHour> openingHour;
  // private Set<Shift> shift;
  
  //A headlibrarian will create multiple openings for the library.
  @OneToMany(cascade={CascadeType.ALL})
  public Set<OpeningHour> getOpeningHour()
  {
    return this.openingHour;
  }
  public void setOpeningHour(Set<OpeningHour> openingHour) {
	  this.openingHour = openingHour;
  }
  
  // //The head librarian will create multiple shifts for the librarians. But only
  // //one Head Librarian can create 1 shift, so no co-authors on shift.
  // @OneToMany(cascade={CascadeType.ALL})
  // public Set<Shift> getShifts()
  // {
  //   return this.shift;
  // }
  // public void setShifts(Set<Shift> shift) {
	//   this.shift = shift;
  // }
}