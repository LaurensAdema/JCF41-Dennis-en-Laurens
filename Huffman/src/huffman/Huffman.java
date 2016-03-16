/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;

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
        System.out.println(countUniqueChars("bananen").size());
    }

    private static TreeMap<Character, Integer> countUniqueChars(String countString)
    {
        TreeMap<Character, Integer> distinctTest = new TreeMap<Character, Integer>();
        for (char character : countString.toCharArray())
        {
            if (!distinctTest.containsKey(character))
            {
                distinctTest.put(character, 1);
            } else
            {
                distinctTest.put(character, distinctTest.get(character) + 1);
            }
        }
        System.out.println(distinctTest);
        sortTreeMap(distinctTest);
        return distinctTest;
    }

    private static ArrayList<java.util.Map.Entry<Character,Integer>> sortTreeMap(TreeMap<Character, Integer> map)
    {
        ArrayList<Character> sortedList = new ArrayList<>();
        java.util.ArrayList<java.util.Map.Entry<Character,Integer>> sortedList2= new java.util.ArrayList<>();
        for (Map.Entry<Character, Integer> entrySet : map.entrySet())
        {
            Character key = entrySet.getKey();

            Integer value = entrySet.getValue();
            for (Character character : sortedList)
            {
                if (map.get(character) > value)
                {
                    sortedList2.add(sortedList.indexOf(character), new AbstractMap.SimpleEntry<>(key,value));
                    sortedList.add(sortedList.indexOf(character), key);
                    break;
                }
            }
            if (!sortedList.contains(key))
            {
                sortedList.add(key);
                sortedList2.add(new AbstractMap.SimpleEntry<>(key,value));
            }
        }
        System.out.println(sortedList);
        System.out.println(sortedList2);
        return sortedList2;
    }
}
