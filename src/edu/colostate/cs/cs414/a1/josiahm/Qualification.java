package edu.colostate.cs.cs414.a1.josiahm;

public class Qualification {
  
  final private String description;

  /**
   * Constructor for the qualification class. The description of the object is given
   * during construction
   * 
   * @param description The description for this qualification
   * @throws InvalidDescription The description is empty string or all white spaces
   * @throws NullPointerException The description is null
   */
  public Qualification(String description) throws InvalidDescription, NullPointerException {
    
    if(description == null) {
      throw new NullPointerException("The description for a Qualification can not be null");
    }
    
    if(description.length() == 0 || !description.matches(".*\\S+.*")) {
      throw new InvalidDescription("The name for a qaulifcation must not be empty");
    }
    
    this.description = description; 
  }
  
  /**
   * Checks if the description of two qualification is the same
   *
   * @return The qualification have the same description
   */
  @Override
  public boolean equals(Object o) {
    if(o instanceof Qualification){
      Qualification other = (Qualification) o;
      return this.description.equals(other.description);
    } 
    
    return false;
    
  }
  
  /**
   * The description of the qualification
   *
   * @return The description
   */
  @Override
  public String toString() {
    return description;
  }
  
  @Override
  public int hashCode() {
	  return this.description.hashCode();
  }

}
