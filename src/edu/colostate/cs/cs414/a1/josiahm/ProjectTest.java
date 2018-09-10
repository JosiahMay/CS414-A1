package edu.colostate.cs.cs414.a1.josiahm;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProjectTest {
  
  private final String workerName = "Valid Worker";
  private final String projectName = "Valid Project";
  private final ProjectSize projectSize =  ProjectSize.LARGE;
  private final ProjectStatus projectStatus = ProjectStatus.PLANNED;
  
  private HashSet<Qualification> testQaulifications = new HashSet<Qualification>();
  private Project testProject;
  
  
  @BeforeEach
  void resetQualifications() throws NullPointerException, InvalidName, InvalidQualifications, InvalidDescription {
    testQaulifications.clear();
    testQaulifications.add(new Qualification("Q 1"));
    
    testProject = new Project(projectName, projectSize, projectStatus, testQaulifications);
    
    
  }

  @Test
  void testProject() {
      try {
        testProject = new Project(projectName, projectSize, projectStatus, testQaulifications);
        Assertions.assertTrue(true);
      } catch (InvalidName | InvalidQualifications | NullPointerException e ) {
        fail(e.getMessage());
      }
      
  
  }
  
  @Test
  void testProjectNameNull() {
    Assertions.assertThrows(NullPointerException.class, () -> {
      testProject = new Project(null, projectSize, projectStatus, testQaulifications);
      
    });
  }
  
  @Test
  void testProjectInvalidNameEmpty() {
    Assertions.assertThrows(InvalidName.class, () -> {
      testProject = new Project("", projectSize, projectStatus, testQaulifications);
      
    });
  }
  
  @Test
  void testProjectInvalidNameSpaces() {
    Assertions.assertThrows(InvalidName.class, () -> {
      testProject = new Project("  ", projectSize, projectStatus, testQaulifications);
      
    });
  }
  
  @Test
  void testProjectValidNameSpacesAndLetters() throws InvalidQualifications {
    try {
      testProject = new Project("  Vaild", projectSize, projectStatus, testQaulifications);
      Assertions.assertTrue(true);
    } catch (InvalidName | InvalidQualifications e) {
      fail(e.getMessage());
    }
  }
  
  @Test
  void testProjectEmptyQualification() {
    Assertions.assertThrows(InvalidQualifications.class, () ->{
      testQaulifications.clear();
      testProject = new Project(projectName, projectSize, projectStatus, testQaulifications);
    });
  }
  
  @Test
  void testProjectNullQualification() {
    Assertions.assertThrows(NullPointerException.class, () ->{
      testProject = new Project(projectName, projectSize, projectStatus, null);
    });
  }

  @Test
  void testGetName() {
    Assertions.assertEquals(projectName, testProject.getName());
  }

  @Test
  void testGetSize() {
    Assertions.assertEquals(projectSize, testProject.getSize());
  }

  @Test
  void testGetStatus() {
    Assertions.assertEquals(projectStatus, testProject.getStatus());
  }

  @Test
  void testSetStatus() {
    testProject.setStatus(ProjectStatus.FINISHED);
    Assertions.assertEquals(ProjectStatus.FINISHED, testProject.getStatus());
  }

  @Test
  void testMissingQualificationsNoWorkers() {
    Assertions.assertIterableEquals(testQaulifications, testProject.missingQualifications());
  }
  
  @Test
  void testMissingQualificationsNone() throws NullPointerException, InvalidDescription, InvalidQualifications, InvalidName {
    Worker testWorker = new Worker(workerName, testQaulifications);
    testProject.addWorker(testWorker);
    Assertions.assertIterableEquals(new HashSet<Qualification>(), testProject.missingQualifications());
  }

  @Test
  void testMissingQualificationsOne() throws NullPointerException, InvalidDescription, InvalidQualifications, InvalidName {
    
    testProject.addQualification(new Qualification("Q 2"));
    Worker testWorker = new Worker(workerName, testQaulifications);
    testProject.addWorker(testWorker);
    
    HashSet<Qualification> missingQual = new HashSet<Qualification>();
    missingQual.add(new Qualification("Q 2"));
    
    Assertions.assertIterableEquals(missingQual, testProject.missingQualifications());
  }

  @Test
  void testAddWorkerTrue() throws NullPointerException, InvalidDescription, InvalidQualifications, InvalidName {
    Worker testWorker = new Worker(workerName, testQaulifications);
    Assertions.assertTrue(testProject.addWorker(testWorker));
    HashSet<Worker> workers = new HashSet<Worker>();
    workers.add(testWorker);
    Assertions.assertIterableEquals(workers, testProject.getWorkers());
    
  }
  
  @Test
  void testAddWorkerNull() throws NullPointerException, InvalidDescription, InvalidQualifications {
    Assertions.assertThrows(NullPointerException.class, () -> {
      testProject.addWorker(null);
    });
    
  }

  @Test
  void testIsHelpfulTrue() throws NullPointerException, InvalidDescription, InvalidQualifications, InvalidName {
    Worker testWorker = new Worker(workerName, testQaulifications);
    Assertions.assertTrue(testProject.isHelpful(testWorker));
  }
  
  @Test
  void testIsHelpfulFalse() throws NullPointerException, InvalidDescription, InvalidQualifications, InvalidName {
    testQaulifications.clear();
    testQaulifications.add(new Qualification("Q 2"));
    
    Worker testWorker = new Worker(workerName, testQaulifications);
    Assertions.assertFalse(testProject.isHelpful(testWorker));
  } 
  
  @Test
  void testGetWorkers() {
    Assertions.assertIterableEquals(new HashSet<Worker>(), testProject.getWorkers());
  }

  @Test
  void testAddQualificationNull() throws NullPointerException, InvalidDescription {
    Assertions.assertThrows(NullPointerException.class, () -> {
      testProject.addQualification(null);
    });
  }
  @Test
  void testAddQualificationTrue() throws NullPointerException, InvalidDescription {
    Assertions.assertTrue(testProject.addQualification(new Qualification("Q 2")));
  }
  
  @Test
  void testAddQualificationFalse() throws NullPointerException, InvalidDescription {
    Assertions.assertFalse(testProject.addQualification(new Qualification("Q 1")));
  }

  @Test
  void testEqualsObject() throws NullPointerException, InvalidName, InvalidQualifications {
    Project object1 = new Project(projectName, projectSize, projectStatus, testQaulifications);
    Project object2 = new Project(projectName, projectSize, projectStatus, testQaulifications);
    Assertions.assertEquals(object1, object2);
  }
  
  @Test
  void testNotEqualsObject() throws NullPointerException, InvalidName, InvalidQualifications {
    Project object1 = new Project(projectName, projectSize, projectStatus, testQaulifications);
    Project object2 = new Project("Not the Same", projectSize, projectStatus, testQaulifications);
    Assertions.assertNotEquals(object1, object2);
    
  }

  @Test
  void testToString() {
    String name = projectName + ":" + "0" + ":" + projectStatus;
    Assertions.assertEquals(name, testProject.toString());
  }
  
  @Test
  void testRemoveWorker() throws NullPointerException, InvalidDescription, InvalidQualifications, InvalidName {
    Worker testWorker = new Worker(workerName, testQaulifications);
    testProject.addWorker(testWorker);
    Assertions.assertTrue(testProject.removeWorker(testWorker));
  }
  
  @Test
  void testRemoveWorkerNull() {
    Assertions.assertThrows(NullPointerException.class, () -> {
      testProject.removeWorker(null);
    });
  }

}
