import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit-tests for Set Algorithms
 * 
 * Run as JUnit test
 * @author forbes
 */
public class SetAlgorithmsTest
{
    SetAlgorithms mySets;
    // Some sample sets
    Set<String> test1, test2, test3;
    ArrayList<Set<String>> allSets;
    @Before
    public void setUp() throws Exception
    {
        mySets = new SetAlgorithms();

        test1 = mySets.makeSet(new Scanner("A B C C D"));
        test2 = mySets.makeSet(new Scanner("D"));
        test3 = mySets.makeSet(new Scanner("A B C"));
        allSets = new ArrayList<Set<String>>();
    }
    @Test
    public void testUnion()
    {
        // Hexadecimal 222A is the union (cup) symbol in Unicode 
        assertEquals("Fail on "+ test1 + " \u222A " + test2, 
                test1, mySets.union(test1, test2));
        // TODO: add more tests for testUnion
    }

    @Test
    public void testIntersection()
    {
     // Hexadecimal 2229 is the intersection (cap) symbol in Unicode 
        assertEquals("Fail on "+ test1 + " \u2229 " + test3, 
                test3, mySets.intersection(test1, test3));
        // TODO: add more tests for testIntersection
    }

    @Test
    public void testDifference()
    {
        // TODO: add tests for testDifference
        fail("No tests written for Difference");
    }

}
