package com.davispcook.asymmetrik;

import java.util.LinkedList;
import java.util.List;

/**
 * An individual node in a trie
 * 
 * Must have an array of references to the child nodes for this node
 * Must have a char value to represent this node
 * Must have an integer count of the number of times this node has
 * been visited during insertions in order to allow for confidence rankings
 * of the autocomplete suggestions
 * 
 * @author Davis
 *
 */
public class TrieNode {

	private TrieNode[] children;
	private int count;
	private char value;
	
	public TrieNode(char value) {
		this.value = value;
		count = 0;
		children = new TrieNode[Trie.ALPHABET_SIZE];
	}
	
	/**
	 * Gets the child node of this trie node for a specific character
	 * 
	 * @param ch the character to get the child node for
	 * @return the child node for a specific character
	 */
	public TrieNode getChild(char ch) {
		return children[charToIndex(ch)];
	}
	
	/**
	 * Gets the count of how many times this word has appeared
	 * in the training data
	 * 
	 * @return the appearance count for this word
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * Insert an String ending into the trie at this node
	 * 
	 * @param ending the String to insert into the trie
	 */
	public void insertEnding(String ending) {
		/*
		 * if this was the last character of the ending to add then we are done
		 * with this ending and know that this node is the one to increment the
		 * word count for
		 */
		if (ending.length() == 0) {
			count++;
			return;
		}
		
		/*
		 * if we aren't done inserting the ending into the trie, 
		 * first make sure that a child node exists for the next
		 * character to insert
		 */
		int childIndex = charToIndex(ending.charAt(0));
		if (children[childIndex] == null) {
			children[childIndex] = new TrieNode(ending.charAt(0));
		}
		
		// then insert the rest of the ending into that child node
		children[childIndex].insertEnding(ending.substring(1));
	}
	
	/**
	 * Get all the candidate autocomplete suggestions for this
	 * node in the trie
	 * 
	 * @param prefixBuilder a StringBuilder containing the prefix defined by the trie up until this node
	 * @return a List<Candidate> of all the autocomplete suggetions from this node and its children
	 */
	public List<Candidate> getWords(StringBuilder prefixBuilder) {
		List<Candidate> candidates = new LinkedList<Candidate>();
		// push the value of this node onto the string builder
		prefixBuilder.append(value);
		
		// add this node to the candidate list if it represents a word
		if (count > 0) {
			candidates.add(new Candidate(prefixBuilder.toString(), count));
		}
		
		/*
		 * recursively add more candidates to the list of possible candidates
		 * by examining the possible autocomplete candidates for this node's children
		 */
		for (int i = 0; i < children.length; i++) {
			if (children[i] != null) {
				candidates.addAll(children[i].getWords(prefixBuilder));
			}
		}
		/*
		 * pop the value of this node off of the string builder so it returns to
		 * it's value before this node was traversed
		 */
		prefixBuilder.deleteCharAt(prefixBuilder.length() - 1);
		return candidates;
	}
	
	/**
	 * Converts a char to the index in the children array that it
	 * corresponds to by subtracting the value of 'a' from it such
	 * that 'a' is 0, 'b' is 1, ... and 'z' is 25
	 * 
	 * @param ch the char to convert to an array index
	 * @return the index in the children array that corresponds to the char
	 */
	private int charToIndex(char ch) {
		return ((int) ch - (int) 'a');
	}
	
}
