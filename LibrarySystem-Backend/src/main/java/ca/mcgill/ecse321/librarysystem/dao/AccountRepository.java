package ca.mcgill.ecse321.librarysystem.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.librarysystem.models.Account;
import ca.mcgill.ecse321.librarysystem.models.Online;

public interface AccountRepository extends CrudRepository<Account, Integer> {

	//This is simply the Primary Key for Account
	Account findAccountById(int id);

	Online findOnlineByUsername(String username);

}