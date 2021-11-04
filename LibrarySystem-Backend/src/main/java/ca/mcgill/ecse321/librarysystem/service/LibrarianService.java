package ca.mcgill.ecse321.librarysystem.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.*;
import ca.mcgill.ecse321.librarysystem.models.Account;
import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;
import ca.mcgill.ecse321.librarysystem.models.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem.models.Librarian;
import ca.mcgill.ecse321.librarysystem.models.Offline;
import ca.mcgill.ecse321.librarysystem.models.Shift;
import javassist.expr.Instanceof;

import java.util.ArrayList;
import java.util.List;

public class LibrarianService {
	 @Autowired
	 AccountRepository accountRepository;
	 @Autowired
	 ShiftRepository shiftRepository;
	
	/**
	 * @author Isabella Hao
	 * View Personal Shift
	 * 
	 */
	@Transactional
    public void ViewPersonalShift () {
		
	}
    
    /**
	 * @author Isabella Hao
	 * Create Offline Account
	 * 
	 */
	@Transactional
    public Offline CreateOfflineAccount(int id, String name) {
		return null;
	}
    
}
