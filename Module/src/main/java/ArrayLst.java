public class ArrayLst<T> {
    T[] array;
    int size;

    public ArrayLst(T[] array, int size) {
        this.array = array;
        this.size = size;
    }

    // equals method for ArrayLists:
    public boolean equals(Object other) {
        switch (other) {
            case ArrayLst array_other:
                if ((this.size != array_other.size) || (this.array.length != array_other.array.length)) {
                    return false;
                }
                for (int i = 0; i < this.array.length; i += 1) {
                    if (this.array[i] != array_other.array[i]) {
                        return false;
                    }
                }
                return true;
            case null, default:
                return false;
        }
    }
}
