package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.models.Media;

public interface MediaRepository extends CrudRepository<Media, Integer> {

    //Primary Key for Media
	Media findMediaByID(int ID);

}