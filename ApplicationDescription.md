Write a simple application called "WordCounter".  
It should have the following two distinct methods:

1. method that allows you to add words
2. method that returns the count of how many times a given word was added to the word
counter

It should also have the following requirements:
- should NOT allow addition of words with non-alphabetic characters
- should treat same words written in different languages as the same word, for example if
adding &quot;flower&quot;, &quot;flor&quot; (Spanish word for flower) and &quot;blume&quot; (German word for
flower) the counting method should return 3.

For the simplicity of the task assume that translation of words will be done via external class
provided to you called &quot;Translator&quot; that will have method &quot;translate&quot; accepting word as an
argument and it will return English name for it.

Technology stack:
Java 8, JUnit and Mockito to solve the task.
Gradle or maven as dependency management tool.
Use your favorite open source libraries including assertion libraries.
Use you favorite IDE

Please do this exercise in TDD manner if possible. Please follow Clean Code, SOLID and FIRST
principles while building your solution. Think of the most optimal algorithm for storing and
counting words. Please do not use persistence or in-memory DB.
Please post your solution on