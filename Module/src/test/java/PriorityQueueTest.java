import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueTest {

    HuffmanLeaf leaf1 = new HuffmanLeaf(1, 'a');
    HuffmanLeaf leaf2 = new HuffmanLeaf(2, 'b');
    HuffmanNode node1 = new HuffmanNode(' ', 3, new HuffmanLeaf(1, ' '), new HuffmanLeaf(2, 'c'));
    HuffmanNode node2 = new HuffmanNode('d', 6, new HuffmanLeaf(3, 'd'), new HuffmanLeaf(3, 'g'));

    PriorityQueue empty_queue = new PriorityQueue(new ArrayLst<HuffmanTree>(new HuffmanTree[5], 0));
    PriorityQueue queue_1 = new PriorityQueue(new ArrayLst<HuffmanTree>(new HuffmanTree[]{null, leaf2, null, null, null}, 1));
    PriorityQueue queue_2 = new PriorityQueue(new ArrayLst<HuffmanTree>(new HuffmanTree[]{null, leaf1, leaf2, null, null}, 2));
    PriorityQueue queue_3 = new PriorityQueue(new ArrayLst<HuffmanTree>(new HuffmanTree[]{null, leaf1, leaf2, node2, null}, 3));
    PriorityQueue queue_4 = new PriorityQueue(new ArrayLst<HuffmanTree>(new HuffmanTree[]{null, leaf1, leaf2, node2, node1}, 4));
    PriorityQueue queue_5 = new PriorityQueue(new ArrayLst<HuffmanTree>(new HuffmanTree[]{null, leaf2, node1, node2, null}, 3));
    PriorityQueue queue_6 = new PriorityQueue(new ArrayLst<HuffmanTree>(new HuffmanTree[]{null, leaf2, node2, null, null}, 2));


    // tests for isEmpty:
    @Test
    void isEmpty_1(){
        assertTrue(new PriorityQueue(new ArrayLst<HuffmanTree>(new HuffmanTree[10], 0)).isEmpty());
    }

    @Test
    void isEmpty_2(){
        assertFalse(new PriorityQueue(new ArrayLst<HuffmanTree>(new HuffmanTree[4], 1)).isEmpty());
    }



    // tests for count:
    @Test
    void count_1(){
        assertEquals(0, new PriorityQueue(new ArrayLst<HuffmanTree>(new HuffmanTree[1], 0)).count());
    }

    @Test
    void count_2(){
        assertEquals(1, new PriorityQueue(new ArrayLst<HuffmanTree>(new HuffmanTree[4], 1)).count());
    }



    // tests for enqueue:
    @Test
    void enqueue_1(){
        empty_queue.enqueue(leaf2);
        assertEquals(queue_1, empty_queue);
    }

    @Test
    void enqueue_2(){
        queue_1.enqueue(leaf1);
        assertEquals(queue_2, queue_1);
    }

    @Test
    void enqueue_3(){
        queue_2.enqueue(node2);
        assertEquals(queue_3, queue_2);
    }

    @Test
    void enqueue_4(){
        queue_3.enqueue(node1);
        assertEquals(queue_4, queue_3);
    }


    // tests for dequeue:
    @Test
    void dequeue_1a(){
        assertEquals(leaf2, queue_1.dequeue());
    }

    @Test
    void dequeue_1b(){
        queue_1.dequeue();
        assertEquals(queue_1, empty_queue);
    }

    @Test
    void dequeue_2a(){
        assertEquals(leaf1, queue_2.dequeue());
    }

    @Test
    void dequeue_2b(){
        queue_2.dequeue();
        assertEquals(queue_2, queue_1);
    }

    @Test
    void dequeue_3a(){
        assertEquals(leaf1, queue_4.dequeue());
    }

    @Test
    void dequeue_3b(){
        queue_4.dequeue();
        assertEquals(queue_4, queue_5);
    }

    @Test
    void dequeue_4a(){
        assertEquals(leaf1, queue_3.dequeue());
    }

    @Test
    void dequeue_4b(){
        queue_3.dequeue();
        assertEquals(queue_3, queue_6);
    }
}

