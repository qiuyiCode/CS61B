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
        OffByOne obo = new OffByOne();
        assertTrue(obo.equalChars('a', 'b'));
        assertTrue(obo.equalChars('r', 'q'));
        assertTrue(obo.equalChars('&', '%'));
        assertTrue(obo.equalChars('%', '&'));
        assertFalse(obo.equalChars('a', 'e'));
        assertFalse(obo.equalChars('z', 'a'));
        assertFalse(obo.equalChars('a', 'a'));
        assertFalse(obo.equalChars('A', 'a'));
        assertFalse(obo.equalChars('c', 'C'));
        assertFalse(obo.equalChars('$', '.'));
        assertFalse(obo.equalChars('1', '5'));
        assertFalse((obo.equalChars('c', '6')));
        assertTrue(obo.equalChars('2', '3'));
        assertTrue(obo.equalChars('3', '2'));
    }
}
