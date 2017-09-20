# Asymmetrik Job Application Programming Challenge - Davis Cook 2017
The following repository contains my solution to the programming challenge provided by Asymmetrik as a part of their job application process. 

It is a solution to the "Mobile Device Keyboard" challenge and is written in the Java programming language. 
The solutions documentation is provided in the form of a JavaDoc and the solution was tested using the JUnit 4 testing framework.

## Implementation Details and Explanation of Design Choices
To implement the required autocomplete functionality I decided to use a trie data structure because its time and space complexity make the algorithm very efficient.

A trie has a space complexity that is O(n * k) where n is the number of words being stored in the trie and k is the average length of the words.
Assuming that this algorithm will be run on words, k should be sufficiently small such that it could be considered a constant, giving the trie an O(n) space complexity.
As such, the space utilization requirements of the trie will only increase linearly with the number of different words provided to the algorithm through training.

A trie also has a time complexity that is at most O(p + n * k) where p is the length of the prefix being examined for autocomplete, n is the number of possible endings, and k is the average length of those endings.
This is because to determine the endings for a prefix, one must first traverse down the trie the length of the prefix in time p, and then traverse each of the possible endings which will take at most n * k time.
Since the length of the prefix will be sufficiently small for almost any examined prefix, it can be considered a constant that is easily dwarfed by n * k. 
As such the performance of the trie for autocomplete look-ups will be almost completely based on the number of possible endings for the prefix and the length of those endings.
And since k, the average length of the endings should be sufficiently small since we are dealing with words, the main factor for the efficiency of lookup will be the number of possible endings, rendering the time complexity O(n).

This O(n) time complexity could be concerning for prefixes with large numbers of possible endings, however the only prefixes with very large n would be prefixes of only a few characters.
Single letter prefixes would have thousands of possible endings, and two letter prefixes would similarly have many possible endings.
So a simple solution to this O(n) time complexity issue could be to restrict autocomplete to only run once at least three characters have been typed out.
In this way, the size of n is kept limited and allows the trie to very efficiently retrieve possible endings for the prefixes that must be searched.

## Compilation and Execution Instructions
Since the software is written in Java it may be compiled using the `javac` command, however the software has already been compiled in the bin/ directory.
So to execute the interactive console for examining the software's function, all that is required is to navigate to the `bin/` directory in the terminal and then running:

```
java com.davispcook.asymmetrik.executable.Main
```

Further details for how to use the interactive console will be shown once the demo starts.

## Pulling up the Documentation
The source code for this project in the `src/` folder is well documented with comments explaining what each part of the algorithm does.
A general overview of the project and its classes can be found in the `doc/` folder by opening `index.html` in the web browser and browsing the automatically generated JavaDoc.