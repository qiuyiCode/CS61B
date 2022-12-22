import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    // Uncomment this class once you've created your CharacterComparator interface
    // and OffByOne class.
    @Test
    public void testEqualChars() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('r', 'q'));
        assertTrue(offByOne.equalChars('&', '%'));
        assertTrue(offByOne.equalChars('%', '&'));
        assertFalse(offByOne.equalChars('a', 'e'));
        assertFalse(offByOne.equalChars('z', 'a'));
        assertFalse(offByOne.equalChars('A', 'a'));
        assertFalse(offByOne.equalChars('a', 'E'));
        assertFalse(offByOne.equalChars('E', 'a'));
        assertFalse(offByOne.equalChars('C', 'F'));
        assertTrue(offByOne.equalChars('A','B'));
        assertFalse(offByOne.equalChars('c', 'D'));
        assertFalse(offByOne.equalChars('a', 'B'));
        assertFalse(offByOne.equalChars('$', '.'));
        assertFalse((offByOne.equalChars('c', '6')));
    }
}
