import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ArrayLstTest {
    ArrayLst<T> list1 = new ArrayLst(new T[4], 0);
    ArrayLst list3 = new ArrayLst(new int[4], 0);
    ArrayLst list4 = new ArrayLst(new int[]{10, 20, 30, 40, 0, 0, 0, 0}, 4);
    ArrayLst list5 = new ArrayLst(new int[]{10, 20, 30, 40}, 4);
    ArrayLst list6 = new ArrayLst(new int[]{10, 20, 30, 50}, 4);
    ArrayLst list7 = new ArrayLst(new int[0], 0);
    ArrayLst list8 = new ArrayLst(new int[]{0, 0, 0}, 0);
    ArrayLst list9 = new ArrayLst(new int[3], 0);


    // Tests for equals method (exactly same ArrayLst and array length)
    @Test
    void equals_1() {
        assertEquals(list1, list3);
    }

    @Test
    void equals_2() {
        assertNotEquals(list5, list4);
    }

    @Test
    void equals_3() {
        assertNotEquals(list5, list6);
    }

    @Test
    void equals_4() {
        assertNotEquals(list7, list9);
    }

    @Test
    void equals_5() {
        assertEquals(list8, list9);
    }
}
