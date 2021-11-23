package ca.mcgill.ecse321.librarysystem.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ca.mcgill.ecse321.librarysystem.service.NonCheckOutItemService;
import ca.mcgill.ecse321.librarysystem.dto.NonCheckOutItemDto;
import ca.mcgill.ecse321.librarysystem.models.NonCheckOutItem;
import ca.mcgill.ecse321.librarysystem.models.Media.Item;

@CrossOrigin(origins = "*")
@RestController
public class NonCheckOutItemController {

	@Autowired
	private NonCheckOutItemService nonCheckOutItemService;
    /**
     * create nonCheckOutItemDto
     * @param mediaID
     * @param mediaType
     * @return noncheckOutItemDto
     * @author Howard Yu
     */
    @PostMapping (value = { "/create_nonCheckOutItems/{id}", "/create_nonCheckOutItems/{id}/" })
    public NonCheckOutItemDto createNonCheckOutItemDto(@PathVariable("id") int mediaID, @RequestParam Item mediaType, @RequestParam String name)
        throws IllegalArgumentException {
            NonCheckOutItem nonCheckOutItem = nonCheckOutItemService.createNonCheckOutItem(mediaType, mediaID, name);
            return Conversion.convertToDto(nonCheckOutItem);
    }
    /**
     * 
     * get nonCheckOutItemDto
     * @param mediaID
     * @return checkOutItemDto
     * @author Howard Yu
     */
    @GetMapping(value = { "/nonCheckOutItems/{id}", "/nonCheckOutItems/{id}/" })
    public NonCheckOutItemDto getNonCheckOutItemsById(@PathVariable("id") int mediaID) throws IllegalArgumentException {
        return Conversion.convertToDto(nonCheckOutItemService.getNonCheckOutItemByID(mediaID));
    }
    /**
     * 
     * get all nonCheckOutItemDto
     * @return checkOutItemDto
     * @author Howard Yu
     */
    @GetMapping(value = { "/nonCheckOutItems", "/nonCheckOutItems/" })
    public List<NonCheckOutItemDto> getAllNonCheckOutItems() {
        return nonCheckOutItemService.getAllNonCheckOutItems().stream().map(p -> Conversion.convertToDto(p)).collect(Collectors.toList());
    }

        /**
     * 
     * update nonCheckOutItemDto
     * @param mediaID
     * @param newMediaType
     * @return nonCheckOutItemDto
     * @author Howard Yu
     */
    @PutMapping(value = { "/edit_nonCheckOutItems/{id}"})
    public NonCheckOutItemDto updateNonCheckOutItem(@PathVariable("id") int mediaID, @RequestParam Item newMediaType)
            throws IllegalArgumentException {
                NonCheckOutItem nonCheckOutItem = nonCheckOutItemService.updateNonCheckOutItem(newMediaType, mediaID);
                return Conversion.convertToDto(nonCheckOutItem);
    }

        /**
     * 
     * delete nonCheckOutItemDto
     * @param mediaID
     * @param mediaType
     * @return nonCheckOutItemDto
     * @author Howard Yu
     */
    @DeleteMapping(value = {"/delete_nonCheckOutItem/{id}"})
    public void deleteNonCheckOutItem(@PathVariable("id") int id){
        nonCheckOutItemService.deleteNonCheckOutItem(id);
    }
}