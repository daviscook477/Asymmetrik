package com.davispcook.asymmetrik;

/**
 * An individual autocomplete candidate
 * 
 * Provides access to the actual word that is this candidate and
 * to the confidence level of this autocomplete suggestion
 * 
 * @author Davis
 *
 */
public class Candidate implements Comparable<Candidate> {

	private String word;
	private int occurences;
	
	public Candidate(String word, int occurences) {
		this.word = word;
		this.occurences = occurences;
	}
	
	/**
	 * Returns the autocomplete candidate
	 * @return the autocomplete candidate
	 */
	public String getWord() {
		return word;
	}
	
	/**
	 * Returns the confidence for the candidate
	 * @return the confidence for the candidate
	 */
	public Integer getConfidence() {
		return occurences;
	}
	
	@Override
	public String toString() {
		return this.getWord() + ": (" + this.getConfidence() + ")";
	}

	@Override
	public int compareTo(Candidate other) {
		if (other == null) {
			throw new NullPointerException();
		}
		return Integer.compare(other.getConfidence(), this.getConfidence());
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Candidate)) {
			return false;
		}
		Candidate otherCandidate = (Candidate) other;
		return (this.getWord().equals(otherCandidate.getWord()) && this.getConfidence() == otherCandidate.getConfidence());
	}
	
}
