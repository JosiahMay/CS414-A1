package edu.colostate.cs.cs414.a1.josiahm;

import java.util.HashSet;

public class Worker {
  
  private final String name;
  private final HashSet<Qualification> qualifications;

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

}
