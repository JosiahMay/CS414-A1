package edu.colostate.cs.cs414.a1.josiahm;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class QualificationTest {
  
  

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
  void testQualificationInvalidDescriptionSpacesAndLetters() {
    try {
      Qualification invalidDescription = new Qualification("  as");
      Assertions.assertTrue(true);
    } catch (NullPointerException e) {
      fail(e.getMessage());
    } catch (InvalidDescription e) {
      fail(e.getMessage());
    }
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
  

}
