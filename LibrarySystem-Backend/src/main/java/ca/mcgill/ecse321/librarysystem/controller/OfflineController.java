package ca.mcgill.ecse321.librarysystem.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @GetMapping(value = {"/offlines", "/offlines/"})
    public List<OfflineDto> getAllOfflines(){
        return offlineService.getAllOfflines().stream().map(p -> Conversion.convertToDto(p)).collect(Collectors.toList());
    }

    @PostMapping(value = {"/offlines/{id}", "/offlines/{id}/"})
    public OfflineDto createOffline(@PathVariable("id") int id, @RequestParam String address, @RequestParam String name, @RequestParam AccountCategory accountCategory, @RequestParam boolean isLocal, @RequestParam int numChecked){
        Offline offline = offlineService.createOffline(id, address, name, accountCategory, isLocal, numChecked);
        return (Conversion.convertToDto(offline));
    }

    @GetMapping(value = { "/getOffline/{id}", "/getOffline/{id}/" })
    public OfflineDto getOfflineById(@PathVariable("id") int id) throws IllegalArgumentException {
        return Conversion.convertToDto(offlineService.getOffline(id));
    }

    @PutMapping(value = {"/add_media_offline/{id}"})
    public OfflineDto addMediaOnline(@PathVariable("id") int id, @RequestParam int mediaId){
        Offline offline = offlineService.checkoutAnItem(mediaId, id);
        return (Conversion.convertToDto(offline));
    }

    @PutMapping(value = {"/edit_offline/{id}"})
    public OfflineDto updateOffline(@PathVariable("id") int id, @RequestParam String address, @RequestParam String name, @RequestParam int numChecked){
        Offline offline = offlineService.updateOffline(id, address, name, numChecked);
        return (Conversion.convertToDto(offline));
    }

    @DeleteMapping(value = {"/delete_offline/{id}"})
    public void deleteOffline(@PathVariable("id") int id){
        offlineService.deleteOffline(id);
    }

}
