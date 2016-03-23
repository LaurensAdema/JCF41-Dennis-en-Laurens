/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author Gebruiker
 */
public class Huffman {

    private static HuffmanNode master;
    private static HashMap<Character, String> codeMap;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        String code = "wasserette";
        System.out.println(code);
        String temp = encode(code);
        System.out.println(temp);
        System.out.println(decode(temp));
    }

    private static String encode(String toCode)
    {
        StringBuilder codes = new StringBuilder();
        if (codeMap == null)
        {
            makeHuffmanTree(toCode);
        }

        // Loop door de chars van de string en controleer op de lijst met codes
        for (char charr : toCode.toCharArray())
        {
            codes.append(codeMap.get(charr));
        }
        return codes.toString();
    }
    
    private static String decode(String toCode)
    {
        StringBuilder codes = new StringBuilder();
        if (codeMap == null)
        {
            return "Dit gaat niet werken vriend";
        }
        HuffmanNode current = master;
        for (Character c : toCode.toCharArray())
        {
            if (current.getChild(c) == null)
            {
                codes.append(current.getChar());
                current = master;
            }
            if (current.getChild(c) != null)
            {
                current = current.getChild(c);
            }
        }
        codes.append(current.getChar());

        return codes.toString();
    }

    private static void makeHuffmanTree(String toCode)
    {
        PriorityQueue<HuffmanNode> huffmanQueue = makeHuffmanQueue(toCode);

        // Zet PriorityQueue om naar nodes
        while (huffmanQueue.size() > 1)
        {
            huffmanQueue.offer(new HuffmanNode(huffmanQueue.poll(), huffmanQueue.poll()));
        }

        master = huffmanQueue.poll();
        codeMap = master.generateCode();
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
        PriorityQueue<HuffmanNode> huffmanQueue = new PriorityQueue<>(tempHashMap.keySet().size());

        // Stop de hashmap in de Priority queue
        for (Map.Entry<Character, Integer> entry : tempHashMap.entrySet())
        {
            HuffmanNode node = new HuffmanNode(entry);
            huffmanQueue.offer(node);
        }

        return huffmanQueue;
    }
}
