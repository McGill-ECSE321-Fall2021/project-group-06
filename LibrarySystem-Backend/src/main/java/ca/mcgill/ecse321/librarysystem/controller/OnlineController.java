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
import ca.mcgill.ecse321.librarysystem.dto.OnlineDto;
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
import ca.mcgill.ecse321.librarysystem.service.OnlineService;
import ca.mcgill.ecse321.librarysystem.service.OpeningHourService;
import ca.mcgill.ecse321.librarysystem.service.ShiftService;
//author David Hu
@CrossOrigin(origins="*")
@RestController
public class OnlineController {

    @Autowired
    private OnlineService onlineService;

    @GetMapping(value = {"/onlines", "/onlines/"})
    public List<OnlineDto> getAllOnlines(){
        return onlineService.getAllOnlines().stream().map(p -> Conversion.convertToDto(p)).collect(Collectors.toList());
    }

    @PostMapping(value = {"/onlines/{id}", "/onlines/{id}/"})
    public OnlineDto createOnline(@PathVariable("id") int id, @RequestParam String address, @RequestParam String name, @RequestParam AccountCategory accountCategory, @RequestParam boolean isLocal, @RequestParam int numChecked, @RequestParam String username, @RequestParam String password, @RequestParam String email){
        Online online = onlineService.createOnline(id, address, name, accountCategory, isLocal, numChecked, username, password, email);
        return (Conversion.convertToDto(online));
    }

    @PutMapping(value = {"/edit_online/{id}"})
    public OnlineDto updateOnline(@PathVariable("id") int id, @RequestParam String address, @RequestParam String name, @RequestParam int numChecked, @RequestParam String username, @RequestParam String password, @RequestParam String email){
        Online online = onlineService.updateOnline(id, address, name, numChecked, username, password, email);
        return (Conversion.convertToDto(online));
    }

    @PutMapping(value = {"/add_media_online/{id}"})
    public OnlineDto addMediaOnline(@PathVariable("id") int id, @RequestParam int mediaId){
        Online online = onlineService.checkoutAnItem(mediaId, id);
        return (Conversion.convertToDto(online));
    }

    @PutMapping(value = {"/return_media_online/{id}"})
    public OnlineDto returnMediaOnline(@PathVariable("id") int id, @RequestParam int mediaId){
        Online online = onlineService.returnAnItem(mediaId, id);
        return (Conversion.convertToDto(online));
    }

    @GetMapping(value = { "/getOnline/{id}", "/getOnline/{id}/" })
    public OnlineDto getOfflineById(@PathVariable("id") int id) throws IllegalArgumentException {
        return Conversion.convertToDto(onlineService.getOnline(id));
    }

    @DeleteMapping(value = {"/delete_online/{id}"})
    public void deleteOffline(@PathVariable("id") int id){
        onlineService.deleteOnline(id);
    }
    
}
