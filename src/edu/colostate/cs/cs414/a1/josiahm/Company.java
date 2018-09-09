package edu.colostate.cs.cs414.a1.josiahm;

import java.util.HashSet;

public class Company {
  
  private final String name;
  
  private HashSet<Worker> availableWorkers;
  
  public Company(String name) throws InvalidName, NullPointerException {
    if(name == null) {
      throw new NullPointerException("The name for a worker can not be null");
      }
    if(name.length() == 0 || !name.matches(".*\\S+.*")) {
      throw new InvalidName("The name for a project must not be empty");
      }
    
    this.name = name;
    this.availableWorkers = new HashSet<Worker>();
  }
  
  public String getName() {
    return this.name;
  }
  
  public HashSet<Worker> getAvailableWorkers(){
    return null;
  }
  public HashSet<Worker> getAssignedWorkers(){
    return null;
  }
  public HashSet<Worker> getUnassignedWorkers(){
    return null;
  }
  public HashSet<Project> getProjects(){
    return null;
  }
  
  @Override
  public int hashCode(){
    return 0;
  }
  
  @Override
  public boolean equals(Object o){
    return false;
  }
  
  @Override
  public String toString(){
    return null;
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
      throw new NullPointerException("Can not a null to the available worker pool");
    }
    if(!w.getProjects().isEmpty()) {
      return false;
    }
    return false;
  }
  public Project createProject(String n, HashSet<Qualification> qs, ProjectSize size) {
    return null;
    
  }
  public boolean assign(Worker w, Project p) {
    return false;
  }
  public boolean unassign(Worker w, Project p) {
    return false;
  }
  public void unassignAll(Worker w) {
   
  }
  public boolean start(Project p) {
    return false;
  }
  public boolean finish(Project p) {
    return false;
  }
}
