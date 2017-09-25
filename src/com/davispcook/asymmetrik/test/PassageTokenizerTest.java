package com.davispcook.asymmetrik.test;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.davispcook.asymmetrik.PassageTokenizer;

public class PassageTokenizerTest {

	/*
	 * build a List<String> from a String array (String[])
	 */
	public List<String> buildStringList(String[] expectedTokensArray) {
		List<String> expectedTokensList = new LinkedList<String>();
		for (int i = 0; i < expectedTokensArray.length; i++) {
			expectedTokensList.add(expectedTokensArray[i]);
		}
		return expectedTokensList;
	}
	
	/**
	 * Tests if the PassageTokenizer makes the expected String array
	 * of tokens for a given String fragment
	 * 
	 * @param expectedTokensArray array of tokens that is expected
	 * @param fragment the fragment to parse with the PassageTokenizer
	 * @return if the tokens created with the PassageTokenizer matched the expected array of tokens
	 */
	public boolean testTokenResults(String[] expectedTokensArray, String fragment) {
		List<String> tokens = PassageTokenizer.makeTokens(fragment);
		List<String> expectedTokensList = buildStringList(expectedTokensArray);
		if (tokens.size() != expectedTokensList.size()) {
			return false;
		}
		tokens.sort(null);
		expectedTokensList.sort(null);
		Iterator<String> tokensIterator = tokens.iterator();
		Iterator<String> expectedTokensIterator = expectedTokensList.iterator();
		while (tokensIterator.hasNext()) {
			if (!tokensIterator.next().equals(expectedTokensIterator.next())) {
				return false;
			}
		}
		return true;
	}
	
	@Test
	public void test() {
		assertTrue("parses tokens", testTokenResults(
				new String[] {"bob", "is", "a", "man"}, "Bob is a man."));
		assertTrue("parses tokens with leading spaces", testTokenResults(
				new String[] {"bob", "is", "a", "man"}, "     Bob is a man."));
		assertTrue("parses tokens with leading white space", testTokenResults(
				new String[] {"bob", "is", "a", "man"}, "  \t \n   \r \f   Bob is a man."));
		assertTrue("parses tokens with trailing white space", testTokenResults(
				new String[] {"bob", "is", "a", "man"}, "Bob is a man.  \t \n   \r \f   "));
		assertTrue("parses tokens with interspersed white space", testTokenResults(
				new String[] {"bob", "is", "a", "man"}, "\t\r\n \tBob\nis\ta   \r \n \n\n man.  \t \n   \r \f   "));
		assertTrue("parses tokens with non alphabetic characters", testTokenResults(
				new String[] {"bob", "is", "a", "man"}, "B23o4b i$%s $%^a m@1a1-2n."));
	}

}
