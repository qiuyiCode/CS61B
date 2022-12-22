import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    //Uncomment this class once you've created your CharacterComparator interface and OffByOne class.
    @Test
    public void testEqualChars(){
      OffByOne obo = new OffByOne();
      assertTrue(obo.equalChars('a','b'));
      assertTrue(obo.equalChars('b','a'));
      assertTrue(obo.equalChars('h','i'));
      assertTrue(obo.equalChars('y','x'));
      assertTrue(obo.equalChars('v','u'));
      assertFalse(obo.equalChars('a','c'));
      assertFalse(obo.equalChars('b','d'));
    }

}
