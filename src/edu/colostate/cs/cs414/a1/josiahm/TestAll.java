package edu.colostate.cs.cs414.a1.josiahm;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({WorkerTest.class, ProjectTest.class, QualificationTest.class, CompanyTest.class})
/**
 * Class that runs all Junit5 tests in eclipse
 * @author Josiah May
 *
 */
public class TestAll {
}
