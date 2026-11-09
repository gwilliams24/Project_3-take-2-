import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HuffmanNodeTest {

    // tests for frequency:
    @Test
    void frequency_1(){
        assertEquals(7, new HuffmanNode(' ', 7, new HuffmanLeaf(3, 'd'), new HuffmanLeaf(4, ' ')).frequency());
    }

    @Test
    void frequency_2(){
        assertEquals(10, new HuffmanNode('a', 10, new HuffmanLeaf(3, 'a'), new HuffmanLeaf(7, 'd')).frequency());
    }


    // tests for ascii value:
    @Test
    void ascii_1(){
        assertEquals(32, new HuffmanNode(' ', 7, new HuffmanLeaf(3, 'd'), new HuffmanLeaf(4, ' ')).charascii());
    }

    @Test
    void ascii_2(){
        assertEquals(97, new HuffmanNode('a', 10, new HuffmanLeaf(3, 'a'), new HuffmanLeaf(7, 'd')).charascii());
    }


    // tests for comesBefore:
    @Test
    void comes_before_1(){
        assertTrue(new HuffmanNode('b', 3, new HuffmanLeaf(2 ,'b'), new HuffmanLeaf(1, 'a')).comesBefore(new HuffmanLeaf(4, 'c')));
    }

    @Test
    void comes_before_2(){
        assertFalse(new HuffmanNode(' ', 1, new HuffmanLeaf(1, ' '), new HuffmanLeaf (0, 'a')).comesBefore(new HuffmanLeaf(0, 'd')));
    }


    // tests for combine:
    @Test
    void combine_1(){
        assertEquals((new HuffmanNode('a', 7, new HuffmanNode('c', 3, new HuffmanLeaf(1, 'd'), new HuffmanLeaf(2, 'c')), new HuffmanLeaf(4, 'a'))),
                new HuffmanNode('c', 3, new HuffmanLeaf(1, 'd'), new HuffmanLeaf(2, 'c')).combine(new HuffmanLeaf(4, 'a')));
    }

    @Test
    void combine_2(){
        assertEquals(new HuffmanNode(' ', 13, new HuffmanNode(' ', 6,  new HuffmanLeaf(3, ' '), new HuffmanLeaf(3, 'b')),
                new HuffmanNode('a', 7, new HuffmanNode('c', 3, new HuffmanLeaf(1, 'd'), new HuffmanLeaf(2, 'c')), new HuffmanLeaf(4, 'a'))),
                new HuffmanNode(' ', 6,  new HuffmanLeaf(3, ' '), new HuffmanLeaf(3, 'b')).combine(new HuffmanNode('a', 7, new HuffmanNode('c', 3, new HuffmanLeaf(1, 'd'), new HuffmanLeaf(2, 'c')), new HuffmanLeaf(4, 'a'))));
    }


    // tests for compare to:
    @Test
    void compareTo1(){
        assertEquals(0, new HuffmanNode('d', 4, new HuffmanLeaf(1, 'd'), new HuffmanLeaf(3, 'a')).compareTo(new HuffmanNode('d', 4, new HuffmanLeaf(1, 'd'), new HuffmanLeaf(3, 'a'))));
    }

    @Test
    void compareTo2(){
        assertEquals(-1, new HuffmanNode('d', 4, new HuffmanLeaf(1, 'd'), new HuffmanLeaf(3, 'a')).compareTo(new HuffmanNode('d', 6, new HuffmanLeaf(2, 'd'), new HuffmanLeaf(4, 'a'))));
    }


    @Test
    void compareTo3(){
        assertEquals(1, new HuffmanNode('d', 4, new HuffmanLeaf(1, 'd'), new HuffmanLeaf(3, 'a')).compareTo(new HuffmanLeaf(0, 'l')));
    }
}
