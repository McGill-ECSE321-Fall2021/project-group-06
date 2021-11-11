package ca.mcgill.ecse321.librarysystem.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse321.librarysystem.service.CheckOutItemService;
import ca.mcgill.ecse321.librarysystem.service.EventService;
import ca.mcgill.ecse321.librarysystem.dto.CheckOutItemDto;
import ca.mcgill.ecse321.librarysystem.dto.EventDto;
import ca.mcgill.ecse321.librarysystem.models.CheckOutItem;
import ca.mcgill.ecse321.librarysystem.models.Event;
import ca.mcgill.ecse321.librarysystem.models.Media.Item;

@CrossOrigin(origins = "*")
@RestController
public class CheckOutItemController {

	@Autowired
	private CheckOutItemService checkOutItemService;

    @PostMapping (value = { "/create_checkOutItems/{id}", "/create_checkOutItems/{id}/" })
    public CheckOutItemDto createCheckOutItemDto(@PathVariable("id") int mediaID, @RequestParam Item mediaType, @RequestParam boolean isCheckedOut, @RequestParam boolean isReserved, @RequestParam int borrowingPeriod, @RequestParam String startDateString)
        throws IllegalArgumentException {
            Date startDate = Date.valueOf(startDateString);
            CheckOutItem checkOutItem = checkOutItemService.createCheckOutItem(mediaType, mediaID, isCheckedOut, isReserved, borrowingPeriod, startDate);
            return Conversion.convertToDto(checkOutItem);
    }

    @GetMapping(value = { "/checkOutItems/{id}", "/checkOutItems/{id}/" })
    public CheckOutItemDto getCheckOutItemsById(@PathVariable("id") int mediaID) throws IllegalArgumentException {
        return Conversion.convertToDto(checkOutItemService.getCheckOutItemByID(mediaID));
    }

    @GetMapping(value = { "/checkOutItems", "/checkOutItems/" })
    public List<CheckOutItemDto> getAllCheckOutItems() {
        return checkOutItemService.getAllCheckOutItems().stream().map(p -> Conversion.convertToDto(p)).collect(Collectors.toList());

    }
    @PutMapping(value = { "/edit_checkOutItems/{id}"})
    public CheckOutItemDto updateCheckOutItem(@PathVariable("id") int mediaID, @RequestParam Item newMediaType, @RequestParam boolean newIsCheckedOut,
    @RequestParam boolean newIsReserved, @RequestParam int newBorrowingPeriod,@RequestParam String date)
            throws IllegalArgumentException {
                Date newStartDate = Date.valueOf(date);
                CheckOutItem checkOutItem = checkOutItemService.updateCheckOutItem(mediaID, newMediaType, newIsCheckedOut, newIsReserved, newBorrowingPeriod, newStartDate);
                return Conversion.convertToDto(checkOutItem);
    }
    @DeleteMapping(value = {"/delete_CheckOutItem/{id}"})
    public void deleteCheckOutItem(@PathVariable("id") int id){
        checkOutItemService.deleteCheckOutItem(id);
    }
}