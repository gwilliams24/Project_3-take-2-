public class ArrayList <T> implements HQueue {
    T[] array;
    int size;

    public ArrayList(T[] array, int size) {
        this.array = array;
        this.size = size;
    }

    public boolean equals(Object other) {
        switch (other) {
            case ArrayList array_other:
                if ((this.size != array_other.size) || (this.array.length != array_other.array.length)){
                    return false;
                }
                for (int i = 0; i < this.array.length; i += 1) {
                    if (this.array[i] != array_other.array[i]) {
                        return false;
                    }
                }
                return true;
            case null, default:
                return false;}

        public static void addNode(ArrayList<HuffmanNode> collection, HuffmanNode node) {
            if (collection.size == 0) {
                collection.array[1] = node;
                collection.size += 1;
            }else{
                int childIdx = collection.size + 1;
                int parentIdx = (collection.size + 1)/2;
                while(HuffmanNode.comesBefore(collection.array[childIdx], collection.array[parentIdx]) && childIdx > 1) {
                    HuffmanNode parent_temp = collection.array[parentIdx];
                    collection.array[parentIdx] = collection.array[childIdx];
                    collection.array[childIdx] = parent_temp;

                    childIdx = parentIdx;
                    parentIdx = childIdx/2;
                    collection.size += 1;
                }
            }
    }
