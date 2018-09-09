package edu.colostate.cs.cs414.a1.josiahm;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CompanyTest {
  
  private final String companyName = "Test Co";
  private final String workerName = "Worker 1";
  private final String projectName = "Valid Project";
  private final ProjectSize projectSize =  ProjectSize.LARGE;
  private final ProjectStatus projectStatus = ProjectStatus.PLANNED;
  
  private HashSet<Qualification> testQualifications = new HashSet<Qualification>();
  private Worker testWorker;
  private Company testCompany;
  private Project testProject;


  @BeforeEach
  void setUp() throws NullPointerException, InvalidName, InvalidQualifications, InvalidDescription {
    testCompany = new Company(companyName);
    testQualifications.clear();
    testQualifications.add(new Qualification("Q 1"));
    testWorker = new Worker(workerName, testQualifications);
    testProject = new Project(projectName, projectSize, projectStatus, testQualifications);
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
  void testGetName() {
    Assertions.assertEquals(companyName, testCompany.getName());;
  }

  @Test
  void testGetAvailableWorkers() {
    HashSet<Worker> workers = new HashSet<Worker>();
    workers.add(testWorker);
    testCompany.addToAvailableWorkerPool(testWorker);
    
    Assertions.assertIterableEquals(workers, testCompany.getAvailableWorkers());
    
  }

  @Test
  void testGetAssignedWorkers() throws NullPointerException, InvalidName, InvalidQualifications {
    Project createdProject = testCompany.createProject(projectName, testQualifications, projectSize);
    testCompany.addToAvailableWorkerPool(testWorker);
    testCompany.assign(testWorker, createdProject);
    
    HashSet<Worker> workers = new HashSet<Worker>();
    workers.add(testWorker);
    
    Assertions.assertIterableEquals(workers, testCompany.getAssignedWorkers());
    
    
  }

  @Test
  void testGetUnassignedWorkers() throws NullPointerException, InvalidName, InvalidQualifications {
    Project createdProject = testCompany.createProject(projectName, testQualifications, projectSize);
    testCompany.addToAvailableWorkerPool(testWorker);
    testCompany.assign(testWorker, createdProject);
    
    Worker unassignedWorker = new Worker("Unassigned", testQualifications);
    testCompany.addToAvailableWorkerPool(unassignedWorker);
    
    HashSet<Worker> workers = new HashSet<Worker>();
    workers.add(unassignedWorker);
    
    Assertions.assertIterableEquals(workers, testCompany.getUnassignedWorkers());
  }


  @Test
  void testEqualsObject() throws NullPointerException, InvalidName {
    Company object1 = new Company(companyName);
    Company Object2 = new Company(companyName);
    Assertions.assertEquals(object1, Object2);
  }
  
  @Test
  void testNotEqualsObject() throws NullPointerException, InvalidName {
    Company object1 = new Company(companyName);
    Company Object2 = new Company("New Company");
    Assertions.assertNotEquals(object1, Object2);
  }

  @Test
  void testToString() {
    String companyString = companyName+ ":0:0";
    Assertions.assertEquals(companyString, testCompany.toString());
  }

  @Test
  void testAddToAvailableWorkerPoolTrue() {
    Assertions.assertTrue(testCompany.addToAvailableWorkerPool(testWorker));
  }
  
  @Test
  void testAddToAvailableWorkerPoolBusy() {
    testWorker.addProject(testProject);
    Assertions.assertFalse(testCompany.addToAvailableWorkerPool(testWorker));
  }
  
  @Test
  void testAddToAvailableWorkerPoolNull() {
    Assertions.assertThrows(NullPointerException.class, () -> {
      testCompany.addToAvailableWorkerPool(null);
      
    });
  }
  
  @Test
  void testAddToAvailableWorkerPoolDuplicate() {
    testCompany.addToAvailableWorkerPool(testWorker);
    Assertions.assertFalse(testCompany.addToAvailableWorkerPool(testWorker));
  }

  @Test
  void testCreateProject() throws NullPointerException, InvalidName, InvalidQualifications {
    Project createdProject = testCompany.createProject(projectName, testQualifications, projectSize);
    HashSet<Project> projects = new HashSet<Project>();
    projects.add(createdProject);
    Assertions.assertIterableEquals(projects, testCompany.getProjects());
  }

  @Test
  void testAssignTrue() throws NullPointerException, InvalidName, InvalidQualifications {
    Project createdProject = testCompany.createProject(projectName, testQualifications, projectSize);
    testCompany.addToAvailableWorkerPool(testWorker);
    Assertions.assertTrue(testCompany.assign(testWorker, createdProject));
  }
  
  @Test
  void testAssignFalseAlreadAssigned() throws NullPointerException, InvalidName, InvalidQualifications {
    Project createdProject = testCompany.createProject(projectName, testQualifications, projectSize);
    testCompany.addToAvailableWorkerPool(testWorker);
    testCompany.assign(testWorker, createdProject);
    Assertions.assertFalse(testCompany.assign(testWorker, createdProject));
  }
  
  @Test
  void testAssignFalseNotUseful() throws NullPointerException, InvalidName, InvalidQualifications, InvalidDescription {
    Project createdProject = testCompany.createProject(projectName, testQualifications, projectSize);
    
    testQualifications.clear();
    testQualifications.add(new Qualification("Q 2"));
    
    Worker unQualWorker = new Worker("Not useful", testQualifications);
    
    testCompany.addToAvailableWorkerPool(unQualWorker);
    Assertions.assertFalse(testCompany.assign(unQualWorker, createdProject));
  }
  
  @Test
  void testAssignFalseInvalidProjectActive() throws NullPointerException, InvalidName, InvalidQualifications, InvalidDescription {
    Project createdProject = testCompany.createProject(projectName, testQualifications, projectSize);
    testCompany.addToAvailableWorkerPool(testWorker);
    createdProject.setStatus(ProjectStatus.ACTIVE);
    Assertions.assertFalse(testCompany.assign(testWorker, createdProject));
  }
  
  @Test
  void testAssignFalseInvalidProjectFinished() throws NullPointerException, InvalidName, InvalidQualifications, InvalidDescription {
    Project createdProject = testCompany.createProject(projectName, testQualifications, projectSize); 
    
    testCompany.addToAvailableWorkerPool(testWorker);
    createdProject.setStatus(ProjectStatus.FINISHED);
    Assertions.assertFalse(testCompany.assign(testWorker, createdProject));
  }
  
  @Test
  void testAssignNullWorker() throws NullPointerException, InvalidName, InvalidQualifications, InvalidDescription {
    Project createdProject = testCompany.createProject(projectName, testQualifications, projectSize);
    
    Assertions.assertThrows(NullPointerException.class, () -> {
      testCompany.assign(null, createdProject);  
    });
  }
  
  @Test
  void testAssignNullProject() throws NullPointerException, InvalidName, InvalidQualifications, InvalidDescription {
    Assertions.assertThrows(NullPointerException.class, () -> {
      testCompany.assign(testWorker, null);  
    });
  }
  
  @Test
  void testAssignUnkownWorker() throws NullPointerException, InvalidName, InvalidQualifications, InvalidDescription {
    Project createdProject = testCompany.createProject(projectName, testQualifications, projectSize); 
    Assertions.assertFalse(testCompany.assign(testWorker, createdProject));
  }
  
  @Test
  void testAssignUnkownProject() throws NullPointerException, InvalidName, InvalidQualifications, InvalidDescription {
    testCompany.addToAvailableWorkerPool(testWorker);
    Assertions.assertFalse(testCompany.assign(testWorker, testProject));
  }
  
  @Test
  void testAssignOverloaded() throws NullPointerException, InvalidName, InvalidQualifications, InvalidDescription {
    
    Project createdProject = testCompany.createProject(projectName, testQualifications, projectSize);
    testCompany.addToAvailableWorkerPool(testWorker);
    
    testWorker.addProject(new Project("P1", ProjectSize.MEDIUM, ProjectStatus.ACTIVE, testQualifications));
    testWorker.addProject(new Project("P2", ProjectSize.LARGE, ProjectStatus.ACTIVE, testQualifications));
    testWorker.addProject(new Project("P3", ProjectSize.LARGE, ProjectStatus.ACTIVE, testQualifications));
    testWorker.addProject(new Project("P4", ProjectSize.LARGE, ProjectStatus.ACTIVE, testQualifications));
    
    Assertions.assertFalse(testCompany.assign(testWorker, createdProject));
  }

  @Test
  void testUnassign() throws NullPointerException, InvalidName, InvalidQualifications {
    Project createdProject = testCompany.createProject(projectName, testQualifications, projectSize);
    testCompany.addToAvailableWorkerPool(testWorker);
    testCompany.assign(testWorker, createdProject);
    
    Assertions.assertTrue(testCompany.unassign(testWorker, createdProject));  
  }
  
  @Test
  void testUnassignCheckStates() throws NullPointerException, InvalidName, InvalidQualifications {
    Project createdProject = testCompany.createProject(projectName, testQualifications, projectSize);
    testCompany.addToAvailableWorkerPool(testWorker);
    testCompany.assign(testWorker, createdProject);
    
    createdProject.setStatus(ProjectStatus.ACTIVE);
    testCompany.unassign(testWorker, createdProject);
    
    Assertions.assertTrue(createdProject.getStatus() == ProjectStatus.SUSPENDED);
    Assertions.assertIterableEquals(new HashSet<Worker>(), testCompany.getAssignedWorkers());
  }
  
  @Test
  void testUnassignNullWorker() throws NullPointerException, InvalidName, InvalidQualifications, InvalidDescription {
    Project createdProject = testCompany.createProject(projectName, testQualifications, projectSize);
    Assertions.assertThrows(NullPointerException.class, () -> {
      testCompany.unassign(null, createdProject);  
    });
  }
  
  @Test
  void testUnassignNullProject() throws NullPointerException, InvalidName, InvalidQualifications, InvalidDescription {
    Assertions.assertThrows(NullPointerException.class, () -> {
      testCompany.unassign(testWorker, null);  
    });
  }
  
  @Test
  void testUnassignUnkownWorker() throws NullPointerException, InvalidName, InvalidQualifications, InvalidDescription {
    Project createdProject = testCompany.createProject(projectName, testQualifications, projectSize); 
    Assertions.assertFalse(testCompany.unassign(testWorker, createdProject));
  }
  
  @Test
  void testUnassignUnkownProject() throws NullPointerException, InvalidName, InvalidQualifications, InvalidDescription {
    testCompany.addToAvailableWorkerPool(testWorker);
    Assertions.assertFalse(testCompany.unassign(testWorker, testProject));
  }
  

  @Test
  void testUnassignAll() throws NullPointerException, InvalidName, InvalidQualifications {
    Project createdProject1 = testCompany.createProject(projectName, testQualifications, projectSize);
    Project createdProject2 = testCompany.createProject(projectName+1, testQualifications, projectSize);
    testCompany.addToAvailableWorkerPool(testWorker);
    testCompany.assign(testWorker, createdProject1);
    testCompany.assign(testWorker, createdProject2);
    
    createdProject1.setStatus(ProjectStatus.ACTIVE);
    testCompany.unassignAll(testWorker);
    
    Assertions.assertTrue(createdProject1.getStatus() == ProjectStatus.SUSPENDED);
    Assertions.assertTrue(createdProject2.getStatus() == ProjectStatus.PLANNED);
  }

  @Test
  void testStartTruePlanned() throws NullPointerException, InvalidName, InvalidQualifications {
    Project createdProject = testCompany.createProject(projectName, testQualifications, projectSize);
    testCompany.addToAvailableWorkerPool(testWorker);
    testCompany.assign(testWorker, createdProject);
    
    Assertions.assertTrue(testCompany.start(createdProject));
    
  }
  
  @Test
  void testStartTrueSuspened() throws NullPointerException, InvalidName, InvalidQualifications {
    Project createdProject = testCompany.createProject(projectName, testQualifications, projectSize);
    testCompany.addToAvailableWorkerPool(testWorker);
    testCompany.assign(testWorker, createdProject);
    
    createdProject.setStatus(ProjectStatus.SUSPENDED);
    Assertions.assertTrue(testCompany.start(createdProject));
    
    
  }
  
  @Test
  void testStartFalseUnsatisfied() throws NullPointerException, InvalidName, InvalidQualifications {
    Project createdProject = testCompany.createProject(projectName, testQualifications, projectSize);
    Assertions.assertFalse(testCompany.start(createdProject));
 
  }
  
  @Test
  void testStartFalseActive() throws NullPointerException, InvalidName, InvalidQualifications {
    Project createdProject = testCompany.createProject(projectName, testQualifications, projectSize);
    testCompany.addToAvailableWorkerPool(testWorker);
    testCompany.assign(testWorker, createdProject);
    
    createdProject.setStatus(ProjectStatus.ACTIVE);
    Assertions.assertFalse(testCompany.start(createdProject));
    
  }
  
  @Test
  void testStartFalseOverloadWorker() throws NullPointerException, InvalidName, InvalidQualifications {
    Project createdProject = testCompany.createProject(projectName, testQualifications, projectSize);
    testCompany.addToAvailableWorkerPool(testWorker);
    testCompany.assign(testWorker, createdProject);
    
    for(int i=0; i<4; i++) {
      testWorker.addProject(new Project(projectName + i, projectSize, ProjectStatus.ACTIVE, testQualifications));
    }
    
    Assertions.assertFalse(testCompany.start(createdProject));
    
  }
  
  @Test
  void testStartNull() throws NullPointerException, InvalidName, InvalidQualifications, InvalidDescription {
    Assertions.assertThrows(NullPointerException.class, () -> {
      testCompany.start(null);  
    });
  }

  @Test
  void testFinish() throws NullPointerException, InvalidName, InvalidQualifications {
    Project createdProject = testCompany.createProject(projectName, testQualifications, projectSize);
    testCompany.addToAvailableWorkerPool(testWorker);
    testCompany.assign(testWorker, createdProject);
    
    createdProject.setStatus(ProjectStatus.ACTIVE);
    
    Assertions.assertTrue(testCompany.finish(createdProject));
    
  }
  
  @Test
  void testFinishStates() throws NullPointerException, InvalidName, InvalidQualifications {
    Project createdProject = testCompany.createProject(projectName, testQualifications, projectSize);
    testCompany.addToAvailableWorkerPool(testWorker);
    testCompany.assign(testWorker, createdProject);
    
    createdProject.setStatus(ProjectStatus.ACTIVE);
    
    testCompany.finish(createdProject);
    Assertions.assertIterableEquals(new HashSet<Worker>(), testCompany.getAssignedWorkers());
    
  }
  
  @Test
  void testFinishNull() throws NullPointerException, InvalidName, InvalidQualifications, InvalidDescription {
    Assertions.assertThrows(NullPointerException.class, () -> {
      testCompany.finish(null);  
    });
  }
  
  @Test
  void testFinishFalsePlanned() throws NullPointerException, InvalidName, InvalidQualifications {
    Project createdProject = testCompany.createProject(projectName, testQualifications, projectSize);
    testCompany.addToAvailableWorkerPool(testWorker);
    testCompany.assign(testWorker, createdProject);
    
    createdProject.setStatus(ProjectStatus.PLANNED);
    
    Assertions.assertFalse(testCompany.finish(createdProject));
  }
  
  @Test
  void testFinishFalseSuspended() throws NullPointerException, InvalidName, InvalidQualifications {
    Project createdProject = testCompany.createProject(projectName, testQualifications, projectSize);
    testCompany.addToAvailableWorkerPool(testWorker);
    testCompany.assign(testWorker, createdProject);
    
    createdProject.setStatus(ProjectStatus.SUSPENDED);
    
    Assertions.assertFalse(testCompany.finish(createdProject));
  }

}
