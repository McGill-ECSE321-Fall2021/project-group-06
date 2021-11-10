package ca.mcgill.ecse321.librarysystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.MediaRepository;
import ca.mcgill.ecse321.librarysystem.models.Media.Item;
import ca.mcgill.ecse321.librarysystem.models.NonCheckOutItem;

@Service
public class NonCheckOutItemService {

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
    public NonCheckOutItem createNonCheckOutItem (Item mediaType, int mediaID){

        // Input validation (methods from tutorial notes 2.6.1)
        String error ="";

        if (mediaType == null) {
            error = error + "Media type not found! (null)";
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

        NonCheckOutItem nonCheckOutItem = new NonCheckOutItem();
        nonCheckOutItem.setType(mediaType);
        nonCheckOutItem.setID(mediaID);
        mediaRepository.save(nonCheckOutItem);
        return nonCheckOutItem;
    }
}
