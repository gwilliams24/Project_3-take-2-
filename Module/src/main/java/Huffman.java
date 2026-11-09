import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Array;
import java.util.*;
import java.util.ArrayList;

/**
 * Project 3: Huffman Encoding / Decoding.
 *
 * Implement the methods below to compress and decompress text files using
 * Huffman coding. See the assignment write-up for the full specification.
 *
 *  - Treat characters as 8-bit values in the range [0, 255].
 *  - When writing output files, write the newline character explicitly as
 *    '\n' (do not rely on System.lineSeparator() / println()), so your
 *    output is identical across Windows/Mac/Linux. A BufferedWriter over
 *    a FileWriter, writing "\n" yourself, works well for this.
 *  - Encoded files store the code as literal '0'/'1' characters (one byte
 *    each), not as packed bits, so the "encoded" file will actually be
 *    larger than the original -- that's expected for this assignment.
 *  - createHuffTree() is built on top of addNode()/removeMin(), which
 *    operate generically over a java.util.Collection<HuffmanNode>.
 *  - HuffmanNode is a record, so its components are read with
 *    node.charAscii(), node.freq(), node.left(), and node.right() -- no
 *    "get" prefix.
 */
public class Huffman {

    /**
     * Number of possible 8-bit character values.
     */
    public static final int NUM_CHARS = 256;

    /**
     * Opens the text file named filename and counts the frequency of
     * occurrence of every character in it.
     * Returns an int array of size NUM_CHARS, indexed by ASCII value, with
     * the count of each character (0 for characters that do not occur).
     */
    public static int[] cntFreq(String filename) throws IOException {
        int[] freq = new int[NUM_CHARS];
        try {
            FileReader fileReader = new FileReader(filename);
            int i;
            while ((i = fileReader.read()) != -1) {
                freq[i] += 1;
            }
            fileReader.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return freq;
    }


    /**
     * Builds a Huffman tree from an array of character frequencies (as
     * produced by cntFreq()) and returns the root HuffmanNode of the tree.
     * <p>
     * Use an HQueue to manage the collection of
     * not-yet-combined nodes as you build the tree.
     * <p>
     * Special cases:
     * - If freq contains no non-zero counts (empty input file), return null
     * - If freq contains exactly one non-zero count (input file has only
     * one unique character), return a single leaf HuffmanNode for that
     * character (no combine() call needed).
     */
    public static HuffmanTree createHuffTree(int[] freq) {
        PriorityQueue lst = new PriorityQueue(new ArrayLst<HuffmanTree>(new HuffmanTree[257], 0));
        for (int i = 0; i < freq.length; i += 1) {
            if (freq[i] > 0) {
                lst.enqueue(new HuffmanLeaf(freq[i], (char) i));
            }
        }
        if (lst.count() == 0) {
            return null;
        } else if (lst.count() == 1) {
            HuffmanTree single_node = lst.dequeue();
            return new HuffmanLeaf(single_node.frequency(), (char) single_node.charascii());
        }
        while (lst.count() > 1) {
            HuffmanTree node1 = lst.dequeue();
            HuffmanTree node2 = lst.dequeue();
            lst.enqueue(node1.combine(node2));
        }
        return lst.dequeue();
    }

    /**
     * Traverses the Huffman tree rooted at root and builds the Huffman
     * code for every character that appears in it. Going left appends
     * '0' to the code, going right appends '1'.
     * <p>
     * Returns a String array of size NUM_CHARS, indexed by ASCII value.
     * Characters that do not appear in the tree have an empty string ""
     * at their index. The same is true of the lone character in a
     * single-node tree (the only-one-unique-character case) - it has no
     * left/right branch to build a code out of; huffmanEncode/huffmanDecode
     * handle that case entirely through the header.
     */
    public static String[] createCode(HuffmanTree root) {
        String[] codes = new String[NUM_CHARS];
        Arrays.fill(codes, "");
        if (root instanceof HuffmanLeaf) {
            return codes;
        } else if (root instanceof HuffmanNode) {
            pathCodes(root, "", codes);
        }
        return codes;
    }

    public static void pathCodes(HuffmanTree root, String path, String[] codes) {
        if (root instanceof HuffmanLeaf leaf) {
            codes[leaf.charascii()] = path;
        } else if (root instanceof HuffmanNode node) {
            pathCodes(node.left(), path + "0", codes);
            pathCodes(node.right(), path + "1", codes);
        }
    }

    /**
     * Given a frequency array (as produced by cntFreq()), returns a
     * header string listing the ASCII values and their associated
     * frequencies, separated by single spaces, in ascending order of
     * ASCII value. Only characters with a non-zero count are included.
     * <p>
     * Example: for the text "aaabbbbcc" this returns "97 3 98 4 99 2".
     */
    public static String createHeader(int[] freq) {
        StringBuilder header = new StringBuilder();
        for (int i = 0; i < freq.length; i += 1) {
            if (freq[i] > 0) {
                header.append(i).append(" ").append(freq[i]).append(" ");
            }
        }
        if (!header.isEmpty()) {
            int end = header.length() - 1;
            header.deleteCharAt(end);
        }
        return header.toString();
    }

    /**
     * Reads the input text file inFile and writes to outFile:
     * 1. A header line (see createHeader), followed by a newline
     * 2. The Huffman-encoded contents of inFile, using the codes from
     * createCode() -- UNLESS inFile has only one unique character,
     * see below.
     * If outFile already exists, its old contents are overwritten.
     * <p>
     * Special cases:
     * - Empty input file: outFile should end up empty too
     * - Input file with only one unique character: should contain only the
     * header line (e.g. "97 5") and nothing else - no second line of code bits.
     */
    public static void huffmanEncode(String inFile, String outFile) throws IOException {
        int[] freq = cntFreq(inFile);
        HuffmanTree root = createHuffTree(freq);
        BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));

        if (root == null) {
            writer.close();

        } else {
            String[] codes = createCode(root);
            String header = createHeader(freq);
            writer.write(header + "\n");
            try {
                FileReader reader = new FileReader(inFile);
                int i;
                while ((i = reader.read()) != -1) {
                    writer.write(codes[i]);
                }
                reader.close();
                writer.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Parses a header string (in the format produced by createHeader) and
     * returns the corresponding frequency array of size NUM_CHARS, indexed
     * by ASCII value, i.e. the same format returned by cntFreq().
     * <p>
     * Example: for the header "97 3 98 4 99 2", freq[97, 98, 99] == {3, 4, 2}.
     */
    public static int[] parseHeader(String headerString) {
        int[] freq = new int[NUM_CHARS];
        String[] header_array = headerString.split(" ");
        for (int i = 0; i < header_array.length; i += 2) {
            int ascii = Integer.parseInt(header_array[i]);
            int frequency = Integer.parseInt(header_array[i + 1]);
            freq[ascii] = frequency;
        }
        return freq;
    }

    /**
     * Reads an encoded file (in the format written by huffmanEncode) and
     * writes the decoded text to decodeFile.
     * <p>
     * Recreate the frequency array from the header with parseHeader(),
     * rebuild the Huffman tree with createHuffTree(), then walk the tree
     * for each bit of the second line: '0' -> left child, '1' -> right
     * child. When a leaf is reached, emit its character and restart at
     * the root.
     * <p>
     * Special case: if the recreated tree is a single leaf (the
     * only-one-unique-character case), there is no second line to read
     * The header's count alone tells you how many times that character occurred,
     * so write it out that many times directly.
     */

    public static void huffmanDecode(String encodedFile, String decodeFile) throws IOException {
        Path path = Path.of(encodedFile);
        String encoded = Files.readString(path);
        BufferedWriter writer = new BufferedWriter(new FileWriter(decodeFile)); // file being written to

        if (encoded.equals("")) {
            writer.close();
        } else {
            String[] lines = encoded.split("\n"); // breaks up header, code line

            if (lines.length == 1) { // in case of single character
                String[] header = lines[0].split(" ");
                int count = Integer.parseInt(header[1]);
                char chr = (char) Integer.parseInt(header[0]);
                for (int i = 0; i < count; i += 1) {
                    writer.write(chr);
                }
                writer.close();

            } else {
                int[] header = parseHeader(lines[0]); // reading header
                HuffmanTree tree = createHuffTree(header);
                StringBuilder string = new StringBuilder(lines[1]); // reading code line

                HuffmanTree spot = tree;
                for (int i = 0; i < string.length(); i += 1) {
                    if (string.charAt(i) == '0') {
                        spot = ((HuffmanNode) spot).left();
                    } else {
                        spot = ((HuffmanNode) spot).right();
                    }
                    if (spot instanceof HuffmanLeaf leaf) {
                        writer.write(leaf.charASCII());
                        spot = tree;
                    }
                }
                writer.close();
            }
        }
    }
}

