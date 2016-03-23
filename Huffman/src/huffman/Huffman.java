/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author Gebruiker
 */
public class Huffman {

    private static HuffmanNode master;
    private static ArrayList<HuffmanNode> allNodes;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        String code = "wasserette";
        //makeHuffmanTree(code);
        String [] temp = encode(code);
        for(String s : temp)
        {
            System.out.print(s + " ");
        }
        System.out.println();
        System.out.println(decode(temp));
        System.out.println(master.drawTree());
    }

    private static PriorityQueue<HuffmanNode> makeHuffmanQueue(String countString)
    {
        // Maak temp hashmap om aantal letters te tellen
        HashMap<Character, Integer> tempHashMap = new HashMap<>();

        for (char character : countString.toCharArray())
        {
            if (!tempHashMap.containsKey(character))
            {
                tempHashMap.put(character, 1);
            } else
            {
                tempHashMap.put(character, tempHashMap.get(character) + 1);
            }
        }

        // Maak priority queue aanmaken met de size van de temp hashmap
        // Priority queue sorteert op value van de entryset
        PriorityQueue<HuffmanNode> huffmanQueue = new PriorityQueue<>(tempHashMap.keySet().size(), new Comparator<HuffmanNode>() {
            public int compare(HuffmanNode value1, HuffmanNode value2)
            {
                return value1.getValue().compareTo(value2.getValue());
            }
        });

        // Stop de hashmap in de Priority queue
        for (Map.Entry<Character, Integer> entry : tempHashMap.entrySet())
        {
            HuffmanNode node = new HuffmanNode(entry);
            huffmanQueue.offer(node);
        }

        return huffmanQueue;
    }

    private static void makeHuffmanTree(String toCode)
    {
        PriorityQueue<HuffmanNode> huffmanQueue = makeHuffmanQueue(toCode);

        // Zet PriorityQueue om naar nodes
        while (huffmanQueue.size() > 1)
        {
            HuffmanNode node1 = huffmanQueue.poll();
            if (node1 != null)
            {
                HuffmanNode node2 = huffmanQueue.poll();
                if (node2 != null)
                {
                    HuffmanNode parent = new HuffmanNode(node1, node2);
                    huffmanQueue.offer(parent);
                }
            }
        }
        master = huffmanQueue.poll();
        allNodes = master.generateTree();
    }

    private static String[] encode(String toCode)
    {
        String[] codes = new String[toCode.length()];
        if(allNodes == null)
        {
            makeHuffmanTree(toCode);
        }
        int i = 0;
        // Loop door de chars van de string en controleer op de lijst met codes
        for(char charr : toCode.toCharArray())
        {
            for(HuffmanNode node : allNodes)
            {
                if(node.getChar() == charr)
                {
                    codes[i] = node.getCode();
                }
            }
            i++;
        }
        return codes;
    }
    
    private static String decode(String[] toCode)
    {
        StringBuilder codes = new StringBuilder();
        if(allNodes == null)
        {
            return "Dit gaat niet werken vriend";
        }
        int i = 0;
        // Loop door de lijst met codes en kijk welke letters er bij horen
        for(String s : toCode)
        {
            for(HuffmanNode node : allNodes)
            {
                if(node.getCode()== s)
                {
                    codes.append(node.getChar());
                }
            }
            i++;
        }
        return codes.toString();
    }
}
