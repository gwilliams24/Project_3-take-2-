import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HuffmanLeafTest {

    // tests for frequency:
    @Test
    void frequency_1(){
        assertEquals(2, new HuffmanLeaf(2, 'a').frequency());
    }

    @Test
    void frequency_2(){
        assertEquals(0, new HuffmanLeaf(0, 'b').frequency());
    }



    // tests for Ascii value:
    @Test
    void charAscii_1(){
        assertEquals(97, new HuffmanLeaf(2, 'a').charascii());
    }

    @Test
    void charAscii_2(){
        assertEquals(98, new HuffmanLeaf(9, 'b').charascii());
    }



    // tests for comesBefore:
    @Test
    void comes_before_1(){
        assertTrue(new HuffmanLeaf(9, 'a').comesBefore(new HuffmanLeaf(9, 'b')));
    }

    @Test
    void comes_before_2(){
        assertFalse(new HuffmanLeaf(4, 'c').comesBefore(new HuffmanNode('b', 3, new HuffmanLeaf(2 ,'b'), new HuffmanLeaf(1, 'a'))));
    }

    @Test
    void comes_before_3(){
        assertFalse(new HuffmanLeaf(2, 'd').comesBefore(new HuffmanLeaf(0, 'e')));
    }

    @Test
    void comes_before_4(){
        assertFalse(new HuffmanLeaf(2, 'g').comesBefore(new HuffmanNode(' ', 1, new HuffmanLeaf(1, ' '), new HuffmanLeaf (0, 'a'))));
    }


    // tests for combine:
    @Test
    void combine_1(){
        assertEquals(new HuffmanNode('a', 5, new HuffmanLeaf(2, 'a'), new HuffmanLeaf(3, 'b')) , new HuffmanLeaf(3, 'b').combine(new HuffmanLeaf(2, 'a')));
    }

    @Test
    void combine_2(){
        assertEquals(new HuffmanNode(' ' ,6, new HuffmanLeaf(3, ' '), new HuffmanLeaf(3, 'a')), new HuffmanLeaf(3, 'a').combine(new HuffmanLeaf(3, ' ')));
    }

    @Test
    void combine_3(){
        assertEquals(new HuffmanNode('a', 7, new HuffmanNode('c', 3, new HuffmanLeaf(1, 'd'), new HuffmanLeaf(2, 'c')), new HuffmanLeaf(4, 'a')),
                new HuffmanLeaf(4, 'a').combine(new HuffmanNode('c', 3, new HuffmanLeaf(1, 'd'), new HuffmanLeaf(2, 'c'))));
    }


    // tests for compareTo:
    @Test
    void compareTo1(){
        assertEquals(0, new HuffmanLeaf(4, 'd').compareTo(new HuffmanLeaf(4, 'd')));
    }

    @Test
    void compareTo2(){
        assertEquals(-1, new HuffmanLeaf(3, 'c').compareTo(new HuffmanLeaf(4, 'g')));
    }

    @Test
    void compareTo3(){
        assertEquals(1, new HuffmanLeaf(6, 'g').compareTo(new HuffmanLeaf(0, 'l')));
    }
}