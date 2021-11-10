package ca.mcgill.ecse321.librarysystem.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.librarysystem.models.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {

	//This is simply the Primary Key for Account
	Account findAccountById(int id);

	//Account findAccountByAddress(String address);

}