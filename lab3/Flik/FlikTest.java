import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {
    @Test
    public void testisSameNumber() {
        int n1 = 0, n2 = -1;
        assertFalse(Flik.isSameNumber(n1, n2));

        int n3 = 6, n4 = 6;
        assertTrue(Flik.isSameNumber(n3, n4));

        assertTrue(Flik.isSameNumber(128, 128));

    }
}
