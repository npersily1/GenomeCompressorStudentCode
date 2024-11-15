/******************************************************************************
 *  Compilation:  javac GenomeCompressor.java
 *  Execution:    java GenomeCompressor - < input.txt   (compress)
 *  Execution:    java GenomeCompressor + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *  Data files:   genomeTest.txt
 *                virus.txt
 *
 *  Compress or expand a genomic sequence using a 2-bit code.
 ******************************************************************************/

/**
 * The {@code GenomeCompressor} class provides static methods for compressing
 * and expanding a genomic sequence using a 2-bit code.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @author Zach Blick
 * @author Noah Persily
 */
public class GenomeCompressor {

    /**
     * Reads a sequence of 8-bit extended ASCII characters over the alphabet
     * { A, C, T, G } from standard input; compresses and writes the results to standard output.
     */

    public static void compress() {
        // Map for letters to numbers
        int[] map = new int['T' + 1];
        map['A'] = 0b00;
        map['C'] = 0b01;
        map['G'] = 0b10;
        map['T'] = 0b11;


        // Write the header to the file
        String s = BinaryStdIn.readString();
        int length = s.length();
        BinaryStdOut.write(length);

        // Write each corresponding each letter's corresponding code
        for (int i = 0; i < length; i++) {
            BinaryStdOut.write(map[s.charAt(i)], 2);
        }

        BinaryStdOut.close();
    }

    /**
     * Reads a binary sequence from standard input; expands and writes the results to standard output.
     */
    public static void expand() {
        // Inverse of the map above
        char[] map = new char[4];
        map[0b00] = 'A';
        map[0b01] = 'C';
        map[0b10] = 'G';
        map[0b11] = 'T';

        // Read in the header
        int length = BinaryStdIn.readInt();

        // Write out the letter that corresponds to every two bit code
        for (int i = 0; i < length; i++) {
            BinaryStdOut.write(map[BinaryStdIn.readInt(2)]);
        }

        BinaryStdOut.close();
    }


    /**
     * Main, when invoked at the command line, calls {@code compress()} if the command-line
     * argument is "-" an {@code expand()} if it is "+".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        if (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}