package edu.colostate.cs.cs414.a1.josiahm;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WorkerTest {
  
  private final String workerName = "Valid";
  
  private HashSet<Qualification> testQaulifications = new HashSet<Qualification>();
  private Worker testWorker;
  
  
  @BeforeEach
  void resetQualifications() throws NullPointerException, InvalidName, InvalidQualifications, InvalidDescription {
    testQaulifications.clear();
    testQaulifications.add(new Qualification("Q 1"));
    testWorker = new Worker(workerName, testQaulifications);
  }

  @Test
  void testWorker() {
      try {
        testWorker = new Worker(workerName, testQaulifications);
      } catch (InvalidName | InvalidQualifications e) {
        fail(e.getMessage());
      }
      
  
  }
  
  @Test
  void testWorkerNameNullPointerException() {
    Assertions.assertThrows(NullPointerException.class, () -> {
      testWorker = new Worker(null, testQaulifications);
      
    });
  }
  
  @Test
  void testWorkerInvalidNameEmpty() {
    Assertions.assertThrows(InvalidName.class, () -> {
      testWorker = new Worker("", testQaulifications);
      
    });
  }
  
  @Test
  void testWorkerInvalidNameSpaces() {
    Assertions.assertThrows(InvalidName.class, () -> {
      testWorker = new Worker("  ", testQaulifications);
      
    });
  }
  
  @Test
  void testWorkerValidDescriptionSpacesAndLetters() throws InvalidQualifications {
    try {
      testWorker = new Worker("  as", testQaulifications);
      Assertions.assertTrue(true);
    } catch (InvalidName | InvalidQualifications e) {
      fail(e.getMessage());
    }
  }
  
  @Test
  void testWorkerEmptyQualification() {
    Assertions.assertThrows(InvalidQualifications.class, () ->{
      testQaulifications.clear();
      testWorker = new Worker(workerName, testQaulifications);
    });
  }
  
  @Test
  void testWorkerNullQualification() {
    Assertions.assertThrows(NullPointerException.class, () ->{
      testWorker = new Worker(workerName, null);
    });
  }
  
  @Test
  void testGetName() {
    Assertions.assertEquals(workerName, testWorker.getName());
  }
  
  @Test
  void testGetSalary() {
    Assertions.assertEquals(new Double(0.0), testWorker.getSalary());
  }
  
  @Test
  void testSetSalary() {
    testWorker.setSalary(new Double(10.5));
    Assertions.assertEquals(new Double(10.5), testWorker.getSalary());
  }
  
  @Test
  void testGetQualifications() {
    HashSet<Qualification> returnedQualifcations = testWorker.getQualifications();
    Assertions.assertIterableEquals(testQaulifications, returnedQualifcations);
  }
  
  @Test
  void testAddQualificationsTrue() throws NullPointerException, InvalidDescription {
    Assertions.assertTrue(testWorker.addQualification(new Qualification("Q 2")));
  }
  
  @Test
  void testAddQualificationsFalse() throws NullPointerException, InvalidDescription {
    Assertions.assertFalse(testWorker.addQualification(new Qualification("Q 1")));
  }
  
  @Test
  void testAddQualificationsNull() throws NullPointerException, InvalidDescription {
    Assertions.assertThrows(NullPointerException.class, () ->{
      testWorker.addQualification(null);
    });
  }
  
  @Test
  void testAddGetProject() throws NullPointerException, InvalidName, InvalidQualifications {
    Project project = new Project("Valid Project", ProjectSize.LARGE, ProjectStatus.PLANNED, testQaulifications);
    HashSet<Project> projects = new HashSet<Project>();
    projects.add(project);
    
    testWorker.addProject(project);
    
    Assertions.assertIterableEquals(projects, testWorker.getProjects());
  }
  
  @Test
  void testAddProjectNull() throws NullPointerException, InvalidDescription {
    Assertions.assertThrows(NullPointerException.class, () ->{
      testWorker.addProject(null);
    });
  }
  
  @Test
  void testEqualsObject() throws NullPointerException, InvalidName, InvalidQualifications, InvalidDescription {
    Worker object1 = new Worker(workerName, testQaulifications);
    Worker object2 = new Worker(workerName, testQaulifications);
    Assertions.assertEquals(object1, object2);
  }
  
  @Test
  void testToString() {
    String workerString = workerName +":0:1:0.0";
    Assertions.assertEquals(workerString, testWorker.toString());
  }
  

}
