package com.davispcook.asymmetrik.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.davispcook.asymmetrik.AutocompleteProvider;
import com.davispcook.asymmetrik.Candidate;

public class AutocompleteProviderTest {

	@Test
	public void test() {
		AutocompleteProvider acProvider = new AutocompleteProvider();
		acProvider.train("The third thing that I need to tell you is that this thing does not think thoroughly.");
		List<Candidate> candidates = acProvider.getWords("th");
		for (Candidate candidate : candidates) {
			System.out.println("\"" + candidate.getWord() + "\" (" + candidate.getConfidence() + ")");
		}
		
		assertTrue(true);
	}

}
