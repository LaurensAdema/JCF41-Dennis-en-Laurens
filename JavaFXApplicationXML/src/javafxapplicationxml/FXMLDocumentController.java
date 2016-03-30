/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplicationxml;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Laurens Adema
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TableView tableView;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn childrenColumn;
    @FXML
    private TreeView treeView;
    @FXML
    private Button btnAddConfirm;
    @FXML
    private ComboBox comboBox;
    @FXML
    private TextField tbName;

    private ObservableList<Classification> species;
    private boolean isEditing;
    private int lastSelected = -1;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        species = FXCollections.observableArrayList();
        comboBox.setItems(species);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Classification, String>("speciesName"));
        childrenColumn.setCellValueFactory(new PropertyValueFactory<Classification, List<Classification>>("children"));
        tableView.setItems(species);
        species.addListener(new ListChangeListener<Classification>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Classification> c)
            {
                if (lastSelected >= 0)
                {
                    treeView.setRoot(((Classification) tableView.getItems().get(lastSelected)).getAsTreeItem());
                }
            }
        });
        hardcodedSpecies();
    }

    @FXML
    private void btnGenerateTree_OnClick()
    {
        lastSelected = tableView.getSelectionModel().getSelectedIndex();
        treeView.setRoot(((Classification) tableView.getSelectionModel().getSelectedItem()).getAsTreeItem());
    }

    @FXML
    private void btnEditSpecies_OnClick()
    {
        if (tableView.getSelectionModel().getSelectedItem() != null)
        {
            isEditing = !isEditing;
            comboBox.setDisable(isEditing);
            if (isEditing)
            {
                btnAddConfirm.setText("Confirm");
                tbName.setText(((Classification) tableView.getSelectionModel().getSelectedItem()).getSpeciesName());
                comboBox.getSelectionModel().select(-1);
            } else
            {
                btnAddConfirm.setText("Add");
            }
        }
    }

    @FXML
    private void btnAddConfirm_OnClick()
    {
        if (isEditing)
        {
            if (!tbName.getText().isEmpty())
            {
                species.get(tableView.getSelectionModel().getSelectedIndex()).setSpeciesName(tbName.getText());
                isEditing = !isEditing;
                btnAddConfirm.setText("Add");
                tbName.setText("");
                comboBox.setDisable(false);
                tableView.refresh();
                treeView.refresh();
            }
        } else if (!tbName.getText().isEmpty() && comboBox.getSelectionModel().getSelectedIndex() >= 0)
        {
            Classification newClassification = new Classification(tbName.getText());
            species.get(comboBox.getSelectionModel().getSelectedIndex()).addChild(newClassification);
            species.add(newClassification);
            tbName.setText("");
            comboBox.getSelectionModel().select(-1);
            tableView.refresh();
            treeView.refresh();
        }
    }

    public void hardcodedSpecies()
    {
        Classification hominoidea = new Classification("Hominoidea");
        species.add(hominoidea);
        Classification hominidea = new Classification("Hominidea");
        species.add(hominidea);
        Classification hylobatidae = new Classification("Hylobatidae");
        species.add(hylobatidae);
        Classification homininae = new Classification("Homininae");
        species.add(homininae);
        Classification ponginae = new Classification("Ponginae");
        species.add(ponginae);
        Classification hominini = new Classification("Hominini");
        species.add(hominini);
        Classification gorillini = new Classification("Gorillini");
        species.add(gorillini);
        Classification homo = new Classification("Homo");
        species.add(homo);
        Classification pan = new Classification("Pan");
        species.add(pan);
        Classification gorilla = new Classification("Gorilla");
        species.add(gorilla);
        Classification pongo = new Classification("Pongo");
        species.add(pongo);
        Classification hylobates = new Classification("Hylobates");
        species.add(hylobates);
        hominoidea.addChild(hominidea);
        hominoidea.addChild(hylobatidae);
        hominidea.addChild(homininae);
        hominidea.addChild(ponginae);
        hylobatidae.addChild(hylobates);
        homininae.addChild(hominini);
        homininae.addChild(gorillini);
        ponginae.addChild(pongo);
        hominini.addChild(homo);
        hominini.addChild(pan);
        gorillini.addChild(gorilla);
    }

}
