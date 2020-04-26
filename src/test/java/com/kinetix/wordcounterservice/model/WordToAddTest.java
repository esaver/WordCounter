package com.kinetix.wordcounterservice.model;

import static com.kinetix.wordcounterservice.model.WordToAdd.NON_ALPHABETIC_WORD_PATTERN;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.matchesPattern;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WordToAddTest {

  @ParameterizedTest
  @ValueSource(strings = {"Hello", "HELLO", "hello", "hallo", "hola"})
  public void testValidAlphabeticWords(String inputWord) {
    assertThat(inputWord, matchesPattern(NON_ALPHABETIC_WORD_PATTERN));
  }

  @ParameterizedTest
  @ValueSource(strings = {" Hello", "H3LLO", "hello world", "123", "H3$$o", " "})
  public void testInValidAlphabeticWords(String inputWord) {
    assertThat(inputWord, not(matchesPattern(NON_ALPHABETIC_WORD_PATTERN)));
  }
}