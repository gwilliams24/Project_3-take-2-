import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ArrayLstTest {
    ArrayLst<Integer> list1 = new ArrayLst<>(new Integer[4], 0);
    ArrayLst<Integer> list3 = new ArrayLst<>(new Integer[4], 0);
    ArrayLst<Integer> list4 = new ArrayLst<>(new Integer[]{10, 20, 30, 40, 0, 0, 0, 0}, 4);
    ArrayLst<Integer> list5 = new ArrayLst<>(new Integer[]{10, 20, 30, 40}, 4);
    ArrayLst<Integer> list6 = new ArrayLst<>(new Integer[]{10, 20, 30, 50}, 4);
    ArrayLst<Integer> list7 = new ArrayLst<>(new Integer[0], 0);
    ArrayLst<Integer> list8 = new ArrayLst<>(new Integer[]{0, 0, 0}, 1);
    ArrayLst<Integer> list9 = new ArrayLst<>(new Integer[3], 0);
    ArrayLst<Integer> list10 = new ArrayLst<>(new Integer[]{10, 20, 30, 40}, 4);


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
    void equals_5(){assertNotEquals(list9, null);}

    @Test
    void equals_6(){assertNotEquals(list9, 9);}

    @Test
    void equals_7(){assertEquals(list10, list5);}

    @Test
    void equals_8(){assertNotEquals(list8, list9);}
}

