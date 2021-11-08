package ca.mcgill.ecse321.librarysystem.models;
import javax.persistence.Entity;

@Entity
public class Online extends Account
{
  //Online is one of the accounts. It is a bit special since it has 3 attributes extra.
  //Online Attributes
  private String username;
  private String password;
  private String email;

  // public Online(){
  //   super.setAccountCategory(Account.AccountCategory.Online);
  //   }

  //It extends Account and inherits the ID primary key.


  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    username = aUsername;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public String getUsername()
  {
    return username;
  }

  public String getPassword()
  {
    return password;
  }

  public String getEmail()
  {
    return email;
  }
}