package com.davispcook.asymmetrik;

import java.util.ArrayList;
import java.util.List;

/**
 * Takes a String passage and turns it into a List<String> of
 * sanitized tokens that only contain lowercase letters of the alphabet
 * by splitting the String passage on spaces and removing punctuation
 * 
 * @author Davis
 *
 */
public class PassageTokenizer {
	
	private PassageTokenizer() {}
	
	/**
	 * Creates a List<String> of sanitized tokens from the String fragment
	 * passed into the function
	 * @param fragment the String fragment to tokenize
	 * @return a List<String> of tokens that only contain lowercase letters of the alphabet and is 
	 */
	public static List<String> makeTokens(String fragment) {
		String processedFragment = preprocess(fragment);
		String[] unsanitizedTokens = separateTokens(processedFragment);
		ArrayList<String> sanitizedTokens = new ArrayList<String>(unsanitizedTokens.length);
		for (int i = 0; i < unsanitizedTokens.length; i++) {
			sanitizedTokens.add(sanitize(unsanitizedTokens[i]));
		}
		return sanitizedTokens;
	}
	
	/**
	 * Compacts whitespaces in the String fragment by replacing
	 * all sequences of whitespace characters with a single space
	 * such that the separateTokens function may better parse the
	 * tokens in this fragment
	 * 
	 * @param fragment the String fragment to compact whitespace characters in
	 * @return the String fragment with its whitespace compacted
	 */
	private static String compactWhitespace(String fragment) {
		return fragment.replaceAll("\\s+", " ");
	}
	
	/**
	 * Processes a String fragment to make it better suited for being tokenized
	 * and sanitized
	 * 
	 * This function could be made more complex to fit different requirements
	 * but for this basic implementation it simply removes leading and trailing
	 * whitespaces and compacts multiple whitespace characters into a single space
	 * 
	 * @param fragment the String fragment to be pre-processed
	 * @return the resultant String from processing the fragment
	 */
	private static String preprocess(String fragment) {
		return compactWhitespace(fragment).trim();
	}
	
	/**
	 * Separates a String fragment into tokens
	 * 
	 * This function could be made more complex to fit different requirements
	 * but for this basic implementation it simply splits the string on spaces
	 * 
	 * @param fragment The String fragment to separate into tokens
	 * @return a String[] of the tokens made from the fragment
	 */
	private static String[] separateTokens(String fragment) {
		return fragment.split(" ");
	}
	
	/**
	 * Checks if a character is a lowercase alphabet character
	 * 
	 * @param ch the character to check
	 * @return if the character is between 'a' and 'z'
	 */
	public static boolean isLowercaseAlphabetic(char ch) {
		return ((int) ch >= (int) 'a' && (int) ch <= (int) 'z');
	}
	
	/**
	 * Sanitizes a single token
	 * 
	 * This function could be made more complex to fit different requirements
	 * but for this basic implementation it simply converts the String to lowercase
	 * and then removes all non-alphabetic characters
	 * 
	 * An example of where this simple algorithm would fail is on hyphenated words
	 * because they will be inserted into the trie without the hyphen and will not
	 * show up as being hyphenated in the autocomplete suggestions - a possible solution
	 * to this problem would be to separate hyphenated words into two separate tokens
	 * in the separateTokens step or to make hyphens also allowed in the trie in addition to
	 * lowercase alphabet characters
	 * 
	 * @param token
	 * @return
	 */
	private static String sanitize(String token) {
		// convert to lowercase
		String lowercaseToken = token.toLowerCase();
		
		// remove characters that are not lowercase alphabet characters
		StringBuilder onlyAlphabeticBuilder = new StringBuilder();
		char[] lowercaseTokenAsCharArray = lowercaseToken.toCharArray();
		for (int i = 0; i < lowercaseTokenAsCharArray.length; i++) {
			char ch = lowercaseTokenAsCharArray[i];
			if (isLowercaseAlphabetic(ch)) {
				onlyAlphabeticBuilder.append(ch);
			}
		}
		return onlyAlphabeticBuilder.toString();
	}
	
}
