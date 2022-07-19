package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class contains methods which, when used together, perform the
 * entire Huffman Coding encoding and decoding process
 * 
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 */
public class HuffmanCoding {
    /**
     * Writes a given string of 1's and 0's to the given file byte by byte
     * and NOT as characters of 1 and 0 which take up 8 bits each
     * 
     * @param filename The file to write to (doesn't need to exist yet)
     * @param bitString The string of 1's and 0's to write to the file in bits
     */
    public static void writeBitString(String filename, String bitString) {
        byte[] bytes = new byte[bitString.length() / 8 + 1];
        int bytesIndex = 0, byteIndex = 0, currentByte = 0;

        // Pad the string with initial zeroes and then a one in order to bring
        // its length to a multiple of 8. When reading, the 1 signifies the
        // end of padding.
        int padding = 8 - (bitString.length() % 8);
        String pad = "";
        for (int i = 0; i < padding-1; i++) pad = pad + "0";
        pad = pad + "1";
        bitString = pad + bitString;

        // For every bit, add it to the right spot in the corresponding byte,
        // and store bytes in the array when finished
        for (char c : bitString.toCharArray()) {
            if (c != '1' && c != '0') {
                System.out.println("Invalid characters in bitstring");
                System.exit(1);
            }

            if (c == '1') currentByte += 1 << (7-byteIndex);
            byteIndex++;
            
            if (byteIndex == 8) {
                bytes[bytesIndex] = (byte) currentByte;
                bytesIndex++;
                currentByte = 0;
                byteIndex = 0;
            }
        }
        
        // Write the array of bytes to the provided file
        try {
            FileOutputStream out = new FileOutputStream(filename);
            out.write(bytes);
            out.close();
        }
        catch(Exception e) {
            System.err.println("Error when writing to file!");
        }
    }
    
    /**
     * Reads a given file byte by byte, and returns a string of 1's and 0's
     * representing the bits in the file
     * 
     * @param filename The encoded file to read from
     * @return String of 1's and 0's representing the bits in the file
     */
    public static String readBitString(String filename) {
        String bitString = "";
        
        try {
            FileInputStream in = new FileInputStream(filename);
            File file = new File(filename);

            byte bytes[] = new byte[(int) file.length()];
            in.read(bytes);
            in.close();
            
            // For each byte read, convert it to a binary string of length 8 and add it
            // to the bit string
            for (byte b : bytes) {
                bitString = bitString + 
                String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            }

            // Detect the first 1 signifying the end of padding, then remove the first few
            // characters, including the 1
            for (int i = 0; i < 8; i++) {
                if (bitString.charAt(i) == '1') return bitString.substring(i+1);
            }
            
            return bitString.substring(8);
        }
        catch(Exception e) {
            System.out.println("Error while reading file!");
            return "";
        }
    }

    /**
     * Reads a given text file character by character, and returns an arraylist
     * of CharFreq objects with frequency > 0, sorted by frequency
     * 
     * @param filename The text file to read from
     * @return Arraylist of CharFreq objects, sorted by frequency
     */
    public static ArrayList<CharFreq> makeSortedList(String filename) {
        StdIn.setFile(filename);
        int[] A = new int[128];

        double total_char = 0;

        while (StdIn.hasNextChar()) {
            char charac =  StdIn.readChar();
            A[charac]++;
            total_char++;
        }

        ArrayList<CharFreq> P = new ArrayList<CharFreq>();

        for (int i = 0; i < A.length; i++) {
            if (A[i] >= 1) {
                int aV = i;
                char newChar = (char)aV;
                double num = A[i] / total_char;
                P.add(new CharFreq(newChar, num));
            }
        }

        if (P.size() == 1) {  // if there is distinct element
            int aV = (int)P.get(0).getCharacter();
            
            if (aV == 127) {
                char firstChr = (char)0;
                
                P.add(new CharFreq(firstChr, 0));
            }
            int aV2 = aV+1;
            char newCharac = (char)aV2;
            P.add(new CharFreq(newCharac, 0));
        }

        Collections.sort(P);

        return P; // Delete this line
    }

    /**
     * Uses a given sorted arraylist of CharFreq objects to build a huffman coding tree
     * 
     * @param sortedList The arraylist of CharFreq objects to build the tree from
     * @return A TreeNode representing the root of the huffman coding tree
     */
    public static TreeNode makeTree(ArrayList<CharFreq> sortedList) {
        /* Your code goes here */

        Queue<CharFreq> first = new Queue<CharFreq>();
        
        
        Queue<TreeNode> node = new Queue<TreeNode>();

        
        for (int i=0; i < sortedList.size(); i++) {
            first.enqueue(sortedList.get(i));
        }

        while (!first.isEmpty() || node.size() != 1) {
            TreeNode right = new TreeNode(null, null, null);
            TreeNode left = new TreeNode(null, null, null);


            for (int i = 0; i <= 1; i++) { 
                if (node.isEmpty()) {
                    CharFreq d = first.dequeue();
                    if (i == 0) {
                        left.setData(d);
                    } else {
                        right.setData(d);
                    }
                } else if (first.isEmpty()) {
                    TreeNode d = node.dequeue();
                    if (i == 0) {
                        left = d;
                    } else {
                        right = d;
                    }
                } else {
                    double smallTarget= node.peek().getData().getProbOccurrence();
                    double smallSource = first.peek().getProbOccurrence();
                    if (smallTarget>= smallSource) {
                        CharFreq d = first.dequeue();
                        if (i == 0) {
                            left.setData(d);
                        } else {
                            right.setData(d);
                        }
                    } else {
                        TreeNode d = node.dequeue();
                        if (i == 0) {
                            left = d;
                        } else {
                            right = d;
                        }
                    }
                }
            }         
            
            double Sum = left.getData().getProbOccurrence() + right.getData().getProbOccurrence();
            CharFreq NoneCharFreq = new CharFreq(null, Sum);
            TreeNode newNode = new TreeNode(NoneCharFreq, left, right);

            node.enqueue(newNode);           
        }
        
        return node.dequeue();
    }

    /**
     * Uses a given huffman coding tree to create a string array of size 128, where each
     * index in the array contains that ASCII character's bitstring encoding. Characters not
     * present in the huffman coding tree should have their spots in the array left null
     * 
     * @param root The root of the given huffman coding tree
     * @return Array of strings containing only 1's and 0's representing character encodings
     */
    public static String[] makeEncodings(TreeNode root) {
        
             String[] string = new String[128];

                through(string, root.getLeft(), "0");
        
             through(string, root.getRight(), "1");
        
        return string;
    }

    
    static void through(String[] string, TreeNode n, String p) {
            
            if(n==null) return;
            
            if (n.getData().getCharacter() != null) {
                
                char A = n.getData().getCharacter();
                
                string[(int)A] = p;
            }
            through(string,n.getLeft(), p+ "0");
           
           
            through(string,n.getRight(),p+ "1");
    }

    /**
     * Using a given string array of encodings, a given text file, and a file name to encode into,
     * this method makes use of the writeBitString method to write the final encoding of 1's and
     * 0's to the encoded file.
     * 
     * @param encodings The array containing binary string encodings for each ASCII character
     * @param textFile The text file which is to be encoded
     * @param encodedFile The file name into which the text file is to be encoded
     */
    public static void encodeFromArray(String[] code, String text, String encoded) {
        
        StdIn.setFile(text);
        String bitString = "";

        while (StdIn.hasNextChar()) {
            char chr = StdIn.readChar();
            bitString += code[chr];
        }
        
        writeBitString(encoded, bitString);
        
    }
    
    /**
     * Using a given encoded file name and a huffman coding tree, this method makes use of the 
     * readBitString method to convert the file into a bit string, then decodes the bit string
     * using the tree, and writes it to a file.
     * 
     * @param encodedFile The file which contains the encoded text we want to decode
     * @param root The root of your Huffman Coding tree
     * @param decodedFile The file which you want to decode into
     */
    public static void decode(String e, TreeNode root, String d) {
        StdOut.setFile(d);
        
        
        
        String bitString = readBitString(e);
        
        TreeNode firstroot = root;
        
        
        for (int i = 0; i < bitString.length(); i++) {
           
           
            char f = bitString.charAt(i);
            
            if (f == '0') {
                root = root.getLeft();
            } else {
                
                root = root.getRight();
            }

            if (root.getData().getCharacter() != null) {
                StdOut.print(root.getData().getCharacter());
                root = firstroot;
            }
        }
    }
}
