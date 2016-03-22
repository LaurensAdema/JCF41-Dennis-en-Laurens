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

    private static PriorityQueue<Map.Entry<Character, Integer>> makeHuffmanQueue(String countString)
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
        PriorityQueue<Map.Entry<Character, Integer>> huffmanQueue = new PriorityQueue<>(tempHashMap.keySet().size(), new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> value1, Map.Entry<Character, Integer> value2)
            {
                return value1.getValue().compareTo(value2.getValue());
            }
        });

        // Stop de hashmap in de Priority queue
        huffmanQueue.addAll(tempHashMap.entrySet());

        return huffmanQueue;
    }

    private static void makeHuffmanTree(String toCode)
    {
        PriorityQueue<Map.Entry<Character, Integer>> huffmanQueue = makeHuffmanQueue(toCode);

        // TEMP TO DISPLAY IT WORKZZZ
        int x = huffmanQueue.size();
        for (int i = 0; i < x; i++)
        {
            System.out.println(huffmanQueue.poll());
        }
        // END TEMP

        // Eerste 2 nodes waar een boom mee moet beginnen
        Map.Entry<Character, Integer> value1 = huffmanQueue.poll();
        Map.Entry<Character, Integer> value2 = huffmanQueue.poll();
    }
}
