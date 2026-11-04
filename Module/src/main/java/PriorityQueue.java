public record PriorityQueue(ArrayLst<HuffmanTree> collection) implements HQueue{

    // checks if queue is empty
    public boolean isEmpty(){
        return collection.size == 0;
    }

    // counts how many nodes in queue
    public int count(){
        return collection.size;
    }

    // adds HuffmanTree to queue preserving priority
    public void enqueue(HuffmanTree node) {
        if (collection.size == 0) {
            collection.array[1] = node;
            collection.size += 1;
        } else {
            int childIdx = collection.size + 1;
            int parentIdx = childIdx / 2;
            collection.array[childIdx] = node;
            collection.size += 1;
            while (childIdx > 1 && collection.array[childIdx].comesBefore(collection.array[parentIdx])) {
                HuffmanTree parent_temp = collection.array[parentIdx];
                collection.array[parentIdx] = collection.array[childIdx];
                collection.array[childIdx] = parent_temp;
                childIdx = parentIdx;
                parentIdx = childIdx / 2;
            }
        }
    }

    // removes the highest-priority HuffmanTree and maintains priority of queue
    public HuffmanTree dequeue() {
        HuffmanTree min = collection.array[1];
        int parentIdx = 1;
        collection.array[parentIdx] = collection.array[collection.size];
        collection.array[collection.size] = null;
        collection.size -= 1;
        while(parentIdx * 2 <= collection.size) {
            int childIdx = parentIdx * 2;
            if (childIdx + 1 <= collection.size && collection.array[childIdx + 1].comesBefore(collection.array[childIdx])) {
                childIdx += 1;
            }
            if (collection.array[childIdx].comesBefore(collection.array[parentIdx])) {
                HuffmanTree parent_temp = collection.array[parentIdx];
                collection.array[parentIdx] = collection.array[childIdx];
                collection.array[childIdx] = parent_temp;

                parentIdx = childIdx;
            } else {
                break;
            }
        }
        return min;
    }
}
