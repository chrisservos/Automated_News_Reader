
package efarmoges_texnitis;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.classifier4J.ITokenizer;
import net.sf.classifier4J.Utilities;
import net.sf.classifier4J.summariser.ISummariser;

public class SimpleSummariser implements ISummariser {

    private Integer findMaxValue(List input) {
        Collections.sort(input);
        return (Integer) input.get(0);
    }


    protected Set getMostFrequentWords(int count, Map wordFrequencies) {
        return Utilities.getMostFrequentWords(count, wordFrequencies);
    }

  
    @Override
    public String summarise(String input, int numSentences) {
        return summariseInternal(input, numSentences, -1, null);
    }


    protected String summariseInternal(String input, int numSentences, int minWordsInSentence, ITokenizer tokenizer) {
        // get the frequency of each word in the input
        Map wordFrequencies = Utilities.getWordFrequency(input);
        // now create a set of the X most frequent words
        Set mostFrequentWords = getMostFrequentWords(100, wordFrequencies);
        // break the input up into sentences
        // workingSentences is used for the analysis, but
        // actualSentences is used in the results so that the 
        // capitalisation will be correct.      
        String[] workingSentences = Utilities.getSentences(input.toLowerCase());
        String[] actualSentences = Utilities.getSentences(input);
        /*
        System.err.println("Sentences");
        for (int i = 0; i < actualSentences.length; i++) {
            System.err.println(actualSentences[i]);
        }
        */
        // iterate over the most frequent words, and add the first sentence 
        // that includes each word to the result
        Set outputSentences = new LinkedHashSet();
        Iterator it = mostFrequentWords.iterator();
        while (it.hasNext()) {
            String word = (String) it.next();
            for (int i = 0; i < workingSentences.length; i++) {               
                if (workingSentences[i].indexOf(word) >= 0) {
                    outputSentences.add(actualSentences[i]);
                    break;
                }
                if (outputSentences.size() >= numSentences) {
                    break;
                }
            }
            if (outputSentences.size() >= numSentences) {
                break;
            }

        }
        List reorderedOutputSentences = reorderSentences(outputSentences, input);
        StringBuffer result = new StringBuffer("");
        it = reorderedOutputSentences.iterator();
        while (it.hasNext()) {
            String sentence = (String) it.next();
            result.append(sentence);
            result.append("."); // This isn't always correct - perhaps it should be whatever symbol the sentence finished with
            if (it.hasNext()) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    /**
     * @param outputSentences
     * @param input
     * @return
     */
    private List reorderSentences(Set outputSentences, final String input) {
        // reorder the sentences to the order they were in the 
        // original text
        ArrayList result = new ArrayList(outputSentences);

        Collections.sort(result, new Comparator() {
            public int compare(Object arg0, Object arg1) {
                String sentence1 = (String) arg0;
                String sentence2 = (String) arg1;

                int indexOfSentence1 = input.indexOf(sentence1.trim());
                int indexOfSentence2 = input.indexOf(sentence2.trim());
                int result = indexOfSentence1 - indexOfSentence2;

                return result;
            }

        });
        return result;
    }


    /**
     * @see net.sf.classifier4J.summariser.ISummariser#getKeywords(java.lang.String, int)
     */
    public String[] getKeywords(String input, int numKeywords) {
        // get the frequency of each word in the input
        Map wordFrequencies = Utilities.getWordFrequency(input);

        //System.out.println(wordFrequencies);
        
        Set mostFrequentWords = getMostFrequentWords(numKeywords, wordFrequencies);
        //System.out.println(mostFrequentWords);
        String[] results = (String[]) mostFrequentWords.toArray(new String[mostFrequentWords.size()]);
        if (results.length <= numKeywords) {
            return results;
        } else {
            String[] newResults = new String[numKeywords];
            System.arraycopy(results, 0, newResults, 0, numKeywords);
            return newResults;
        }        
    }

}