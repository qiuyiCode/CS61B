import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
public class HuffmanEncoder {
    public static Map<Character,Integer> buildFrequencyTable(char[] inputSymbols){
        Map<Character,Integer> m = new HashMap<>();

        for(char c : inputSymbols){
            if(!m.containsKey(c)){
                m.put(c,1);
            }else{
                int count = m.get(c);
                m.put(c,count+1);
            }
        }
        return m;
    }

    public static void main(String[] args) {
        char[] eightBitSymbols = FileUtils.readFile(args[0]);
        Map<Character,Integer> frequencyTable = buildFrequencyTable(eightBitSymbols);
        BinaryTrie encoder = new BinaryTrie(frequencyTable);

        ObjectWriter objectWriter = new ObjectWriter(args[0] + ".huf");
        objectWriter.writeObject(encoder);
        objectWriter.writeObject(eightBitSymbols.length);

        Map<Character,BitSequence> lookUpTable = encoder.buildLookupTable();
        LinkedList<BitSequence> list = new LinkedList<>();

        for (char c : eightBitSymbols) {
            list.add(lookUpTable.get(c));
        }
        BitSequence allBitSequence = BitSequence.assemble(list);
        objectWriter.writeObject(allBitSequence);
    }
}
