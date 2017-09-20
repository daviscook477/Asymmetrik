package com.davispcook.asymmetrik;

import java.util.List;

/**
 * Provides autocomplete functionality by using a trie data structure
 * 
 * The two main functions provided by this class are training the autocomplete provider
 * with new data through the train method and obtaining autocomplete candidates for a
 * word fragment using the getWords method
 * 
 * @author Davis
 *
 */
public class AutocompleteProvider {

	/*
	 * trie data structure used to implement the autocomplete service
	 */
	private Trie autocompleteTrie;
	
	public AutocompleteProvider() {
		// initialize the trie
		autocompleteTrie = new Trie();
	}
	
	/**
	 * Gets the list of autocomplete candidates for this String fragment
	 * ordered by confidence
	 * 
	 * @param fragment the String fragment to be used a prefix for searching the trie
	 * @return the List<Candidate> of autocomplete candidates ordered by confidence
	 */
	public List<Candidate> getWords(String fragment) {
		/* 
		 * obtain the autocomplete candidate list by querying the trie 
		 * for which words start with a prefix of fragment
		 * 
		 * however the returned list is NOT sorted so it must be sorted
		 * before being returned from this method
		 */
		List<Candidate> candidateList = autocompleteTrie.candidatesFor(fragment);
		
		/*
		 * do an in-place sort the list using the default Comparator for the Candidate class
		 * which ensures that the candidates are sorted by their confidence values
		 */
		candidateList.sort(null);
		return candidateList;
	}
	
	/**
	 * Trains the algorithm with the provided passage
	 * 
	 * @param passage the passage to use to train the algorithm
	 */
	public void train(String passage) {
		/* 
		 * split the passage into a List<String> of String tokens 
		 * where each token is a single word in the passage
		 */
		List<String> passageTokens = PassageTokenizer.makeTokens(passage);
		
		// insert each of the words in the passage into the trie
		for (String token : passageTokens) {
			autocompleteTrie.insertWord(token);
		}
	}
	
}
