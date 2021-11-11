package ca.mcgill.ecse321.librarysystem.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    @PostMapping (value = { "/create_nonCheckOutItems/{id}", "/create_nonCheckOutItems/{id}/" })
    public NonCheckOutItemDto createCheckOutItemDto(@PathVariable("id") int mediaID, @RequestParam Item mediaType)
        throws IllegalArgumentException {
            NonCheckOutItem nonCheckOutItem = nonCheckOutItemService.createNonCheckOutItem(mediaType, mediaID);
            return Conversion.convertToDto(nonCheckOutItem);
    }

    @GetMapping(value = { "/nonCheckOutItems/{id}", "/nonCheckOutItems/{id}/" })
    public NonCheckOutItemDto getNonCheckOutItemsById(@PathVariable("id") int mediaID) throws IllegalArgumentException {
        return Conversion.convertToDto(nonCheckOutItemService.getNonCheckOutItemByID(mediaID));
    }

    @GetMapping(value = { "/nonCheckOutItems", "/nonCheckOutItems/" })
    public List<NonCheckOutItemDto> getAllNonCheckOutItems() {
        return nonCheckOutItemService.getAllNonCheckOutItems().stream().map(p -> Conversion.convertToDto(p)).collect(Collectors.toList());
    }
    @PutMapping(value = { "/edit_nonCheckOutItems/{id}"})
    public NonCheckOutItemDto updateNonCheckOutItem(@PathVariable("id") int mediaID, @RequestParam Item newMediaType)
            throws IllegalArgumentException {
                NonCheckOutItem nonCheckOutItem = nonCheckOutItemService.updateNonCheckOutItem(newMediaType, mediaID);
                return Conversion.convertToDto(nonCheckOutItem);
    }
    @DeleteMapping(value = {"/delete_nonCheckOutItem/{id}"})
    public void deleteNonCheckOutItem(@PathVariable("id") int id){
        nonCheckOutItemService.deleteNonCheckOutItem(id);
    }
}