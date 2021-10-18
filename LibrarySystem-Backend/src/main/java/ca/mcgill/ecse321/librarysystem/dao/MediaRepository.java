package ca.mcgill.ecse321.librarysystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.models.Media;
import ca.mcgill.ecse321.librarysystem.models.Account;

public interface MediaRepository extends CrudRepository<Media, Integer> {

	Media findMediaByID(int ID);

    //List<Account> findByMedia(Media mediaId);

}