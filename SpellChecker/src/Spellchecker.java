/**
 * The Spellchecker class will manage the database of all correctly
 * spelled words.  You will want to keep track of both the words
 * and the frequency at which those words appear.
 */
public class Spellchecker
{
	private WordCount [] database;
	private int index;
	
    //the constructor should use the FileReader class to read in all
    //of the words contained in the given file.  You will need to keep
    //track of each word and how often it appears
    public Spellchecker(String filename)
    {
        database = new WordCount[100];
        index = 0;
        
        FileReader fr = new FileReader(filename);  
        
        while (fr.hasNextWord())
        {
        	boolean AddorNot = true;
        	String WordtoAdd = fr.getNextWord();
        	
        	for (int i = 0; i < index; i++) {
        		if (WordtoAdd.equals(database[i].word)) {
            		database[i].count++;
            		AddorNot = false;
        		}
        	}
        	if (database.length == index) {
        		WordCount [] expanded = new WordCount[database.length * 2];
        		for (int i = 0; i < database.length; i++) {
        			expanded[i] = database[i];
        		}
        		database = expanded;
        	}	
        	if (AddorNot) {
        		database[index] = new WordCount();
        		database[index].word = WordtoAdd;
            	database[index].count = 1;
            	index++;
        	}      	
        }
    }
    
    //this method will return the correctly spelled word that is
    //the best match for the input word
    //do so by using the StringPermuter class to find permutations of 
    //misspeltWord, and see if any correctly spelled words match a permutation
    public String getBestMatch(String misspeltWord)
    {
        StringPermuter sp = new StringPermuter(misspeltWord);
        String bestMatchsoFar = "";
        int bestCountsoFar = -1;
        
        for (int i = 0; i < index; i++) {
     	   if (sp.isMatch(database[i].word)) {
     		   if (bestCountsoFar < database[i].count) {
     			  bestCountsoFar = database[i].count;
     			  bestMatchsoFar = database[i].word;
     		   }
     	   }
        }
        return bestMatchsoFar;
    }
    
    //below is a simple example of how you might use the Spellchecker class
    public static void main(String[] args)
    {
        Spellchecker sc = new Spellchecker("words.txt");
        String misspeltWord = "eboo";
        String bestMatch = sc.getBestMatch(misspeltWord);
        System.out.println("The best match for " + misspeltWord + " is: " + bestMatch);
    }
}
