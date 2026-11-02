public record HuffmanLeaf (int freq, char charASCII) implements HuffmanTree{

    @Override
    public boolean comesBefore(HuffmanTree other) {
        if (this.freq < ((HuffmanLeaf)other).freq) {
            return true;
        } else if (this.freq == ((HuffmanLeaf)other).freq) {
            return (this.charASCII < ((HuffmanLeaf)other).charASCII);
        }
        return false;
    }

    @Override
    public HuffmanTree combine(HuffmanTree other) {
        int parentASCII;
        if (this.charASCII < ((HuffmanLeaf)other).charASCII) {
            parentASCII = this.charASCII;
        } else {
            parentASCII = ((HuffmanLeaf)other).charASCII;
        }
        if (comesBefore(other)) {
            return new HuffmanNode(parentASCII, this.freq + ((HuffmanLeaf)other).freq, this, other);
        } else {
            return new HuffmanNode(parentASCII, this.freq + ((HuffmanLeaf)other).freq, other, this);
        }
    }

    @Override
    public int compareTo(HuffmanTree other) {
        if (comesBefore(other)) {
            return -1;
        } else if (this.charASCII == ((HuffmanLeaf)other).charASCII) {
            return 0;
        }
        return 1;
    }
}
