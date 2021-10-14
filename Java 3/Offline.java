/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 33 "model.ump"
// line 124 "model.ump"
public class Offline extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Offline(int aId, String aAddress, String aName, AccountType aAccountType, boolean aIsLocal, int aNumChecked, LibrarySystem aLibrarySystem)
  {
    super(aId, aAddress, aName, aAccountType, aIsLocal, aNumChecked, aLibrarySystem);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}