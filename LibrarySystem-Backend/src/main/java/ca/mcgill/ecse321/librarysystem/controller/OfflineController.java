package ca.mcgill.ecse321.librarysystem.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse321.librarysystem.dto.OfflineDto;
import ca.mcgill.ecse321.librarysystem.models.Offline;
import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;
import ca.mcgill.ecse321.librarysystem.service.OfflineService;

@CrossOrigin(origins="*")
@RestController
public class OfflineController {

    @Autowired
	private OfflineService offlineService;

        /**
     * 
     * get all offline
     * @return offline
     * @author Howard Yu, David
     */
    @GetMapping(value = {"/offlines", "/offlines/"})
    public List<OfflineDto> getAllOfflines(){
        return offlineService.getAllOfflines().stream().map(p -> Conversion.convertToDto(p)).collect(Collectors.toList());
    }
        /**
     * create offline
     * @param id
     * @param address
     * @param name
     * @param accountCategory
     * @param isLocal
     * @param numChecked
     * @return offline
     * @author Howard Yu, David
     */
    @PostMapping(value = {"/offlines/{id}", "/offlines/{id}/"})
    public OfflineDto createOffline(@PathVariable("id") int id, @RequestParam String address, @RequestParam String name, @RequestParam AccountCategory accountCategory, @RequestParam boolean isLocal, @RequestParam int numChecked){
        Offline offline = offlineService.createOffline(id, address, name, accountCategory, isLocal, numChecked);
        return (Conversion.convertToDto(offline));
    }
        /**
     * 
     * get offline
     * @param id
     * @return offline
     * @author Howard Yu, David
     */
    @GetMapping(value = { "/getOffline/{id}", "/getOffline/{id}/" })
    public OfflineDto getOfflineById(@PathVariable("id") int id) throws IllegalArgumentException {
        return Conversion.convertToDto(offlineService.getOffline(id));
    }

            /**
     * 
     * add media
     * @param id
     * @param mediaId
     * @return offline
     * @author Howard Yu, David
     */
    @PutMapping(value = {"/add_media_offline/{id}"})
    public OfflineDto addMediaOnline(@PathVariable("id") int id, @RequestParam int mediaId){
        Offline offline = offlineService.checkoutAnItem(mediaId, id);
        return (Conversion.convertToDto(offline));
    }
        /**
     * 
     * return media
     * @param id
     * @param mediaId
     * @return offline
     * @author Howard Yu, David
     */
    @PutMapping(value = {"/return_media_offline/{id}"})
    public OfflineDto returnMediaOnline(@PathVariable("id") int id, @RequestParam int mediaId){
        Offline offline = offlineService.returnAnItem(mediaId, id);
        return (Conversion.convertToDto(offline));
    }
        /**
     * edit offline
     * @param id
     * @param address
     * @param name
     * @param numChecked
     * @return offline
     * @author Howard Yu, David
     */
    @PutMapping(value = {"/edit_offline/{id}"})
    public OfflineDto updateOffline(@PathVariable("id") int id, @RequestParam String address, @RequestParam String name){
        Offline offline = offlineService.updateOffline(id, address, name);
        return (Conversion.convertToDto(offline));
    }
        /**
     * 
     * delete offline
     * @param id
     * @author Howard Yu, David
     */
    @DeleteMapping(value = {"/delete_offline/{id}"})
    public void deleteOffline(@PathVariable("id") int id){
        offlineService.deleteOffline(id);
    }

}
