package com.davispcook.asymmetrik;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * An implementation of the trie data structure
 * 
 * Tries are prefix trees where each node is a character that has
 * an array of pointers to the possible characters that may come after it
 * 
 * This allows for the determining of possible autocomplete suggestions by
 * traversing down the trie from the root node according to the provided prefix,
 * and then determining all of the possible character sequences that may come
 * after that prefix
 * 
 * @author Davis
 *
 */
public class Trie {

	public static final int ALPHABET_SIZE = 26;
	
	private TrieNode root;
	
	public Trie() {
		root = new TrieNode('\0');
	}
	
	/**
	 * Insert a word into the trie
	 * 
	 * @param word the word to be inserted
	 */
	public void insertWord(String word) {
		root.insertEnding(word);
	}
	
	/**
	 * Get all possible autocomplete candidates from this
	 * trie for a given prefix
	 * 
	 * @param prefix the prefix to find autocomplete candidates for
	 * @return a List<Candidate> of the possible autocomplete candidates
	 */
	public List<Candidate> candidatesFor(String prefix) {
		TrieNode current = root;
		int prefixIndex = 0;
		// traverse the trie down until the prefix has been exhausted
		while (prefixIndex < prefix.length()) {
			current = current.getChild(prefix.charAt(prefixIndex));
			prefixIndex++;
			if (current == null) {
				return new ArrayList<Candidate>();
			}
		}
		List<Candidate> candidateList = new LinkedList<Candidate>();
		
		// if the prefix itself represents a possible candidate, make sure to include it
		if (current.getCount() > 0) {
			candidateList.add(new Candidate(prefix, current.getCount()));
		}
		
		/*
		 * find all possible autocomplete candidates for this prefix by examining
		 * each of the child nodes of the prefix on the trie and using the recursive
		 * getWords method to get all possible endings to the prefix
		 */
		char ch = 'a';
		while ((int) ch <= (int) 'z') {
			TrieNode node = current.getChild(ch);
			if (node != null) {
				candidateList.addAll(node.getWords(new StringBuilder(prefix)));
			}
			ch++;
		}
		
		return candidateList;
	}
	
}
