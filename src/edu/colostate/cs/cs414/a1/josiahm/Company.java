package edu.colostate.cs.cs414.a1.josiahm;

import java.util.HashSet;

public class Company {
  
  /**
   * The name of the company
   */
  private final String name;
  /**
   * All the workers the company have
   */
  private HashSet<Worker> availableWorkers;
  /**
   * All the workers assigned to projects
   */
  private HashSet<Worker> assignedWorkers;
  /**
   * All the projects the company is working on
   */
  private HashSet<Project> projects;
  
  /**
   * Constructor for the company, The company name must not be empty
   * @param name the name of company
   * @throws InvalidName the name was empty or only white spaces
   * @throws NullPointerException the name was null
   */
  public Company(String name) throws InvalidName, NullPointerException {
    // Check name
    if(name == null) {
      throw new NullPointerException("The name for a worker can not be null");
      }
    if(name.length() == 0 || !name.matches(".*\\S+.*")) {
      throw new InvalidName("The name for a project must not be empty");
      }
    
    this.name = name;
    this.availableWorkers = new HashSet<Worker>();
    this.assignedWorkers = new HashSet<Worker>();
    this.projects = new HashSet<Project>();
  }
  
  /**
   * Gets the name of the company
   * @return the company name
   */
  public String getName() {
    return this.name;
  }
  
  /**
   * Gets all the available workers in the company
   * @return the available workers
   */
  public HashSet<Worker> getAvailableWorkers(){
    return new HashSet<Worker>(availableWorkers);
  }
  
  /**
   * Gets all the assigned worker
   * @return the assigned workers
   */
  public HashSet<Worker> getAssignedWorkers(){
    return new HashSet<Worker>(assignedWorkers);
  }
  
  /**
   * Get the set of all workers not assigned to a project
   * @return the unassigned workers
   */
  public HashSet<Worker> getUnassignedWorkers(){
    HashSet<Worker> availableWorkersCopy = new HashSet<Worker>(availableWorkers);
    availableWorkersCopy.removeAll(assignedWorkers);
    return availableWorkersCopy;
  }
  
  /**
   * Gets the set of all the projects for the company
   * @return the projects
   */
  public HashSet<Project> getProjects(){
    return new HashSet<Project>(this.projects);
  }
  
  /**
   * Adds a worker to a set of available workers if they are not already in the pool
   * or assigned to a project
   * @param w worker to add
   * @return true the worker was added, false worker not added
   * @throws NullPointerException worker was null
   */
  public boolean addToAvailableWorkerPool(Worker w) throws NullPointerException{
    if(w == null) {
      throw new NullPointerException("Can not add null to the available worker pool");
    }
    if(!w.getProjects().isEmpty()) {
      return false;
    }
    return this.availableWorkers.add(w);
  }
  
  /**
   * Creates a new project and adds it to the set of project for the company
   * @param n name of the project
   * @param qs the set qualifications of the project
   * @param size the size of the project
   * @return the created project
   * @throws NullPointerException name or qualifications was null
   * @throws InvalidName empty string or string of white spaces
   * @throws InvalidQualifications empty set of qualifications
   */
  public Project createProject(String n, HashSet<Qualification> qs, ProjectSize size) 
      throws NullPointerException, InvalidName, InvalidQualifications {
    Project newProject = new Project(n, size, ProjectStatus.PLANNED, qs);
    projects.add(newProject);
    return newProject;
    
  }
  
  /**
   * Assigns a worker to a project if they have the qualifications or is not overloaded
   * @param w the worker to assign to the project
   * @param p the project to assign the worker to
   * @return True if the worker was assigned, else False
   * @throws NullPointerException either the worker or project is null
   */
  public boolean assign(Worker w, Project p) throws NullPointerException{
    // Check for nulls
    if(checkValidWorkerProject(w, "Can not assign null to a project",
        p, "Can not assign a worker to a null project")){
      return false;
    }
    // Check if the project can assign new workers
    if(p.getStatus() == ProjectStatus.ACTIVE || p.getStatus() == ProjectStatus.FINISHED) {
      return false;
    }
    // See if worker is already assigned to the project
    if(w.getProjects().contains(p)) {
      return false;
    }
    
    // Add the worker if they are useful and not overloaded
    if(p.isHelpful(w) && !w.willOverload(p)) {
      p.addWorker(w);
      w.addProject(p);
      assignedWorkers.add(w);
      return true;
    }
    // Catch any other cases
    return false;
  }
  
  /**
   * Checks if a worker and project are null and if they are know to the company
   * @param w worker to check
   * @param message1 message if worker if null
   * @param p project to check
   * @param message2 message if project is null
   * @return true if worker and project are part of the company
   */
  private boolean checkValidWorkerProject(Worker w, String message1, Project p, String message2) throws NullPointerException {
    if(w == null) {
      throw new NullPointerException(message1);
    }
    if(p == null) {
      throw new NullPointerException(message2);
    }
    // Check if the project and worker are part of the company
    if(!availableWorkers.contains(w) || !projects.contains(p) ) {
      return true;
    }
    
    return false;
  }
  
  /**
   * Unassigns a worker from a project and removes the worker from assigned workers list
   * if the worker is assigned to no other project
   * @param w the worker to unassign
   * @param p the project to remove the worker from
   * @return true of the worker was unassigned, else false
   * @throws NullPointerException the worker or project was null
   */
  public boolean unassign(Worker w, Project p) throws NullPointerException{
 // Check for nulls
    if(checkValidWorkerProject(w, "Can not unassign null to a project",
        p, "Can not unassign a worker to a null project")){
      return false;
    }
    // Remove worker
    w.removeProject(p);
    p.removeWorker(w);
    // Change Active to suspended if project missing qualifications
    if(p.getStatus() == ProjectStatus.ACTIVE && !p.missingQualifications().isEmpty()) {
      p.setStatus(ProjectStatus.SUSPENDED);
    }
    // Remove from assigned if no other projects
    if(w.getProjects().isEmpty()) {
      assignedWorkers.remove(w);
    }
    
    return true;
  }
  
  /**
   * Unassigns a worker from all their assigned projects
   * @param w the worker
   * @throws NullPointerException the worker was null
   */
  public void unassignAll(Worker w) throws NullPointerException {
   for(Project p: w.getProjects()) {
     unassign(w, p);
   }
  }
  
  /**
   * Starts a projects if it is in the Planning or Suspended status, its qualifications are meet and
   * assigned worker will not be overloaded by starting the project
   * @param p the project to start
   * @return true if the project was started, else false
   */
  public boolean start(Project p) {
    // Check for Null
    if(p == null) {
      throw new NullPointerException("Can not start a null project");
    }
    
    // Start project if it can be started, has no missing qualifications
    if((p.getStatus() == ProjectStatus.PLANNED || p.getStatus() == ProjectStatus.SUSPENDED) && p.missingQualifications().isEmpty()) {
      for(Worker w : p.getWorkers()) {
        // Check if any of the workers will be overloaded
        if(w.willOverload(p)) {
          return false;
        }
      }
      // Start Project
      p.setStatus(ProjectStatus.ACTIVE);
      return true;
    }
    
    return false;
  }
  
  /**
   * Finishes a project and unassigns all the workers assigned project
   * @param p the project to finish
   * @return true if the project was finished, else false
   */
  public boolean finish(Project p) {
    // Check for Null
    if(p == null) {
      throw new NullPointerException("Can not finish a null project");
    }
    // See if project can be stoped
    if(p.getStatus() != ProjectStatus.ACTIVE) {
      return false;
    }
    // Unassign all workers
    for(Worker w: p.getWorkers()) {
      w.removeProject(p);
      p.removeWorker(w);
      
      if(w.getProjects().isEmpty()) {
        assignedWorkers.remove(w);
      }
    }
    
    return true;
  }
  
  @Override
  public int hashCode(){
    return name.hashCode();
  }
  
  @Override
  public boolean equals(Object o){
    if(o instanceof Company){
      Company other = (Company) o;
      return this.name.equals(other.name);
    } 
    
    return false;
  }
  
  @Override
  public String toString(){
    return name + ":" + availableWorkers.size() + ":" + projects.size();
  }
}
