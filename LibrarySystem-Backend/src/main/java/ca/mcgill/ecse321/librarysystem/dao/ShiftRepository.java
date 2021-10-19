package ca.mcgill.ecse321.librarysystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.models.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem.models.Librarian;
import ca.mcgill.ecse321.librarysystem.models.Shift;

public interface ShiftRepository extends CrudRepository<Shift, Integer> {

    //The primary key for shift.
    Shift findShiftByShiftID(int shiftID);

    //Multiple per HL
    List<Shift> findByHeadLibrarian(HeadLibrarian headLibrarianName);
    //Multiple per Librarian
    List<Shift> findByLibrarian(Librarian librarianName);
    //We can check if they exist by HL
    boolean existsByHeadLibrarian(HeadLibrarian headLibrarianName);
    //Same with librarian.
    boolean existsByLibrarian(Librarian librarianName);
}