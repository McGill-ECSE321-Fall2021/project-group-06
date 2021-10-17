package ca.mcgill.ecse321.librarysystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import ca.mcgill.ecse321.librarysystem.models.Account;
import ca.mcgill.ecse321.librarysystem.models.Event;
import ca.mcgill.ecse321.librarysystem.models.Media;

public interface AccountRepository extends CrudRepository<Account, Integer> {

	Account findAccountById(int id);

	List<Account> findByMedia(Media mediaId);

	boolean existsByEvent(Event event);
}