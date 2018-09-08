package edu.colostate.cs.cs414.a1.josiahm;

import java.util.HashSet;

public class Project {

  final private String name;
  private ProjectSize size;
  private ProjectStatus status;
  private HashSet<Qualification> qualifications;
  
  
  public Project(String name, ProjectSize size, ProjectStatus status, HashSet<Qualification> qs) 
  throws NullPointerException, InvalidName, InvalidQualifications {
    
    if(name == null) {
      throw new NullPointerException("The name for a worker can not be null");
      }
    
    if(name.length() == 0 || !name.matches(".*\\S+.*")) {
      throw new InvalidName("The name for a project must not be empty");
      }
    
    if(qs == null) {
      throw new NullPointerException("The qualification's for a worker can not be null");
      }
    
    if(qs.isEmpty()) {
      throw new InvalidQualifications("The qualification's for a worker can not be empty");
      }
    
    this.name = name;
    this.size = size;
    this.status = status;
    this.qualifications = qs;
  }
  
  public String getName() {
    return null;
  }
  
  public ProjectSize getSize() {
    return null; 
  }
  
  public ProjectStatus getStatus() {
    return null;
  }
  
  public void setStatus(ProjectStatus status) {
    
  }
  
  public HashSet<Qualification> missingQualifications(){
    return null;
  }
  
  public boolean isHelpful(Worker w) throws NullPointerException {
    return false;
  }
  
  public HashSet<Worker> getWorkers(){
    return null;
  }
  
  public boolean addQualification(Qualification q) throws NullPointerException {
    return false;
  }
  
  public int hashCode() {
    return 0;
  }
  
  public boolean equals(Object o){
    return false;
  }
  
  public String toString() {
    return null;
  }
  
}
