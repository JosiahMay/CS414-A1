package edu.colostate.cs.cs414.a1.josiahm;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProjectTest {
  
  private final String workerName = "Valid Worker";
  private final String projectName = "Valid Project";
  private HashSet<Qualification> testQaulifications = new HashSet<Qualification>();
  private Project testProject;
  
  
  @BeforeEach
  void resetQualifications() throws NullPointerException, InvalidName, InvalidQualifications, InvalidDescription {
    testQaulifications.clear();
    testQaulifications.add(new Qualification("Q 1"));
    
    testProject = new Project(projectName, ProjectSize.LARGE, ProjectStatus.PLANNED, testQaulifications);
    
    
  }

  @Test
  void testProject() {
      try {
        testProject = new Project(projectName, ProjectSize.LARGE, ProjectStatus.PLANNED, testQaulifications);
        Assertions.assertTrue(true);
      } catch (InvalidName | InvalidQualifications | NullPointerException e ) {
        fail(e.getMessage());
      }
      
  
  }
  
  @Test
  void testProjectNameNull() {
    Assertions.assertThrows(NullPointerException.class, () -> {
      testProject = new Project(null, ProjectSize.LARGE, ProjectStatus.PLANNED, testQaulifications);
      
    });
  }
  
  @Test
  void testProjectInvalidNameEmpty() {
    Assertions.assertThrows(InvalidDescription.class, () -> {
      testProject = new Project("", ProjectSize.LARGE, ProjectStatus.PLANNED, testQaulifications);
      
    });
  }
  
  @Test
  void testProjectInvalidNameSpaces() {
    Assertions.assertThrows(InvalidDescription.class, () -> {
      testProject = new Project("  ", ProjectSize.LARGE, ProjectStatus.PLANNED, testQaulifications);
      
    });
  }
  
  @Test
  void testProjectValidNameSpacesAndLetters() throws InvalidQualifications {
    try {
      testProject = new Project("  Vaild", ProjectSize.LARGE, ProjectStatus.PLANNED, testQaulifications);
      Assertions.assertTrue(true);
    } catch (InvalidName | InvalidQualifications e) {
      fail(e.getMessage());
    }
  }
  
  @Test
  void testProjectEmptyQualification() {
    Assertions.assertThrows(InvalidQualifications.class, () ->{
      testQaulifications.clear();
      testProject = new Project(projectName, ProjectSize.LARGE, ProjectStatus.PLANNED, testQaulifications);
    });
  }
  
  @Test
  void testProjectNullQualification() {
    Assertions.assertThrows(NullPointerException.class, () ->{
      testProject = new Project(projectName, ProjectSize.LARGE, ProjectStatus.PLANNED, null);
    });
  }

  @Test
  void testGetName() {
    fail("Not yet implemented");
  }

  @Test
  void testSizegetSize() {
    fail("Not yet implemented");
  }

  @Test
  void testGetStatus() {
    fail("Not yet implemented");
  }

  @Test
  void testSetStatus() {
    fail("Not yet implemented");
  }

  @Test
  void testMissingQualifications() {
    fail("Not yet implemented");
  }

  @Test
  void testIsHelpful() {
    fail("Not yet implemented");
  }

  @Test
  void testGetWorkers() {
    fail("Not yet implemented");
  }

  @Test
  void testAddQualification() {
    fail("Not yet implemented");
  }

  @Test
  void testEqualsObject() {
    fail("Not yet implemented");
  }

  @Test
  void testToString() {
    fail("Not yet implemented");
  }

  @Test
  void testToString1() {
    fail("Not yet implemented");
  }

}
