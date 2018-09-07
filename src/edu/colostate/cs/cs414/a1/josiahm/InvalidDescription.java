package edu.colostate.cs.cs414.a1.josiahm;

/**
 * Exception for invalid descriptions for a qualification
 * @author josiahm
 * @version 1.0
 *
 */
public class InvalidDescription extends Exception {
  
  /**
   * Basic constructor for Invalid Descriptions for a qualification
   * @param message The message for the exception
   */
  public InvalidDescription(String message) {
    super(message);
  }
  
}
