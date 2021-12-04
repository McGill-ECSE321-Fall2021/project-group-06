package ca.mcgill.ecse321.librarysystem.dto;
import java.util.*;
import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;
/**
* @author Howard
*/
public abstract class AccountDto {

    private int id;
    private String address;
    private String name;
    private AccountCategory accountCategory;
    private boolean isLocal;
    //This is not necessary, but may facilitate readability later
    private int numChecked;

    private Set<EventDto> events;
    private Set<MediaDto> medias;

    public AccountDto() {
	}

    public AccountDto(int id, AccountCategory accountCategory) {
        this(id, "123@example.com", "Jack Mehoff", accountCategory, true, 1, null, null);
	}
	public AccountDto(int id, String address, String name, AccountCategory accountCategory, boolean isLocal, int numChecked, HashSet<EventDto> events, HashSet<MediaDto> medias) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.accountCategory = accountCategory;
        this.isLocal = isLocal;
        this.numChecked = numChecked;
        this.events = events;
        this.medias = medias;
	}


    public String getAddress() {
        return address;
    }

    public AccountCategory getAccountCategory() {
        return accountCategory;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public int getNumChecked() {
        return numChecked;
    }

    public Set<EventDto> getEvents() {
        return events;
    }

    public Set<MediaDto> getMedias() {
        return medias;
    }

    public int getId(){
        return this.id;
    }
	public String getName() {
		return name;
	}

}