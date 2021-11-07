package ca.mcgill.ecse321.librarysystem.models;
import java.sql.Time;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Librarian extends Account
{
	//The Librarian class will have shifts and some methods we will write later.
	//Since it is an account, it inherits the ID as primary key.
  	private Set<Shift> shift; 
  
	//Each shift will have multiple librarians, just as each librarian will have multiple shifts
	@ManyToMany(cascade={CascadeType.ALL})
	  public Set<Shift> getShift()
	  {
	    return this.shift;
	  }
	  public void setShift(Set<Shift> shift) {
		  this.shift = shift;
	  }

}