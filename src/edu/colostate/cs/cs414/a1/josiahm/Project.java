package edu.colostate.cs.cs414.a1.josiahm;

import java.util.HashSet;

public class Project {

  final private String name;
  final private ProjectSize size;
  private ProjectStatus status;
  private HashSet<Qualification> qualifications;
  private HashSet<Worker> assignedWorker;
  
  /**
   * Constructs the Project using a name that has at least one letter and a Set of qualifications
   * @param name the name of the project
   * @param size the size of the project
   * @param status the status of the project
   * @param qs the qualifications of the project
   * @throws NullPointerException name or qualifications are null
   * @throws InvalidName an empty string or one with only spaces
   * @throws InvalidQualifications an empty set of qualifications
   */
  public Project(String name, ProjectSize size, ProjectStatus status, HashSet<Qualification> qs) 
  throws NullPointerException, InvalidName, InvalidQualifications {
    // Check name
    if(name == null) {
      throw new NullPointerException("The name for a worker can not be null");
      }
    if(name.length() == 0 || !name.matches(".*\\S+.*")) {
      throw new InvalidName("The name for a project must not be empty");
      }
    // Check qualifications
    if(qs == null) {
      throw new NullPointerException("The qualification's for a project can not be null");
      }
    if(qs.isEmpty()) {
      throw new InvalidQualifications("The qualification's for a project can not be empty");
      }
    
    // assign states
    this.name = name;
    this.size = size;
    this.status = status;
    this.qualifications = new HashSet<Qualification>(qs);
    this.assignedWorker = new HashSet<Worker>();
  }
  
  /**
   * Gets the name of the project
   * @return the name of the project
   */
  public String getName() {
    return this.name;
  }
  
  /**
   * Gets the size of the object
   * @return size of the object
   */
  public ProjectSize getSize() {
    return this.size; 
  }
  
  /**
   * Gets the status of the object
   * @return status of the object
   */
  public ProjectStatus getStatus() {
    return this.status;
  }
  
  /**
   * Sets a new status for the project
   * @param status the new status of the project
   */
  public void setStatus(ProjectStatus status) {
    this.status = status;
  }
  
  /**
   * Checks all the currently assigned workers qualifications against the projects qualifications 
   * and returns the qualifications not yet met.
   * @return the qualifications missing
   */
  public HashSet<Qualification> missingQualifications(){
    HashSet<Qualification> metQualifications = new HashSet<Qualification>();
    HashSet<Qualification> projectQualificationsCopy = new HashSet<Qualification>(qualifications);
    for(Worker worker: assignedWorker) {
      metQualifications.addAll(worker.getQualifications());
    }
    projectQualificationsCopy.removeAll(metQualifications);
    return projectQualificationsCopy;
  }
  
  /**
   * Checks a worker qualifications to see if they meet any of the missing qualifications of the project
   * @param w the worker in question
   * @return true if the worker has a needed qualification, else false
   * @throws NullPointerException the worker is null
   */
  public boolean isHelpful(Worker w) throws NullPointerException {
    for(Qualification q: w.getQualifications()) {
      if(missingQualifications().contains(q)) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Gets all the currently assigned workers to project but there is no way to add workers
   * @return the workers assigned 
   */
  public HashSet<Worker> getWorkers(){
    return new HashSet<Worker>(assignedWorker);
  }
  
  /**
   * Adds a worker to the project. 
   * @param w the worker to add
   * @return true if worker added, else false
   * @throws NullPointerException the worker is null
   */
  public boolean addWorker(Worker w) throws NullPointerException {
    if(w == null) {
      throw new NullPointerException("The worker to add to a project can not be null");
    }
    
    return this.assignedWorker.add(w);
  }
  
  /**
   * Removes a worker from the project. 
   * @param w the worker to remove
   * @return true if worker is removed, else false
   * @throws NullPointerException the worker is null
   */
  public boolean removeWorker(Worker w) throws NullPointerException {
    if(w == null) {
      throw new NullPointerException("The worker to add to a project can not be null");
    }
    
    return this.assignedWorker.remove(w);
  }
  
  /**
   * Adds a qualification to the project
   * @param q the qualification to add
   * @return true if qualification added, else false
   * @throws NullPointerException the qualification is null
   */
  public boolean addQualification(Qualification q) throws NullPointerException {
    if(q == null) {
      throw new NullPointerException("The qualification's for a project can not be null");
    }
    return this.qualifications.add(q);
  }
  
  
  /**
   * Checks if the name of two Projects are the same
   *
   * @return The qualification have the same description
   */
  @Override
  public boolean equals(Object o) {
    if(o instanceof Project){
      Project other = (Project) o;
      return this.name.equals(other.name);
    } 
    return false;
  }
  
  public String toString() {
    return name + ":" + assignedWorker.size() + ":" + status;
  }
  
  public int hashCode() {
    return this.name.hashCode();
  }

  
}
