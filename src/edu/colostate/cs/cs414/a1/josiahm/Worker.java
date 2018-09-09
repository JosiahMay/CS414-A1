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
    // Check name
    if(name == null) {
      throw new NullPointerException("The name for a worker can not be null");
      }
    if(name.length() == 0 || !name.matches(".*\\S+.*")) {
      throw new InvalidName("The name for a worker must not be empty");
      }
    // Check Qualifications
    if(qs == null) {
      throw new NullPointerException("The qualification's for a worker can not be null");
      }
    if(qs.isEmpty()) {
      throw new InvalidQualifications("The qualification's for a worker can not be empty");
      }
    // States
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
    return new HashSet<Qualification>(this.qualifications);
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
    return new HashSet<Project>(projects);
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
   * Removes a project to the workers assigned projects 
   * @param p the project to remove
   * @throws NullPointerException project was null
   */
  public void removeProject(Project p) throws NullPointerException{
    if(p == null) {
      throw new NullPointerException("Workers can not remove a null project");
      }
    this.projects.remove(p);
  }
  
  /**
   * Checks if adding the a project will overload a worker based
   * on the project they are actively working on
   * @param p the project in question
   * @return true if adding the project will over load the worker, else false
   */
  public boolean willOverload(Project p) {
    // Check for null
    if(p == null) {
      throw new NullPointerException("Cannot check a null project to overload a worker");
      }
    
    int currentLoad = computeCurrentLoad();
    // Add project to current workload
    switch(p.getSize()) {
      case SMALL:
        currentLoad += 1;
        break;
      case MEDIUM:
        currentLoad += 2;
        break;
      case LARGE:
        currentLoad += 3;
        break;
    }
    
    return currentLoad > 12;  
  }
  
  /**
   * Sums up current work load of the active projects
   * 3 for large projects
   * 2 for medium projects
   * 1 for small projects 
   * @return the current work load
   */
  private int computeCurrentLoad() {
    int small = getProjectLoad(ProjectSize.SMALL);
    int mid = getProjectLoad(ProjectSize.MEDIUM);
    int large = getProjectLoad(ProjectSize.LARGE);
    return (3*large + 2*mid + small);
  }
  
  /**
   * Checks all the active projects for their size and counts them by size
   * @param size the size of the project to check for
   * @return the count of the size
   */
  private int getProjectLoad(ProjectSize size) {
    int count = 0;
    for(Project p: projects) {
      if(p.getStatus() == ProjectStatus.ACTIVE && p.getSize() == size) {
        count++;
      }
    }
    return count;
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
