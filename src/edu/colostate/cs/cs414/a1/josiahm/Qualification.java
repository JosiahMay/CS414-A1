package edu.colostate.cs.cs414.a1.josiahm;

import java.util.HashSet;

public class Qualification {
  
  final private String description;
  final private HashSet<Worker> workerWithQual = new HashSet<Worker>();

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
   * Adds a worker to a set of all workers that have this qualification
   * @param w the worker to add
   * @throws NullPointerException the passed worker is null
   */
  public void addWorker(Worker w) throws NullPointerException {
	  if (w == null) {
		  throw new NullPointerException("The a worker to add to a qaualification can not be null");
	  }
	  this.workerWithQual.add(w);
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
