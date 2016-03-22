/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author Gebruiker
 */
public class Huffman {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        makeHuffmanTree("bananen");
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

        // TEMP TO DISPLAY IT WORKZZZ
        int x = huffmanQueue.size();
        for (int i = 0; i < x; i++)
        {
            System.out.println(huffmanQueue.poll().getValue());
        }
        // END TEMP
    }
}
