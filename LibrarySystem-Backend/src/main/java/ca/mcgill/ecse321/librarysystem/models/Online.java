/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem.models;


import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;

// line 25 "model.ump"
// line 119 "model.ump"
@Entity
public class Online extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Online Attributes
  private String username;
  private String password;
  private String email;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  // public Online(int aId, String aAddress, String aName, AccountCategory aAccountCategory, boolean aIsLocal, int aNumChecked, LibrarySystem aLibrarySystem, String aUsername, String aPassword, String aEmail)
  // {
  //   //super(aId, aAddress, aName, aAccountCategory, aIsLocal, aNumChecked, aLibrarySystem);
  //   username = aUsername;
  //   password = aPassword;
  //   email = aEmail;
  // }
  public Online(){
    super.setAccountCategory(Account.AccountCategory.Online);
    }

  //------------------------
  // INTERFACE
  //------------------------

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
@Id
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

  // public void delete()
  // {
  //   //super.delete();
  // }


  // public String toString()
  // {
  //   return super.toString() + "["+
  //           "username" + ":" + getUsername()+ "," +
  //           "password" + ":" + getPassword()+ "," +
  //           "email" + ":" + getEmail()+ "]";
  // }
}