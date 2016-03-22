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
    private HuffmanNode child1;
    private HuffmanNode child2;
    
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
    
    public HuffmanNode (HuffmanNode child1, HuffmanNode child2)
    {
        this.child1 = child1;
        this.child2 = child2;
        value = child1.getValue() + child2.getValue();
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("|");
        if(character != null)
        {
            sb.append("Char: ");
            sb.append(character);
            sb.append(";");
        }
        if(value != null)
        {
            sb.append("Value: ");
            sb.append(value);
            sb.append(";");
        }
        if(child1 != null)
        {
            sb.append("Child1: ");
            sb.append(child1);
            sb.append(";");
        }
        if(child2 != null)
        {
            sb.append("Child2: ");
            sb.append(child2);
            sb.append(";");
        }
        sb.append("|");
        return sb.toString();
    }
}
