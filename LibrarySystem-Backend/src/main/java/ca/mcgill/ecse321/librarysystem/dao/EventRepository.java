package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.models.Event;

public interface EventRepository extends CrudRepository<Event, String> {

	//Primary key for event
	Event findEventByName(String Name);



}