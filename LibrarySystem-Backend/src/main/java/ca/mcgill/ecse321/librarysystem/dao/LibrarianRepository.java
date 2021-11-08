package ca.mcgill.ecse321.librarysystem.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.librarysystem.models.Librarian;
@Repository
public interface LibrarianRepository extends CrudRepository<Librarian, Integer> {

	//This is simply the Primary Key for Account
	Librarian findLibrarianById(int ID);

}