import java.util.LinkedList;
import java.util.Map;

public class HuffmanDecoder {
    public static void main(String[] args) {
        String readFile = args[0];
        ObjectReader rd = new ObjectReader(readFile);

        Object Trie = rd.readObject();
        BinaryTrie trie = (BinaryTrie) Trie;

        Object Size = rd.readObject();
        int size = (int) Size;

        Object BitSequence = rd.readObject();
        BitSequence bitSequence = (BitSequence) BitSequence;

        char[] chars = new char[size];
        for (int i = 0; i < size; i++) {
            Match match = trie.longestPrefixMatch(bitSequence);
            chars[i] = match.getSymbol();
            bitSequence = bitSequence.allButFirstNBits(match.getSequence().length());
        }

        FileUtils.writeCharArray(args[1],chars);
    }
}
