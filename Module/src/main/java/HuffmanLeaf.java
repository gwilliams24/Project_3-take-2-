public record HuffmanLeaf (int freq, char charASCII) implements HuffmanTree{

    // returns the frequency of the HuffmanLeaf
    public int frequency(){
        return this.freq;
    }

    // returns the Ascii integer value of the HuffmanLeaf
    public int charascii(){
        return charASCII;
    }

    // Returns true if the first parameter has a smaller frequency than the second
    // or if the same frequency, true if the first parameter has smaller Ascii value
    @Override
    public boolean comesBefore(HuffmanTree other) {
        if (this.freq < other.frequency()) {
            return true;
        } else if (this.freq == other.frequency()) {
            return (this.charASCII < other.charascii());
        }
        return false;
    }


    // combines two HuffmanTrees into a HuffmanNode
    @Override
    public HuffmanNode combine(HuffmanTree other) {
        int parentASCII;
        if (this.charASCII < other.charascii()) {
            parentASCII = this.charASCII;
        } else {
            parentASCII = other.charascii();
        }
        if (comesBefore(other)) {
            return new HuffmanNode(parentASCII, this.freq + other.frequency(), this, other);
        } else {
            return new HuffmanNode(parentASCII, this.freq + other.frequency(), other, this);
        }
    }

    // Compares HuffmanTrees together
    @Override
    public int compareTo(HuffmanTree other) {
        if (comesBefore(other)) {
            return -1;
        } else if (this.charASCII == other.charascii()) {
            return 0;
        }
        return 1;
    }
}
