package edu.colostate.cs.cs414.a1.josiahm;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CompanyTest {
  
  private final String companyName = "Test Co";
  private final String workerName = "Worker 1";
  
  private Worker testWorker;
  private Company testCompany;


  @BeforeEach
  void setUp() throws NullPointerException, InvalidName {
    testCompany = new Company(companyName);
  }

  @AfterEach
  void tearDown() throws Exception {}

  @Test
  void testCompanyNameNull() {
    Assertions.assertThrows(NullPointerException.class, () -> {
      testCompany = new Company(null);
      
    });
  }
  
  @Test
  void testCompanyInvalidNameEmpty() {
    Assertions.assertThrows(InvalidName.class, () -> {
      testCompany = new Company("");   
    });
  }
  
  @Test
  void testCompanyInvalidNameSpaces() {
    Assertions.assertThrows(InvalidName.class, () -> {
      testCompany = new Company("  ");   
    });
  }
  
  @Test
  void testCompanyValidNameSpacesAndLetters() {
    try {
      testCompany = new Company("  Vaild");
      Assertions.assertTrue(true);
    } catch (InvalidName  e) {
      fail(e.getMessage());
    }
  }
  
  @Test
  void testHashCode() {
    fail("Not yet implemented");
  }

  @Test
  void testCompany() {
    fail("Not yet implemented");
  }

  @Test
  void testGetName() {
    Assertions.assertEquals(companyName, testCompany.getName());;
  }

  @Test
  void testGetAvailableWorkers() {
    fail("Not yet implemented");
  }

  @Test
  void testGetAssignedWorkers() {
    fail("Not yet implemented");
  }

  @Test
  void testGetUnassignedWorkers() {
    fail("Not yet implemented");
  }

  @Test
  void testGetProjects() {
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
  void testAddToAvailableWorkerPool() {
    fail("Not yet implemented");
  }

  @Test
  void testCreateProject() {
    fail("Not yet implemented");
  }

  @Test
  void testAssign() {
    fail("Not yet implemented");
  }

  @Test
  void testUnassign() {
    fail("Not yet implemented");
  }

  @Test
  void testUnassignAll() {
    fail("Not yet implemented");
  }

  @Test
  void testStart() {
    fail("Not yet implemented");
  }

  @Test
  void testFinish() {
    fail("Not yet implemented");
  }

  @Test
  void testEqualsObject1() {
    fail("Not yet implemented");
  }

  @Test
  void testToString1() {
    fail("Not yet implemented");
  }

}
