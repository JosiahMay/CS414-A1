package edu.colostate.cs.cs414.a1.josiahm;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.

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
  void testEquals() {
    try {
      Qualification object1 = new Qualification("Test Qaulification");
      Qualification object2 = new Qualification("Test Qaulification");
      Assertions.assertEquals(object1, object2);
    } catch (NullPointerException e) {
      fail(e.getMessage());
    } catch (InvalidDescription e) {
      fail(e.getMessage());
    }
  }
  

}
