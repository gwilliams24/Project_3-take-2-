/**
 * A single node in a Huffman tree, implemented as a record.
 *
 * A HuffmanNode represents either a leaf (an actual character together
 * with its occurrence count) or an internal node created by combining two
 * subtrees during tree construction.
 *
 * Records are immutable and give you charAscii(), freq(), left(), and
 * right() accessor methods automatically, along with
 * equals()/hashCode()/toString(). Do not rename the components below.
 */
public record HuffmanNode(int charAscii, int freq, HuffmanTree left, HuffmanTree right)
        implements HuffmanTree{

    // returns the frequency of the HuffmanNode
    public int frequency(){
        return this.freq;
    }

    // returns the ascii integer value of the HuffmanNode
    public int charascii(){
        return this.charAscii;
    }


    // Returns true if the first parameter has a smaller frequency than the second
    // or if the same frequency, true if the first parameter has smaller Ascii value
     public boolean comesBefore(HuffmanTree other) {
         if (this.freq() < other.frequency()) {
             return true;
         } else if (this.freq() == other.frequency()) {
             return (this.charAscii() < other.charascii());
         }
         return false;
    }


    // combines two HuffmanTrees into a HuffmanNode
    public HuffmanNode combine(HuffmanTree other) {
        int parentASCII;
        if (this.charAscii() < other.charascii()) {
            parentASCII = this.charascii();
        } else {
            parentASCII = other.charascii();
        }
        if (comesBefore(other)) {
            return new HuffmanNode(parentASCII, this.freq + other.frequency(), this, other);
        } else {
            return new HuffmanNode(parentASCII, this.freq + other.frequency(), other, this);
        }
    }

    /**
     * Natural ordering consistent with comesBefore(), so that HuffmanNode
     * objects can be stored directly in a sorted structure (e.g. a
     * PriorityQueue or a sorted List) while building the tree.
     */
    // Compares HuffmanTrees together
    @Override
    public int compareTo(HuffmanTree other) {
        if (this.comesBefore(other)) return -1;
        if (other.comesBefore(this)) return 1;
        return 0;
    }
}
