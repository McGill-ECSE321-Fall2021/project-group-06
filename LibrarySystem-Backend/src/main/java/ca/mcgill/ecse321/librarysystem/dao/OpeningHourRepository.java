package ca.mcgill.ecse321.librarysystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.models.OpeningHour;
import ca.mcgill.ecse321.librarysystem.models.HeadLibrarian;

public interface OpeningHourRepository extends CrudRepository<OpeningHour, Integer> {

    //The primary key for opening hours
    OpeningHour findOpeningHourById(int id);

    
}