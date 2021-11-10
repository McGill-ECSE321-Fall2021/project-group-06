package ca.mcgill.ecse321.librarysystem.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.MediaRepository;
import ca.mcgill.ecse321.librarysystem.models.Media;
import ca.mcgill.ecse321.librarysystem.models.Media.Item;
import ca.mcgill.ecse321.librarysystem.models.CheckOutItem;

@Service
public class CheckOutItemService {

    @Autowired
    private MediaRepository mediaRepository;

    /**
     * 
     * @param mediaType
     * @param mediaID
     * @return media
     * @author Samuel
     */

    @Transactional
    public Media createCheckOutItem (Item mediaType, int mediaID, boolean isCheckedOut, boolean isReserved, int borrowingPeriod, Date startDate){

        // Input validation (methods from tutorial notes 2.6.1)
        String error ="";
        if(mediaID == 0){
            error = error + "Media Id cannot be 0";
        }
        if(mediaRepository.findMediaByID(mediaID)!=null){
            error = error + "Media Id already exists";
        }
        if (mediaType == null) {
            error = error + "Media type not found! (null)";
        }
        if (borrowingPeriod <=0){
            error = error + "borrowingPeriod must be more that 0";
        }
        if (startDate == null){
            error = error + "startDate cannot be null";
        }
        boolean isValidType = false;
        for (Item item: Item.values()){

            if (mediaType.equals(item)) {
                isValidType = true;
                break;
            }
        }
        if (!isValidType){
            error = error + "Media type invalid!";
        }

        if (mediaRepository.findMediaByID(mediaID) != null) {
            error = error + "Media ID already exists!";
        }
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }

        CheckOutItem checkOutItem = new CheckOutItem();
        checkOutItem.setType(mediaType);
        checkOutItem.setID(mediaID);
        checkOutItem.setBorrowingPeriod(borrowingPeriod);
        checkOutItem.setIsCheckedOut(isCheckedOut);
        checkOutItem.setIsReserved(isReserved);
        checkOutItem.setStartDate(startDate);
        mediaRepository.save(checkOutItem);
        return checkOutItem;
    }
    @Transactional
    public Media updateCheckOutItem (int mediaID, int newMediaID, boolean newIsCheckedOut, boolean newIsReserved, int newBorrowingPeriod, Date newStartDate){

        // Input validation (methods from tutorial notes 2.6.1)
        String error ="";
        if(mediaID == 0){
            error = error + "Media Id cannot be 0";
        }
        if(mediaRepository.findMediaByID(mediaID)==null){
            error = error + "Media Id does not exist";
        }
        if(newMediaID == 0){
            error = error + "Media Id cannot be 0";
        }
        if(mediaRepository.findMediaByID(newMediaID)!=null){
            error = error + "Media Id already exists";
        }
        if (newBorrowingPeriod <=0){
            error = error + "borrowingPeriod must be more that 0";
        }
        if (newStartDate == null){
            error = error + "startDate cannot be null";
        }
        if (mediaRepository.findMediaByID(mediaID) != null) {
            error = error + "Media ID already exists!";
        }
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }

        CheckOutItem checkOutItem = (CheckOutItem)mediaRepository.findMediaByID(mediaID);
        checkOutItem.setID(mediaID);
        checkOutItem.setBorrowingPeriod(newBorrowingPeriod);
        checkOutItem.setIsCheckedOut(newIsCheckedOut);
        checkOutItem.setIsReserved(newIsReserved);
        checkOutItem.setStartDate(newStartDate);
        mediaRepository.save(checkOutItem);
        return checkOutItem;
    }

}
