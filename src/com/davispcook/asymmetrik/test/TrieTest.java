package com.davispcook.asymmetrik.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.davispcook.asymmetrik.Candidate;
import com.davispcook.asymmetrik.PassageTokenizer;
import com.davispcook.asymmetrik.Trie;

public class TrieTest {

	@Test
	public void test() {
		Trie trie = new Trie();
		String testString = "The third thing that I need to tell you is that this thing does not think thoroughly.";
		List<String> tokens = PassageTokenizer.makeTokens(testString);
		for (String token : tokens) {
			trie.insertWord(token);
		}
		List<Candidate> candidates = trie.candidatesFor("th");
		for (Candidate candidate : candidates) {
			System.out.println("\"" + candidate.getWord() + "\" (" + candidate.getConfidence() + ")");
		}
		
		assertTrue(true);
	}

}
