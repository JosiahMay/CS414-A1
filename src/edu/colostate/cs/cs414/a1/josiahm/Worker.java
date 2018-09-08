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
   * @throws NullPointerException Either the name or set of qualifications are null
   */
  public Worker(String name, HashSet<Qualification> qs ) throws InvalidDescription, InvalidQualifications,  NullPointerException{
    
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
    return this.name;
  }
  
  /**
   * Gets the workers salary
   * @return The salary
   */
  public Double getSalary() {
    return this.salary;
  }
  
  /**
   * Sets the workers salary
   * @param salary The new salary
   */
  public void setSalary(Double salary) {
    this.salary = salary;
  }
  
  /**
   * Returns a shallow copy of the qualifications
   * @return A copy of the qualifications
   */
  public HashSet<Qualification> getQualifications(){
    return this.qualifications;
  }
  
  /**
   * Adds a new qualification to the worker
   * @param q the new qualification
   * @return True if qualification added to set or False if not added
   * @throws NullPointerException
   */
  public boolean addQualification(Qualification q) throws NullPointerException{
    if(q == null) {
      throw new NullPointerException("The new qualification for a worker can not be null");
    }
    return this.qualifications.add(q);
  }
  
  @Override
  public int hashCode() {
	  return this.name.hashCode();
  }
  
  

}
