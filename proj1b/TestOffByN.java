import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    @Test
    public void testEqualChars() {
        OffByN obn = new OffByN(5);
        assertTrue(obn.equalChars('a', 'f'));
        assertTrue(obn.equalChars('e', 'j'));
        assertFalse(obn.equalChars('a', 'c'));
        assertFalse(obn.equalChars('b', 'd'));
    }
}
