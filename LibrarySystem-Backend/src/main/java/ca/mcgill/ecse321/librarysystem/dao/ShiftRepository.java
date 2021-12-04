package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.librarysystem.models.Shift;

public interface ShiftRepository extends CrudRepository<Shift, Integer> {

    //The primary key for shift.
    Shift findShiftByShiftID(int shiftID);

    
}