package edu.colostate.cs.cs414.a1.josiahm;

/**
 * Exception for invalid qualifications for a worker
 * @author josiahm
 * @version 1.0
 */
public class InvalidQualifications extends Exception {
  /**
   * Basic constructor for Invalid Qualifications for a worker
   * @param message The message for the exception
   */
  public InvalidQualifications(String message) {
    super(message);
  }

}
