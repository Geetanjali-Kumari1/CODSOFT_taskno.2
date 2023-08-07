import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

//  word counter

public class Wordscounter {
    //defining a set of stop  words to be excluded the words count
    private static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList("a","to","an","should","would","in","of","and","you","she","he","could"));

    
    
    
    public static void main (String args[]){
    String input = getInput(); // get input from the user
        if (input.isEmpty()){
         JOptionPane.showMessageDialog(null,"No input provided."); // if nothing is provided as input then it will display en error message
         return;   
        }
        Map<String,Integer> wordsCounts = countWords(input);//  count frequency of each words in the given input
        int totalCount = wordsCounts.values().stream().mapToInt(Integer::intValue).sum();// count the total words
        int uniqueCount = wordsCounts.size(); // count the unique words
        JOptionPane.showMessageDialog(null,"Total words : " + totalCount + "\n Unique words :" + uniqueCount);
        if (!wordsCounts.isEmpty()){
            String frequencyTable = "Words \t Frequency\n"; // if any same word appear more than once, its frequency will be displayed
            for ( Map.Entry<String, Integer> entry : wordsCounts.entrySet()){
                frequencyTable += entry.getKey() + "\t" + entry.getValue() + "\n";
            }
            JOptionPane.showMessageDialog(null,frequencyTable);
        }
    }



   
   
    private static String getInput(){
        String input = "";
        JFileChooser filechooser = new JFileChooser();
        int choice = JOptionPane.showOptionDialog(null,"Do you want to enter text or select a file?","WORD COUNTER",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,new String[]{"Text","File"},"Text");

        if (choice == JOptionPane.YES_OPTION){
            input = JOptionPane.showInputDialog("Enter text:");
        }
        else if(choice ==JOptionPane.NO_OPTION){
            int result = filechooser.showOpenDialog(null);
            if (result ==JFileChooser.APPROVE_OPTION){
                try(BufferedReader reader = new BufferedReader(new FileReader(filechooser.getSelectedFile()))){
                    String line;
                    while ((line = reader.readLine()) != null){
                        input += line + "\n";
                        }
                           }
                           catch(IOException e){
                            JOptionPane.showMessageDialog(null,"Error reading file: "+ e.getMessage());
                           }}
       }
       return input.trim();
    }
    
    
        
       
    
    
    private static Map<String, Integer> countWords(String input){// count the frequency of each word excluding the stop words
                    Map<String,Integer> wordCounts = new HashMap<>();
                    String words[]= input.split("\\s+");// split the string into an array of words
                    
                for (String word : words){
                    if(!STOP_WORDS.contains(word.toLowerCase())){// excluding the stop words
                        
                wordCounts.put(word, wordCounts.getOrDefault(word,0)+ 1);// increases the count of words in the map
            }
        }
                return wordCounts;
                }

        }















