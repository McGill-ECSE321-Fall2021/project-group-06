package ca.mcgill.ecse321.librarysystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.AccountRepository;
import ca.mcgill.ecse321.librarysystem.dao.MediaRepository;
import ca.mcgill.ecse321.librarysystem.models.Media;
import ca.mcgill.ecse321.librarysystem.models.Account;

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
    public Media getMediaByID(int mediaID){
        
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
    public List<Media> getAllMedia() {
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
    public Media deleteMediaByID(int mediaID){

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

    // Helper method that converts Iterables to Lists

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
