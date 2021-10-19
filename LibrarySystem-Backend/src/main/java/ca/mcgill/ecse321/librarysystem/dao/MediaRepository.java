package ca.mcgill.ecse321.librarysystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.models.Media;
import ca.mcgill.ecse321.librarysystem.models.Account;

public interface MediaRepository extends CrudRepository<Media, Integer> {

    //Primary Key for Media
	Media findMediaByID(int ID);

    //We can find a list of media within each accounts.
    List<Media> findByAccount(Account accountId);

}