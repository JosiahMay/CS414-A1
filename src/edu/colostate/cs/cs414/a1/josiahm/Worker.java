package edu.colostate.cs.cs414.a1.josiahm;

import java.util.HashSet;

public class Worker {
  
  private final String name;
  private final HashSet<Qualification> qualifications;
  private Double salary = 0.0;

  /**
   * Constructor for a Worker object. The name and qualifications must not be empty or null 
   * @param name Name of the worker
   * @param qs The set qualifications of the worker
   * @throws InvalidDescription An empty name was given
   * @throws InvalidQualifications An empty set of qualification was given
   */
  public Worker(String name, HashSet<Qualification> qs ) throws InvalidDescription, InvalidQualifications {
    
    if(name == null) {
      throw new NullPointerException("The name for a worker can not be null");
      }
    
    if(name.length() == 0 || !name.matches(".*\\S+.*")) {
      throw new InvalidDescription("The name for a worker must not be empty");
      }
    
    if(qs == null) {
      throw new NullPointerException("The qualification's for a worker can not be null");
      }
    
    if(qs.isEmpty()) {
      throw new InvalidQualifications("The qualification's for a worker can not be empty");
      }
    
    this.name = name;
    this.qualifications = qs;
  }
  
  /**
   * The name of the worker
   * @return the name of the worker
   */
  public String getName() {
    return name;
  }

}
