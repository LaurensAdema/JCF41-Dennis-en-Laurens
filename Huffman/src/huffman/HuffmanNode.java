/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Laurens Adema
 */
public class HuffmanNode implements Comparable<HuffmanNode> {

    private Character character;
    private Integer value;
    private String code;
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

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getCode()
    {
        return code;
    }

    public HuffmanNode(Map.Entry<Character, Integer> entry)
    {
        character = entry.getKey();
        value = entry.getValue();
        code = "";
    }

    public HuffmanNode(HuffmanNode child1, HuffmanNode child2)
    {
        this.child1 = child1;
        this.child2 = child2;
        value = child1.getValue() + child2.getValue();
        code = "";
    }    

    public HashMap<Character, String> generateCode()
    {
        HashMap<Character, String> codes = new HashMap<>();
        postOrder(codes, this, "");

        return codes;
    }

    public void postOrder(HashMap<Character, String> map, HuffmanNode hn, String code)
    {
        if (hn.getChild1() != null)
        {
            postOrder(map, hn.getChild1(), code + "0");
        }
        if (hn.getChild2() != null)
        {
            postOrder(map, hn.getChild2(), code + "1");
        } else
        {
            map.put(hn.getChar(), code);
        }
    }

    public HuffmanNode getChild(Character c)
    {
        if (c.equals('0'))
        {
            return child1;
        }
        if (c.equals('1'))
        {
            return child2;
        }
        return null;
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

    @Override
    public int compareTo(HuffmanNode other)
    {
        return getValue().compareTo(other.getValue());
    }
    
    // OUDE CODE
    public ArrayList<HuffmanNode> generateTree()
    {
        ArrayList<HuffmanNode> allNodes = new ArrayList<>();
        ArrayList<HuffmanNode> loopParentNodes = new ArrayList<>();
        if (character != null)
        {
            allNodes.add(this);
        }
        loopParentNodes.add(this);

        // De begin node voegt zijn children toe aan een temp list mits er een char verbonden is aan de node
        // Vervolgens voegen zijn children hun children toe aan een andere lijst, wordt de eerste temp lijst leeg gegooid en de nieuwe children toegevoegd
        // Dit proces herhaalt tot er geen kinderen meer zijn
        // Alle nodes die voorbij komen worden toegevoegd aan een hoofdlist, dit is de tree.
        while (loopParentNodes.size() > 0)
        {
            ArrayList<HuffmanNode> newChildrenToAddNodes = new ArrayList<>();
            Iterator<HuffmanNode> i = loopParentNodes.iterator();
            while (i.hasNext())
            {
                HuffmanNode node = i.next();
                if (node.getChild1() != null)
                {
                    node.getChild1().setCode(node.getCode() + "0");
                    if (node.getChild1().getChar() != null)
                    {
                        allNodes.add(node.getChild1());
                    }
                    newChildrenToAddNodes.add(node.getChild1());
                }
                if (node.getChild2() != null)
                {
                    node.getChild2().setCode(node.getCode() + "1");
                    if (node.getChild2().getChar() != null)
                    {
                        allNodes.add(node.getChild2());
                    }
                    newChildrenToAddNodes.add(node.getChild2());
                }
                i.remove();
            }
            loopParentNodes.addAll(newChildrenToAddNodes);
        }
        return allNodes;
    }

    public String drawTree()
    {
        if (code == null)
        {
            generateTree();
        }
        ArrayList<HuffmanNode> loopParentNodes = new ArrayList<>();
        loopParentNodes.add(this);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < getDepth(0); i++)
        {
            sb.append("\t");
        }
        sb.append(this);
        sb.append(System.getProperty("line.separator"));
        int b = 0;
        while (loopParentNodes.size() > 0)
        {
            b++;
            ArrayList<HuffmanNode> newChildrenToAdd = new ArrayList<>();
            Iterator<HuffmanNode> i = loopParentNodes.iterator();
            for (int v = 0 + b; v < getDepth(0); v++)
            {
                sb.append("\t");
            }
            while (i.hasNext())
            {
                HuffmanNode node = i.next();
                if (node.getChild1() != null)
                {
                    sb.append(node.getChild1());
                    newChildrenToAdd.add(node.getChild1());
                }
                if (node.getChild2() != null)
                {
                    sb.append("\t");
                    sb.append(node.getChild2());
                    newChildrenToAdd.add(node.getChild2());
                }
                i.remove();
            }
            sb.append(System.getProperty("line.separator"));
            loopParentNodes.addAll(newChildrenToAdd);
        }
        return sb.toString();
    }

    public int getDepth(int i)
    {
        i++;
        if (child1 == null && child2 == null)
        {
            return i;
        }
        if (child1 == null)
        {
            return child2.getDepth(i);
        }
        if (child2 == null)
        {
            return child1.getDepth(i);
        }
        return Math.max(child1.getDepth(i), child2.getDepth(i));
    }
}
