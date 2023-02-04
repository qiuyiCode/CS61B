package lab9tester;

import static org.junit.Assert.*;

import org.junit.Test;
import lab9.BSTMap;

import java.util.HashSet;
import java.util.Set;

/**
 * Tests by Brendan Hu, Spring 2015, revised for 2018 by Josh Hug
 */
public class TestBSTMap {

    @Test
    public void sanityGenericsTest() {
        try {
            BSTMap<String, String> a = new BSTMap<String, String>();
            BSTMap<String, Integer> b = new BSTMap<String, Integer>();
            BSTMap<Integer, String> c = new BSTMap<Integer, String>();
            BSTMap<Boolean, Integer> e = new BSTMap<Boolean, Integer>();
        } catch (Exception e) {
            fail();
        }
    }

    //assumes put/size/containsKey/get work
    @Test
    public void sanityClearTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1 + i);
            //make sure put is working via containsKey and get
            assertTrue(null != b.get("hi" + i));
            assertTrue(b.get("hi" + i).equals(1 + i));
            assertTrue(b.containsKey("hi" + i));
        }
        assertEquals(455, b.size());
        b.clear();
        assertEquals(0, b.size());
        for (int i = 0; i < 455; i++) {
            assertTrue(null == b.get("hi" + i) && !b.containsKey("hi" + i));
        }
    }

    // assumes put works
    @Test
    public void sanityContainsKeyTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertFalse(b.containsKey("waterYouDoingHere"));
        b.put("waterYouDoingHere", 0);
        assertTrue(b.containsKey("waterYouDoingHere"));
    }

    // assumes put works
    @Test
    public void sanityGetTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertEquals(null, b.get("starChild"));
        assertEquals(0, b.size());
        b.put("starChild", 5);
        assertTrue(((Integer) b.get("starChild")).equals(5));
        b.put("KISS", 5);
        assertTrue(((Integer) b.get("KISS")).equals(5));
        assertNotEquals(null, b.get("starChild"));
        assertEquals(2, b.size());
    }

    // assumes put works
    @Test
    public void sanitySizeTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertEquals(0, b.size());
        b.put("hi", 1);
        assertEquals(1, b.size());
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1);
        }
        assertEquals(456, b.size());
    }

    //assumes get/containskey work
    @Test
    public void sanityPutTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        b.put("hi", 1);
        assertTrue(b.containsKey("hi"));
        assertTrue(b.get("hi") != null);
    }

    @Test
    public void sanityKeySetTest(){
        BSTMap<Integer,String> b = new BSTMap<>();
        BSTMap<Integer,String> a = new BSTMap<>();
        a.put(3,"b");
        a.put(2,"c");
        a.put(1,"a");
        a.deleteMin();

        b.put(1,"f");
        b.put(2,"a");
        b.put(3,"c");
        b.deleteMin();

        Set<Integer> s1 = b.keySet();
        Set<Integer> s2 = a.keySet();
        assertTrue(s1.equals(s2));
    }

    @Test
    public void sanityMinTest(){
        BSTMap<Integer,String> a = new BSTMap<>();
        a.put(5,"a");
        a.put(3,"b");
        a.put(7,"c");
        a.put(1,"d");
        a.put(2,"e");
        int cmp = a.min();
        assertEquals(1,cmp);
    }

    @Test
    public void sanityremoveTest(){
        BSTMap<Integer,String> c = new BSTMap<>();
        c.put(5,"a");
        c.put(3,"b");
        c.put(7,"c");
        c.put(1,"d");
        c.put(2,"e");
        String v = c.remove(5,"a");

        Set<Integer> d = new HashSet<>();
        d.add(7);
        d.add(3);
        d.add(1);
        d.add(2);
        Set<Integer> e = c.keySet();
        assertTrue(e.equals(d));

        int f = c.min();
        assertEquals(f,1);
    }
    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestBSTMap.class);
    }
}
