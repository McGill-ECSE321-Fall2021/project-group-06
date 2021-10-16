package ca.mcgill.ecse321.librarysystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.models.Event;
import ca.mcgill.ecse321.librarysystem.models.Account;

public interface EventRepository extends CrudRepository<Event, String> {

	Event findEventByName(String name);
	List<Event> findByAccount (Account account); 

}