package edu.colostate.cs.cs414.a1.josiahm;

public class Qualification {
  
  final private String description;

  /**
   * 
   * @param description
   * @throws InvalidDescription
   * @throws NullPointerException
   */
  public Qualification(String description) throws InvalidDescription, NullPointerException {
    
    if(description == null) {
      throw new NullPointerException("The description for a Qualification can not be null");
    }
    System.out.println(description + ": " + description.matches(".*\\S+.*"));
    if(description.length() == 0 || !description.matches(".*\\S+.*")) {
      throw new InvalidDescription("The name for a qaulifcation must not be empty: " + description);
    }
    
    this.description = description; 
  }
  
  @Override
  public boolean equals(Object o) {
    if(o instanceof Qualification){
      Qualification other = (Qualification) o;
      return this.description.equals(other.description);
    } 
    
    return false;
    
  }
  
  

}
