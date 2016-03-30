/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplicationxml;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Laurens Adema
 */
public class Classification {

    private String speciesName;
    private List<Classification> children;
    //private Image image;

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

    /*
    public Image getImage()
    {
        return image;
    }

    public void setImage(String imageURL)
    {
        this.image = new Image(imageURL);
    }*/

    public Classification(String speciesName)
    {
        this.speciesName = speciesName;
        this.children = new ArrayList<>();
        //this.image = new Image("http://maagg.com/wp-content/uploads/2015/09/naruto-640x834-190x122.png");
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
