package ca.mcgill.ecse321.librarysystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.librarysystem.dao.MediaRepository;
import ca.mcgill.ecse321.librarysystem.models.Media;
import ca.mcgill.ecse321.librarysystem.models.Media.Item;

@Service
public class MediaService {
    
    // @Autowired
    // private AccountRepository accountRepository;

    @Autowired
    private MediaRepository mediaRepository;

    /**
     * 
     * @param mediaID
     * @return media
     * @author Samuel
     */
    @Transactional
    public Media getMedia(int mediaID){
        
        // Input validation
        String error="";

        if (mediaRepository.findMediaByID(mediaID) == null) {
            error = error + "Media ID cannot be found!";
        }

        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }

        Media media = mediaRepository.findMediaByID(mediaID);
        return media;
    }

    /**
     * 
     * @param accountID
     * @return List<Media>
     * @author Samuel
     */
    // @Transactional
    // public List<Media> getAllMediaByAccountID(int accountID) {

    //     // Input validation
    //     String error = "";

    //     if (accountRepository.findAccountById(accountID) == null){
    //         error = error + "Account not found! ";
    //     }
    //     error = error.trim();

    //     if (error.length() > 0){
    //         throw new IllegalArgumentException(error);
    //     }

    //     Account account = accountRepository.findAccountById(accountID);
    //     List<Media> mediaList = mediaRepository.findByAccount(account);
    //     return mediaList;
    // }

    /**
     * 
     * @return List<Media>
     * @author Samuel
     */
    @Transactional
    public List<Media> getAllMedias() {
        Iterable<Media> medias = mediaRepository.findAll();
        return toList(medias);
    }
    
    /**
     * 
     * @param mediaID
     * @return media
     * @author Samuel
     */
    @Transactional
    public Media deleteMedia(int mediaID){

        // Input validation
        String error = "";

        if (mediaRepository.findMediaByID(mediaID) == null){
            error = error + "Media ID not found";
            error = error.trim();
            throw new IllegalArgumentException(error);
        }

        Media media = mediaRepository.findMediaByID(mediaID);
        mediaRepository.delete(media);
        return media;
    }

    /**
     * 
     * @param accountID
     * @return List<Media>
     * @author Samuel
     */
    // @Transactional
    // public List<Media> deleteAllMediaByAccountID(int accountID){

    //     // Input validation
    //     String error = "";

    //     if (accountRepository.findAccountById(accountID) == null){
    //         error = error + "Account ID not found";
    //         error = error.trim();
    //         throw new IllegalArgumentException(error);
    //     }

    //     Account account = accountRepository.findAccountById(accountID);
    //     List<Media> mediaList = mediaRepository.findByAccount(account);

    //     for (Media media : mediaList){
    //         mediaRepository.delete(media);
    //     }

    //     return mediaList;
    // }

    /**
     * 
     * @return List<Media>
     */
    @Transactional
    public List<Media> deleteAllMedias(){

        Iterable<Media> mediaList = mediaRepository.findAll();
        mediaRepository.deleteAll();
        return toList(mediaList);
    }

    @Transactional
    public Media updateMedia(Item mediaType, int mediaID){
        if (mediaRepository.findMediaByID(mediaID) == null){
            throw new IllegalArgumentException("Media ID does not exist");
        }
        if (mediaType == null){
            throw new IllegalArgumentException("media type cannot be empty.");
        }
        Media media = mediaRepository.findMediaByID(mediaID);
        media.setType(mediaType);
        mediaRepository.save(media);
        return media;
    }
    // Helper method that converts Iterables to Lists

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
