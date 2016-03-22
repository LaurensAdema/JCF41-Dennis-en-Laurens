/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.Map;

/**
 *
 * @author Laurens Adema
 */
public class HuffmanNode {

    private Character character;
    private Integer value;
    
    public Character getChar()
    {
        return character;
    }
    
    public Integer getValue()
    {
        return value;
    }

    public HuffmanNode(Map.Entry<Character, Integer> entry)
    {
        character = entry.getKey();
        value = entry.getValue();
    }
}
