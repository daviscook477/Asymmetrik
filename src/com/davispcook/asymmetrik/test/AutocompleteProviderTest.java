package com.davispcook.asymmetrik.test;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.davispcook.asymmetrik.AutocompleteProvider;
import com.davispcook.asymmetrik.Candidate;

public class AutocompleteProviderTest {

	public  boolean compareLists(List<Candidate> list0, List<Candidate> list1) {
		if (list0.size() != list1.size()) {
			return false;
		}
		Iterator<Candidate> iter0 = list0.iterator();
		Iterator<Candidate> iter1 = list1.iterator();
		while (iter0.hasNext()) {
			if (!iter0.next().equals(iter1.next())) {
				return false;
			}
		}
		return true;
	}
	
	public boolean testAutocompleteResults(String trainingData, String testFragment, List<Candidate> expectedResults) {
		AutocompleteProvider acProvider = new AutocompleteProvider();
		acProvider.train(trainingData);
		List<Candidate> candidates = acProvider.getWords(testFragment);
		for (Candidate candidate : candidates) {
			System.out.println(candidate);
		}
		return compareLists(candidates, expectedResults);
	}
	
	public List<Candidate> buildCandidateList(String candidates) {
		if (candidates == null) {
			return new LinkedList<Candidate>();
		}
		String[] candidateTokens = candidates.split(" ");
		List<Candidate> candidateList = new LinkedList<Candidate>();
		for (int i = 0; i < candidateTokens.length; i += 2) {
			candidateList.add(new Candidate(
					candidateTokens[i],
					Integer.parseInt(candidateTokens[i + 1])));
		}
		return candidateList;
	}
	
	@Test
	public void test() {
		assertTrue("provides autocomplete results", testAutocompleteResults(
				"The third thing that I need to tell you is that this thing does not think thoroughly.",
				"th",
				buildCandidateList("that 2 thing 2 the 1 think 1 third 1 this 1 thoroughly 1"))
		);
		assertTrue("provides autocomplete results for an entire word", testAutocompleteResults(
				"The third thing that I need to tell you is that this thing does not think thoroughly.",
				"that",
				buildCandidateList("that 2"))
		);
		assertTrue("provides autocomplete results", testAutocompleteResults(
				"The third thing that I need to tell you is that this thing does not think thoroughly.",
				"thi",
				buildCandidateList("thing 2 think 1 third 1 this 1"))
		);
		assertTrue("provides autocomplete results for an empty string", testAutocompleteResults(
				"The third thing that I need to tell you is that this thing does not think thoroughly.",
				"",
				buildCandidateList("that 2 thing 2 does 1 i 1 is 1 need 1 not 1 tell 1 the 1 think 1 third 1 this 1 thoroughly 1 to 1 you 1"))
		);
		assertTrue("provides no autocomplete results for strings not contained in the training data", testAutocompleteResults(
				"The third thing that I need to tell you is that this thing does not think thoroughly.",
				"potato",
				buildCandidateList(null))
		);
	}

}
