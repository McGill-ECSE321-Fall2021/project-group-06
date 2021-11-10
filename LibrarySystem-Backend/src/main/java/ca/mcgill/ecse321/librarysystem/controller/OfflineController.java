package ca.mcgill.ecse321.librarysystem.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.dto.AccountDto;
import ca.mcgill.ecse321.librarysystem.dto.HeadLibrarianDto;
import ca.mcgill.ecse321.librarysystem.dto.LibrarianDto;
import ca.mcgill.ecse321.librarysystem.dto.OfflineDto;
import ca.mcgill.ecse321.librarysystem.dto.OpeningHourDto;
import ca.mcgill.ecse321.librarysystem.dto.ShiftDto;
import ca.mcgill.ecse321.librarysystem.models.Account;
import ca.mcgill.ecse321.librarysystem.models.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem.models.Librarian;
import ca.mcgill.ecse321.librarysystem.models.Offline;
import ca.mcgill.ecse321.librarysystem.models.Online;
import ca.mcgill.ecse321.librarysystem.models.OpeningHour;
import ca.mcgill.ecse321.librarysystem.models.Shift;
import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;
import ca.mcgill.ecse321.librarysystem.service.AccountService;
import ca.mcgill.ecse321.librarysystem.service.HeadLibrarianService;
import ca.mcgill.ecse321.librarysystem.service.OfflineService;
import ca.mcgill.ecse321.librarysystem.service.OpeningHourService;
import ca.mcgill.ecse321.librarysystem.service.ShiftService;
//author David Hu
@CrossOrigin(origins="*")
@RestController
public class OfflineController {

    @Autowired
	private OfflineService offlineService;

    @PostMapping(value = {"/offines/{id}", "/offlines/{id}/"})
    public OfflineDto createOffline(@PathVariable("id") int id, @RequestParam String address, @RequestParam String name, @RequestParam AccountCategory accountCategory, @RequestParam boolean isLocal, @RequestParam int numChecked){
        Offline offline = offlineService.createOffline(id, address, name, accountCategory, isLocal, numChecked);
        return (Conversion.convertToDto(offline));
    }

}
