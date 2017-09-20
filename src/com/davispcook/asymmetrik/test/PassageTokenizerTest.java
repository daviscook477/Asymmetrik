package com.davispcook.asymmetrik.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.davispcook.asymmetrik.PassageTokenizer;

public class PassageTokenizerTest {

	// TODO: implement tests for various parts of the passage tokenizer functionality
	// to prove that it works and that I understand testing
	
	@Test
	public void test() {
		String testString = "The third thing that I need to tell you is that this thing does not think thoroughly.";
		List<String> tokens = PassageTokenizer.makeTokens(testString);
		for (String token : tokens) {
			System.out.println(token);
		}
		assertTrue(true);
	}

}
