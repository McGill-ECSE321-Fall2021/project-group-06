/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem.models;


import java.sql.Time;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

// line 38 "model.ump"
// line 129 "model.ump"
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Entity
public class Librarian extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Librarian Attributes
  //private Shift shift;
  //Librarian Associations
  private Set<Shift> shift; 

//  public void setShift(Shift aShift) {
//	  shift = aShift;
//  }

//  @Id
//  public Shift getShift() {
//	  return shift;
//  }
  
	@ManyToMany(cascade={CascadeType.ALL})
	  public Set<Shift> getShift()
	  {
	    return this.shift;
	  }
	  public void setShift(Set<Shift> shift) {
		  this.shift = shift;
	  }

}