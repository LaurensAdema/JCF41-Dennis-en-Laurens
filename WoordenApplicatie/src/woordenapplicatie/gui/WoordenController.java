package woordenapplicatie.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.sun.jndi.url.iiop.iiopURLContext;
import java.net.URL;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import static java.util.Collections.reverseOrder;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import static java.util.Map.Entry.comparingByValue;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author frankcoenen
 */
public class WoordenController implements Initializable {

    private static final String DEFAULT_TEXT = "Een, twee, drie, vier\n"
            + "Hoedje van, hoedje van\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "Heb je dan geen hoedje meer\n"
            + "Maak er één van bordpapier\n"
            + "Eén, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van, hoedje van\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "En als het hoedje dan niet past\n"
            + "Zetten we 't in de glazenkas\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier";

    @FXML
    private Button btAantal;
    @FXML
    private TextArea taInput;
    @FXML
    private Button btSorteer;
    @FXML
    private Button btFrequentie;
    @FXML
    private Button btConcordantie;
    @FXML
    private TextArea taOutput;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        taInput.setText(DEFAULT_TEXT);
    }

    @FXML
    private void aantalAction(ActionEvent event)
    {
        taOutput.setText("Aantal verschillende woorden:    " + Stream.of(getWords()).distinct().count() + "\n" + taOutput.getText());
        taOutput.setText("Totaal aantal woorden:           " + getWords().length + "\n" + taOutput.getText());
    }

    @FXML
    private void sorteerAction(ActionEvent event)
    {
        Stream<String> stream = Stream.of(getWords()).distinct().sorted();
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(stream.toArray(size -> new String[size])));
        Collections.reverse(list);
        String output = "";
        for (String string : list)
        {
            output += string + "\n";
        }
        taOutput.setText(output + taOutput.getText());
    }

    @FXML
    private void frequentieAction(ActionEvent event)
    {
        Stream<String> stream = Stream.of(getWords()).parallel();

        Map<String, Long> wordFreq = stream.collect(Collectors.groupingBy(String::toString, Collectors.counting()));
        Stream<Map.Entry<String, Long>> sorted = wordFreq.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));
        sorted.forEach(e ->
        {
            taOutput.setText(e.getKey() + ": " + e.getValue() + "\n" + taOutput.getText());
        });
    }

    @FXML
    private void concordatieAction(ActionEvent event)
    {
        String string = Normalizer.normalize(taInput.getText().trim().toLowerCase(), Normalizer.Form.NFD);
        string = string.replaceAll("[^\\p{ASCII}]", "");
        String[] lines = string.split("\\\n");
        ArrayList<String> words= new ArrayList<String>(Arrays.asList(getWords()));
        Collections.sort(words);
        Set<String> wordsSet = new TreeSet<String>(words);
        String output = "";
        for (String word : wordsSet)
        {
            ArrayList<Integer> positions = new ArrayList<>();      
            int i = 0;
            for (String line : lines)
            {
                i++;
                if (line.contains(word))
                {
                    positions.add(i);
                    /////////////////////////////////////////////////////////////continue;
                }                
            }
            output += word+": " + positions.toString() + "\n";
        }
        taOutput.setText(output + taOutput.getText());
    }

    private String[] getWords()
    {
        String string = Normalizer.normalize(taInput.getText().trim().toLowerCase(), Normalizer.Form.NFD);
        string = string.replaceAll("[^\\p{ASCII}]", "");
        String[] words = string.split("[\\s,\\.]+");
        
        return words;
    }

}
