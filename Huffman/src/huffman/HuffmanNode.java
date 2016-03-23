/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
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

    private HuffmanNode getChild1()
    {
        return child1;
    }

    private HuffmanNode getChild2()
    {
        return child2;
    }

    public HuffmanNode(Map.Entry<Character, Integer> entry)
    {
        character = entry.getKey();
        value = entry.getValue();
    }

    public HuffmanNode(HuffmanNode child1, HuffmanNode child2)
    {
        this.child1 = child1;
        this.child2 = child2;
        value = child1.getValue() + child2.getValue();
    }

    public ArrayList<HuffmanNode> generateTree()
    {
        ArrayList<HuffmanNode> allNodes = new ArrayList<>();
        ArrayList<HuffmanNode> tempNodes = new ArrayList<>();
        allNodes.add(this);
        tempNodes.add(this);

        while (tempNodes.size() > 0)
        {
            ArrayList<HuffmanNode> tempIteratorNodes = new ArrayList<>();
            Iterator<HuffmanNode> i = tempNodes.iterator();
            while (i.hasNext())
            {
                HuffmanNode node = i.next();
                if (node.getChild1() != null)
                {
                    allNodes.add(node.getChild1());
                    tempIteratorNodes.add(node.getChild1());
                }
                if (node.getChild2() != null)
                {
                    allNodes.add(node.getChild2());
                    tempIteratorNodes.add(node.getChild2());
                }
                i.remove();
            }
            tempNodes.addAll(tempIteratorNodes);
        }
        return allNodes;
    }

    public String drawTree()
    {
        ArrayList<HuffmanNode> allNodes = generateTree();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (HuffmanNode node : allNodes)
        {
            for (int i = 0 + count; i < Math.ceil(allNodes.size() / 2); i++)
            {
                sb.append("\t");
            }
            sb.append(node);
            if (node.getChild1() != null || node.getChild2() != null)
            {
                sb.append(System.getProperty("line.separator"));
            }
            for (int i = 1 + count; i < Math.ceil(allNodes.size() / 2); i++)
            {
                sb.append("\t");
            }
            if (node.getChild1() != null)
            {
                sb.append(node.getChild1());
            }
            if (node.getChild2() != null)
            {
                sb.append("\t");
                sb.append(node.getChild2());
            }
            count++;
        }

        return sb.toString();
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("| ");
        if (character != null)
        {
            sb.append(character);
        } else
        {
            sb.append("*");
        }
        sb.append(" ; ");
        if (value != null)
        {
            sb.append(value);
        }
        sb.append(" |");
        return sb.toString();
    }
}
