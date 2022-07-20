/**
 * The StringPermuter class has a baseWord, and allows
 * someone to get permutations of that word.  The purpose
 * is to find a word that is "close" to baseWord.  This way, if 
 * baseWord is misspelled, we can find a word that's correctly
 * spelled which is "close" to baseWord, aka a spellchecker.
 */
public class StringPermuter
{
    private String baseWord;
    private String [] permutations;
    private int perm_index;
    
    public StringPermuter(String word) 
    {
        baseWord = word;
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        
        int size = (alphabet.length() * (word.length() + 1)) + word.length() + (word.length() - 1) + (alphabet.length() * word.length()); 
        permutations = new String[size];
        perm_index = 0;
        
        //ADDITIONS
        //counter = location of added letters
        for (int counter = 0; counter <= this.baseWord.length(); counter++)
        {
            for (int alpha_index = 0; alpha_index < alphabet.length(); alpha_index++)
            {
            	permutations[perm_index] = baseWord.substring(0, counter) + alphabet.charAt(alpha_index) + baseWord.substring(counter, baseWord.length());
                perm_index++;
            }
        }
        
        //DELETIONS
        for (int i = 0; i < this.baseWord.length(); i++)
        {
        	permutations[perm_index] = this.baseWord.substring(0,i) + this.baseWord.substring(i+1);
        	perm_index++;
        }
        
        //SWAPS
        for (int counter = 0; counter < this.baseWord.length() - 1; counter++)
        {
            String text = "";
            for (int i = 0; i < this.baseWord.length(); i++)
            {
                if (i == counter)
                {               
                    String part1 = this.baseWord.substring(0, counter);
                    part1 += this.baseWord.charAt(counter + 1);
                    String part2 = this.baseWord.substring(counter, counter + 1);
                    String part3 = this.baseWord.substring(counter + 2, this.baseWord.length());
                    text += (part1 + part2 + part3);
                }
            }
            permutations[perm_index] = text;
            perm_index++;
        }
        
        //EXCHANGES
        //counter = location of added letters
        for (int counter = 0; counter < this.baseWord.length(); counter++)
        {
            for (int alpha_index = 0; alpha_index < alphabet.length(); alpha_index++)
            {
            	permutations[perm_index] = baseWord.substring(0, counter) + alphabet.charAt(alpha_index) + baseWord.substring(counter + 1, baseWord.length());
                perm_index++;
            }
        }
        for (int k = 0; k < permutations.length; k++) {
			System.out.println(permutations[k]);
        }
    }
        
    public static void main(String[] args)
    {
        StringPermuter sp = new StringPermuter("hello");
    }
    
    //returns true if goodWord is a potential match for baseWord
    //false if it isn't
    //initially we will permute baseWord and see if any of the permutations 
    //match goodWord.  Later on we can make a list of permutations in 
    //the constructor, so we only need to generate the permutations once
    //instead of doing so everytime isMatch is called
    public boolean isMatch(String goodWord)
    {
    	for (int k = 0; k < permutations.length; k++) {
			if (goodWord.equals(permutations[k])) {
				return true;
			}
        }
    	return false;
    }
    
    
    //The methods below are to help practice writing the isMatch method
    
    //This method should modify the word by adding a letter to it
    //E.g. hello -> ahello, bhello, ... haello, hbello, ... helloz
    private void printAllAdditions() 
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        //counter = location of added letters
        for (int counter = 0; counter <= this.baseWord.length(); counter++)
        {
            for (int alpha_index = 0; alpha_index < alphabet.length(); alpha_index++)
            {
            	System.out.println(baseWord.substring(0, counter) + alphabet.charAt(alpha_index) + baseWord.substring(counter, baseWord.length()));
            }
        }
    }
    
    //Here we remove a letter from the word
    //hello -> ello, hllo, helo, helo, hell
    private void printAllDeletions() 
    {
        for (int i = 0; i < this.baseWord.length(); i++)
        {
            System.out.println(this.baseWord.substring(0,i) + this.baseWord.substring(i+1));
        }
    }
    
    //Here we look at swapping two adjacent letters
    //hello -> ehllo, hlelo, hello, helol
    private void printAllSwaps() 
    {
        for (int counter = 0; counter < this.baseWord.length() - 1; counter++)
        {
            String text = "";
            for (int i = 0; i < this.baseWord.length(); i++)
            {
                if (i == counter)
                {               
                    String part1 = this.baseWord.substring(0, counter);
                    part1 += this.baseWord.charAt(counter + 1);
                    String part2 = this.baseWord.substring(counter, counter + 1);
                    String part3 = this.baseWord.substring(counter + 2, this.baseWord.length());
                    text += (part1 + part2 + part3);
                }
            }
            System.out.println(text);
        }
    }
    
    //Here we exchange each letter with a different letter
    //hello -> aello, bello, cello, ... , hallo, hbllo, ... hellz
    private void printAllExchanges() 
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int counter = 0; counter < this.baseWord.length(); counter++)
        {
            for (int alpha_index = 0; alpha_index < alphabet.length(); alpha_index++)
            {
            	permutations[perm_index] = baseWord.substring(0, counter) + alphabet.charAt(alpha_index) + baseWord.substring(counter + 1, baseWord.length());
                perm_index++;
            }
        }
    }
}
