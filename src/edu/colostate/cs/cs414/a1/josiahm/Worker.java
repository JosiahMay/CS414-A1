package edu.colostate.cs.cs414.a1.josiahm;

import java.util.HashSet;

public class Worker {
  
  private final String name;
  private final HashSet<Qualification> qualifications;
  private final HashSet<Project> projects;
  private Double salary;

  /**
   * Constructor for a Worker object. The name and qualifications must not be empty or null 
   * @param name Name of the worker
   * @param qs The set qualifications of the worker
   * @throws InvalidName An empty name was given
   * @throws InvalidQualifications An empty set of qualification was given
   * @throws NullPointerException Either the name or set of qualifications are null
   */
  public Worker(String name, HashSet<Qualification> qs ) throws InvalidName, InvalidQualifications,  NullPointerException{
    
    if(name == null) {
      throw new NullPointerException("The name for a worker can not be null");
      }
    
    if(name.length() == 0 || !name.matches(".*\\S+.*")) {
      throw new InvalidName("The name for a worker must not be empty");
      }
    
    if(qs == null) {
      throw new NullPointerException("The qualification's for a worker can not be null");
      }
    
    if(qs.isEmpty()) {
      throw new InvalidQualifications("The qualification's for a worker can not be empty");
      }
    
    this.name = name;
    this.qualifications = new HashSet<Qualification>(qs);
    this.projects = new HashSet<Project>();
    salary = new Double(0.0);
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
  
  /**
   * Get all the project the worker is assigned to
   * @return the projects
   */
  public HashSet<Project> getProjects() {
    return projects;
  }
  
  /**
   * Adds a project to the workers assigned projects 
   * @param p the project to add
   * @throws NullPointerException project was null
   */
  public void addProject(Project p) throws NullPointerException{
    if(p == null) {
      throw new NullPointerException("Workers can not add a null project");
      }
    this.projects.add(p);
  }
  
  
  /**
   * Checks if the name of two workers are the same
   *
   * @return The worker have the same name
   */
  @Override
  public boolean equals(Object o) {
    if(o instanceof Worker){
      Worker other = (Worker) o;
      return this.name.equals(other.name);
    } 
    
    return false;
  }
  
  @Override
  public int hashCode() {
	  return this.name.hashCode();
  }
  
  @Override
  public String toString() {
    return name+ ":" + projects.size() + ":" + qualifications.size() + ":" + salary;
  }

  
  
  

}
