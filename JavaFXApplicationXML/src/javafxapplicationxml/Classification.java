/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplicationxml;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TreeItem;

/**
 *
 * @author Laurens Adema
 */
public class Classification {

    private String speciesName;
    private List<Classification> children;

    public String getSpeciesName()
    {
        return speciesName;
    }

    public void setSpeciesName(String speciesName)
    {
        this.speciesName = speciesName;
    }

    public List<Classification> getChildren()
    {
        return children;
    }

    public Classification(String speciesName)
    {
        this.speciesName = speciesName;
        this.children = new ArrayList<>();
    }

    public void addChild(Classification child)
    {
        children.add(child);
    }

    @Override
    public String toString()
    {
        return speciesName;
    }

    public TreeItem<Classification> getAsTreeItem()
    {
        TreeItem<Classification> treeItem = new TreeItem<>(this);
        for (Classification child : children)
        {
            treeItem.getChildren().add(child.getAsTreeItem());
        }
        return treeItem;
    }
}
