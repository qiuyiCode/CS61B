import com.sun.source.tree.UsesTree;
import edu.princeton.cs.algs4.MinPQ;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
public class BinaryTrie implements Serializable {
    Node root;
    private class Node implements Comparable<Node>,Serializable{
        public char aChar;
        public int freq;
        public Node left;
        public Node right;

        public Node(char c,int f,Node l,Node r){
            this.aChar = c;
            this.freq = f;
            this.left = l;
            this.right = r;
        }

        public boolean isLeaf(){
            return this.left == null && this.right == null;
        }

        @Override
        public int compareTo(Node o) {
            return this.freq - o.freq;
        }
    }

    public BinaryTrie(Map<Character,Integer> frequencyTable){
        MinPQ<Node> pq = new MinPQ<>();

        for(char c : frequencyTable.keySet()){
            pq.insert(new Node(c,frequencyTable.get(c),null,null));
        }

        while(pq.size() > 1){
            Node min1 = pq.delMin(),min2 = pq.delMin();
            pq.insert(new Node('\0',min1.freq + min2.freq,min1,min2));
        }
        this.root = pq.delMin();
    }

    public Match longestPrefixMatch(BitSequence querySequence){
        char c;
        BitSequence bitSequence;
        Node root = this.root;
        String str = "";

        for (int i = 0; i < querySequence.length() && !root.isLeaf(); i++) {
            if(querySequence.bitAt(i) == 0){
                str += '0';
                root = root.left;
            }else{
                str += '1';
                root = root.right;
            }
        }
        c = root.aChar;
        bitSequence = new BitSequence(str);
        return new Match(bitSequence,c);
    }

    private void helper(Map<Character,BitSequence> m,Node node,String s){
        if(node.isLeaf()){
            m.put(node.aChar,new BitSequence(s));
        }else{
            helper(m,node.left,s + '0');
            helper(m,node.right,s + '1');
        }
    }

    public Map<Character,BitSequence> buildLookupTable(){
        Map<Character,BitSequence> m = new HashMap<>();
        helper(m,root,"");
        return m;
    }
}
