package ca.mcgill.ecse321.librarysystem.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse321.librarysystem.dto.MediaDto;
import ca.mcgill.ecse321.librarysystem.dto.OnlineDto;
import ca.mcgill.ecse321.librarysystem.models.CheckOutItem;
import ca.mcgill.ecse321.librarysystem.models.Online;
import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;
import ca.mcgill.ecse321.librarysystem.service.OnlineService;
@CrossOrigin(origins="*")
@RestController
public class OnlineController {

    @Autowired
    private OnlineService onlineService;
        /**
     * 
     * get all online
     * @return online
     * @author Howard Yu, David
     */
    @GetMapping(value = {"/onlines", "/onlines/"})
    public List<OnlineDto> getAllOnlines(){
        return onlineService.getAllOnlines().stream().map(p -> Conversion.convertToDto(p)).collect(Collectors.toList());
    }
        /**
     * 
     * create online
     * @param id
     * @param address
     * @param name
     * @param accountCategory
     * @param isLocal
     * @param numChecked
     * @param username
     * @param password
     * @param email
     * @return online
     * @author Howard Yu, David
     */
    @PostMapping(value = {"/onlines/{id}", "/onlines/{id}/"})
    public OnlineDto createOnline(@PathVariable("id") int id, @RequestParam String address, @RequestParam String name, @RequestParam AccountCategory accountCategory, @RequestParam boolean isLocal, @RequestParam int numChecked, @RequestParam String username, @RequestParam String password, @RequestParam String email){
        Online online = onlineService.createOnline(id, address, name, accountCategory, isLocal, numChecked, username, password, email);
        return (Conversion.convertToDto(online));
    }
        /**
     * 
     * update online
     * @param id
     * @param address
     * @param name
     * @param accountCategory
     * @param isLocal
     * @param numChecked
     * @param username
     * @param password
     * @param email
     * @return online
     * @author Howard Yu, David
     */
    @PutMapping(value = {"/edit_online/{id}"})
    public OnlineDto updateOnline(@PathVariable("id") int id, @RequestParam String address, @RequestParam String name, @RequestParam String password){
        Online online = onlineService.updateOnline(id, address, name, password);
        return (Conversion.convertToDto(online));
    }
        /**
         * 
     * add media
     * @param id
     * @param mediaId
     * @return online
     * @author Howard Yu, David
     */
    @PutMapping(value = {"/add_media_online/{id}"})
    public OnlineDto addMediaOnline(@PathVariable("id") int id, @RequestParam int mediaId){
        Online online = onlineService.checkoutAnItem(mediaId, id);
        return (Conversion.convertToDto(online));
    }
        /**
     * 
     * return media
     * @param id
     * @param mediaId
     * @return online
     * @author Howard Yu, David
     */
    @PutMapping(value = {"/return_media_online/{id}"})
    public OnlineDto returnMediaOnline(@PathVariable("id") int id, @RequestParam int mediaId){
        Online online = onlineService.returnAnItem(mediaId, id);
        return (Conversion.convertToDto(online));
    }

    @PutMapping(value = {"/reserve_media_online/{id}"})
    public MediaDto reserveMediaOnline(@PathVariable("id") int id, @RequestParam int userId){
        CheckOutItem media = (CheckOutItem) onlineService.reserveAnItem(id, userId);
        return (Conversion.convertToDto(media));
    }
        /**
     * 
     * get online
     * @param id
     * @return online
     * @author Howard Yu, David
     */
    @GetMapping(value = { "/getOnline/{id}", "/getOnline/{id}/" })
    public OnlineDto getOnlineById(@PathVariable("id") int id) throws IllegalArgumentException {
        return Conversion.convertToDto(onlineService.getOnline(id));
    }
        /**
     * 
     * delete online
     * @param id
     * @return online
     * @author Howard Yu, David
     */
    @DeleteMapping(value = {"/delete_online/{id}"})
    public void deleteOnline(@PathVariable("id") int id){
        onlineService.deleteOnline(id);
    }
    
}
