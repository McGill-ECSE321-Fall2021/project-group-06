package ca.mcgill.ecse321.librarysystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.models.Event;
import ca.mcgill.ecse321.librarysystem.models.Account;

public interface EventRepository extends CrudRepository<Event, String> {

	//Primary key for event
	Event findEventByName(String name);

	//Each Account has multiple events
	List<Event> findByAccount(Account accountId);

	//We can check if it exists within account.
	boolean existsByAccount(Account account);

}