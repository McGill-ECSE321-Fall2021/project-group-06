package ca.mcgill.ecse321.librarysystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.models.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem.models.Librarian;
import ca.mcgill.ecse321.librarysystem.models.Shift;

public interface ShiftRepository extends CrudRepository<Shift, Integer> {

    Shift findShiftByShiftID(int shiftID);

    List<Shift> findByHeadLibrarian(HeadLibrarian headLibrarianName);

    List<Shift> findByLibrarian(Librarian librarianName);

    boolean existsByHeadLibrarian(HeadLibrarian headLibrarianName);
}