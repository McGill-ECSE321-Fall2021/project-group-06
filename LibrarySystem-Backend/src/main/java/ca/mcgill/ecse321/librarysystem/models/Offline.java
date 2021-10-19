/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem.models;


import java.util.*;

import javax.persistence.Entity;

// line 33 "model.ump"
// line 124 "model.ump"
@Entity
public class Offline extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------
  // @Override
  // public void setAccountCategory(AccountCategory aAccountCategory) {
  //     // TODO Auto-generated method stub
  //     super.setAccountCategory(aAccountCategory);
  // }
  
  //------------------------
  // CONSTRUCTOR
  //------------------------
  // public Offline(int aId, String aAddress, String aName, AccountCategory aAccountCategory, boolean aIsLocal, int aNumChecked, LibrarySystem aLibrarySystem)
  // {
  //   //super(aId, aAddress, aName, aAccountCategory, aIsLocal, aNumChecked, aLibrarySystem);
  // }
  public Offline(){
  super.setAccountCategory(Account.AccountCategory.Offline);
  }
  //------------------------
  // INTERFACE
  //------------------------

  // public void delete()
  // {
  //   //super.delete();
  // }

}