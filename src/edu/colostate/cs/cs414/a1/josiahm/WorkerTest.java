package edu.colostate.cs.cs414.a1.josiahm;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WorkerTest {
  
  private HashSet<Qualification> testQaulifications = new HashSet<Qualification>();
  private Worker testWorker;
  
  
  @BeforeEach
  void resetQualifications() throws NullPointerException, InvalidDescription {
    testQaulifications.clear();
    testQaulifications.add(new Qualification("Q 1"));
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
      Worker testWorker = new Worker("", testQaulifications);
      
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
    } catch (NullPointerException e) {
      fail(e.getMessage());
    } catch (InvalidDescription e) {
      fail(e.getMessage());
    }
  }
  
  @Test
  void testWorkerEmptyQualification() {
    Assertions.assertThrows(InvalidQualifications.class, () ->{
      testQaulifications.clear();
      testWorker = new Worker("Valid", testQaulifications);
    });
  }
  
  @Test
  void testWorkerNullQualification() {
    Assertions.assertThrows(NullPointerException.class, () ->{
      testWorker = new Worker("Valid", null);
    });
  }
  

}
