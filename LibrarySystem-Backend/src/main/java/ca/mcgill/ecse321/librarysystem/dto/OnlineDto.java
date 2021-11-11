package ca.mcgill.ecse321.librarysystem.dto;
import java.util.HashSet;
import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;
/**
* @author Howard
*/
public class OnlineDto extends AccountDto{

    private String username;
    private String password;
    private String email;
    
	public OnlineDto() {
	}

	public OnlineDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
	}

    public OnlineDto(int id, String address, String name, AccountCategory accountCategory, boolean isLocal, int numChecked, HashSet<EventDto> events, HashSet<MediaDto> medias, String username, String password, String email) {
        super(id, address, name, accountCategory, isLocal, numChecked, events, medias);
        this.username = username;
        this.password = password;
        this.email = email;
	}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
    
}