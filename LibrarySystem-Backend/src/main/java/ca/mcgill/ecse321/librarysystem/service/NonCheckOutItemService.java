package ca.mcgill.ecse321.librarysystem.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.MediaRepository;
import ca.mcgill.ecse321.librarysystem.models.Media.Item;
import ca.mcgill.ecse321.librarysystem.models.CheckOutItem;
import ca.mcgill.ecse321.librarysystem.models.Media;
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
    public NonCheckOutItem createNonCheckOutItem (Item mediaType, int mediaID, String name){

        // Input validation (methods from tutorial notes 2.6.1)
        String error ="";
        boolean isValidType = false;
        if(mediaType == Item.Archive || mediaType == Item.Newspaper){
            isValidType = true;
        }
        if (!isValidType){
            error = error + "Media type invalid!";
        }
        if (mediaRepository.findMediaByID(mediaID) != null || mediaID == 0) {
            error = error + "Media ID invalid!";
        }
        if (name.trim().length() == 0){
            error = error + "Media name cannot be empty!";
        }
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }

        NonCheckOutItem nonCheckOutItem = new NonCheckOutItem();
        nonCheckOutItem.setType(mediaType);
        nonCheckOutItem.setID(mediaID);
        nonCheckOutItem.setName(name);
        mediaRepository.save(nonCheckOutItem);
        return nonCheckOutItem;
    }

    @Transactional
    public NonCheckOutItem updateNonCheckOutItem (Item newMediaType, int mediaID, String name){

        // Input validation (methods from tutorial notes 2.6.1)
        String error ="";
        boolean isValidType = false;
        if(newMediaType == Item.Archive || newMediaType == Item.Newspaper){
            isValidType = true;
        }
        if (!isValidType){
            error = error + "Media type invalid!";
        }

        if (mediaRepository.findMediaByID(mediaID) == null) {
            error = error + "Media does not exist";
        }

        if (name.trim().length() == 0){
            error = error + "Media name cannot be empty!";
        }

        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }

        NonCheckOutItem nonCheckOutItem = new NonCheckOutItem();
        nonCheckOutItem.setType(newMediaType);
        nonCheckOutItem.setID(mediaID);
        nonCheckOutItem.setName(name);
        mediaRepository.save(nonCheckOutItem);
        return nonCheckOutItem;
    }

    @Transactional
    public NonCheckOutItem getNonCheckOutItemByID(int mediaID){
        
        // Input validation
        String error="";

        if (mediaRepository.findMediaByID(mediaID) == null) {
            error = error + "Media ID cannot be found!";
        }
        if (mediaRepository.findMediaByID(mediaID) instanceof CheckOutItem) {
            error = error + "This is a CheckOutItem";
        }
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }

        NonCheckOutItem media = (NonCheckOutItem) mediaRepository.findMediaByID(mediaID);
        return media;
    }

    @Transactional
    public Set<NonCheckOutItem> getAllNonCheckOutItems(){
        HashSet<NonCheckOutItem> nonCheckOutItems = new HashSet<NonCheckOutItem>();
        for(Media media: mediaRepository.findAll()){
            if(media instanceof NonCheckOutItem){
                nonCheckOutItems.add((NonCheckOutItem)media);
            }
        }
        return nonCheckOutItems;
    }

    @Transactional
    public NonCheckOutItem deleteNonCheckOutItem(int id) {
        if(mediaRepository.findById(id) == null){
            throw new IllegalArgumentException("NonCheckOutItem does not exist");
        }
        NonCheckOutItem nonCheckOutItem = (NonCheckOutItem) mediaRepository.findMediaByID(id);        
        mediaRepository.delete(nonCheckOutItem);
        return nonCheckOutItem;
    }
}
