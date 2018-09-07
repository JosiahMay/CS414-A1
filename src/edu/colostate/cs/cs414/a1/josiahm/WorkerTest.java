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
  void resetQualifications() throws NullPointerException, InvalidDescription, InvalidQualifications {
    testQaulifications.clear();
    testQaulifications.add(new Qualification("Q 1"));
    testWorker = new Worker(workerName, testQaulifications);
  }

  @Test
  void testWorker() {
      try {
        testWorker = new Worker(workerName, testQaulifications);
      } catch (InvalidDescription | InvalidQualifications e) {
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
    Assertions.assertThrows(InvalidDescription.class, () -> {
      testWorker = new Worker("", testQaulifications);
      
    });
  }
  
  @Test
  void testWorkerInvalidNameSpaces() {
    Assertions.assertThrows(InvalidDescription.class, () -> {
      testWorker = new Worker("  ", testQaulifications);
      
    });
  }
  
  @Test
  void testWorkerValidDescriptionSpacesAndLetters() throws InvalidQualifications {
    try {
      testWorker = new Worker("  as", testQaulifications);
      Assertions.assertTrue(true);
    } catch (InvalidDescription | InvalidQualifications e) {
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

}
