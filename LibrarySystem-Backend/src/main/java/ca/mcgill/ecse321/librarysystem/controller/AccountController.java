package ca.mcgill.ecse321.librarysystem.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse321.librarysystem.dto.AccountDto;
import ca.mcgill.ecse321.librarysystem.models.Account;
import ca.mcgill.ecse321.librarysystem.models.Offline;
import ca.mcgill.ecse321.librarysystem.models.Online;
import ca.mcgill.ecse321.librarysystem.service.AccountService;
//author David Hu
@CrossOrigin(origins="*")
@RestController
public class AccountController {
    @Autowired
	private AccountService accountService;

	@GetMapping(value={"/login/{id}", "/login/{id}/"})
	public AccountDto login(@PathVariable("id") int id, @RequestParam String password) throws IllegalArgumentException{
		Account account = null;
		account = accountService.login(id, password);
		if (account instanceof Offline){
			return Conversion.convertToDto((Offline) account);
		} else if (account instanceof Online){
			if(((Online) account).getPassword().equals(password)){
				return Conversion.convertToDto((Online) account);
			}
			return null;
		} else {
			return null;
		}
	}

	@PutMapping(value= {"/assignEvent/{id}", "/assignEvent/{id}/"})
	 public AccountDto assignEvent(@PathVariable(name="id") int id, @RequestParam String name) {
		 Account acc = accountService.bookEvent(name, id);
		if (acc instanceof Offline){
			return Conversion.convertToDto((Offline) acc);
		} else if (acc instanceof Online){
			
			return Conversion.convertToDto((Online) acc);
		} else {
			return null;
		}
	 }
}
