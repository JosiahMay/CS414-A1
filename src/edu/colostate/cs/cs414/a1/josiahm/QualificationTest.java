package edu.colostate.cs.cs414.a1.josiahm;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QualificationTest {
 
  private final String workerName = "Valid";
  private HashSet<Qualification> testQaulifications = new HashSet<Qualification>();
  private Worker testWorker;
  private Qualification testQual ;
  
  
  @BeforeEach
  void resetQualifications() throws NullPointerException, InvalidDescription, InvalidQualifications, InvalidName {
    
    testQaulifications.clear();
    
    testQual = new Qualification("Q 1");
    testQaulifications.add(testQual);
    testWorker = new Worker(workerName, testQaulifications);
  }

  @Test
  void testQualificationNullPointerException() {
    Assertions.assertThrows(NullPointerException.class, () -> {
      Qualification nullQualification = new Qualification(null);
      
    });
  }
  
  @Test
  void testQualificationInvalidDescriptionEmpty() {
    Assertions.assertThrows(InvalidDescription.class, () -> {
      Qualification invalidDescription = new Qualification("");
      
    });
  }
  @Test
  void testQualificationInvalidDescriptionSpaces() {
    Assertions.assertThrows(InvalidDescription.class, () -> {
      Qualification invalidDescription = new Qualification("  ");
      
    });
  }
  @Test
  void testQualificatioValidDescriptionSpacesAndLetters() throws NullPointerException, InvalidDescription {
    Qualification invalidDescription = new Qualification("  as");
      Assertions.assertTrue(true);
  }
  
  
  @Test
  void testEquals() throws NullPointerException, InvalidDescription {
    Qualification object1 = new Qualification("Test Qaulification");
    Qualification object2 = new Qualification("Test Qaulification");
    Assertions.assertEquals(object1, object2);
  }
  
  @Test
  void testNotEquals() throws NullPointerException, InvalidDescription {
    Qualification object1 = new Qualification("Test Qaulification");
    Qualification object2 = new Qualification("Test");
    Assertions.assertNotEquals(object1, object2);
  }
  
  @Test
  void testNotEqualsDiffObjects() throws NullPointerException, InvalidDescription {
    Qualification object1 = new Qualification("Test Qaulification");
    String object2 = "Test Qaulification";
    Assertions.assertNotEquals(object1, object2);
  }
  
  @Test
  void testAddWorker() {
	testQual.addWorker(testWorker);
	Assertions.assertTrue(true);
  }
  
  @Test
  void testAddProject() throws NullPointerException, InvalidName, InvalidQualifications {
    Project testProject = new Project("Vail Project", ProjectSize.LARGE, ProjectStatus.PLANNED, testQaulifications);
    testQual.addProject(testProject);
    Assertions.assertTrue(true);
  }
  
  @Test
  void testAddWorkerNull() {
    Assertions.assertThrows(NullPointerException.class, () -> {
      testQual.addWorker(null);
      
    });
  }
  
  @Test
  void testAddProjectNull(){
    Assertions.assertThrows(NullPointerException.class, () -> {
      testQual.addProject(null);
      
    });
  }
  
  @Test
  void testToString() {
    Assertions.assertEquals("Q 1", testQual.toString());
  }
  

}
