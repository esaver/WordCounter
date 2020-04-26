package com.kinetix.wordcounterservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kinetix.wordcounterservice.model.WordToAdd;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class WordAdditionServiceTest {

  public static final String HELLO = "hello";
  public static final String HOLA = "hola";
  public static final String HELLO_IN_CAMEL_CASE = "Hello";
  public static final String WORLD = "World";

  @Mock
  private TranslatorService translatorService;

  private WordAdditionService wordAdditionService;

  @BeforeEach
  void setUp() {
    wordAdditionService = new WordAdditionService(translatorService);
  }

  @Test
  void whenAWordAdded_thenWordGetsAddedAndCounterUpdated() {

    when(translatorService.translate(HELLO)).thenReturn(HELLO);

    WordToAdd hello = WordToAdd.builder().word(HELLO).build();

    wordAdditionService.addWord(hello);

    assertThat(wordAdditionService.getWordCount(hello)).isEqualTo(1);
    verify(translatorService, times(2)).translate(anyString());
  }

  @Test
  void whenAWordAddedInOtherLanguage_thenWordGetsAddedAndCounterUpdated() {

    when(translatorService.translate(HOLA)).thenReturn(HELLO);

    WordToAdd hola = WordToAdd.builder().word(HOLA).build();
    WordToAdd holaInEnglish = WordToAdd.builder().word(HELLO).build();

    wordAdditionService.addWord(hola);

    assertThat(wordAdditionService.getWordCount(hola)).isEqualTo(1);
    verify(translatorService, times(2)).translate(anyString());
  }

  @Test
  void whenSameWordAddedTwice_thenWordGetsAddedAndCounterUpdatedTwice() {

    when(translatorService.translate(anyString())).thenReturn(HELLO);

    WordToAdd hello = WordToAdd.builder().word(HELLO).build();
    WordToAdd helloInCamelCase = WordToAdd.builder().word(HELLO_IN_CAMEL_CASE).build();

    wordAdditionService.addWord(hello);
    wordAdditionService.addWord(helloInCamelCase);

    assertThat(wordAdditionService.getWordCount(hello)).isEqualTo(2);
    verify(translatorService, times(3)).translate(anyString());
  }

  @Test
  void whenSameWordAddedInOtherLanguage_thenWordGetsAddedAndCounterUpdatedTwice() {

    when(translatorService.translate(HELLO)).thenReturn(HELLO);
    when(translatorService.translate(HOLA)).thenReturn(HELLO);

    WordToAdd hello = WordToAdd.builder().word(HELLO).build();
    WordToAdd hola = WordToAdd.builder().word(HOLA).build();

    wordAdditionService.addWord(hello);
    wordAdditionService.addWord(hola);

    assertThat(wordAdditionService.getWordCount(hello)).isEqualTo(2);
    assertThat(wordAdditionService.getWordCount(hola)).isEqualTo(2);
    verify(translatorService, times(4)).translate(anyString());
  }

  @Test
  void whenWordAdded_AndNoTranslatedWordFound_thenWordDoesNotGetAddedAndCounterNotUpdated() {

    when(translatorService.translate(WORLD.toLowerCase())).thenReturn(null);

    WordToAdd world = WordToAdd.builder().word(WORLD).build();

    wordAdditionService.addWord(world);

    assertThat(wordAdditionService.getWordCount(world)).isEqualTo(0);
    verify(translatorService, times(2)).translate(anyString());
  }

  @Test
  void whenGetWordCountForWordNotReturnedByTranslator_thenWordCountIsZero() {
    when(translatorService.translate(WORLD.toLowerCase())).thenReturn(null);

    WordToAdd world = WordToAdd.builder().word(WORLD).build();

    assertThat(wordAdditionService.getWordCount(world)).isEqualTo(0);
    verify(translatorService, times(1)).translate(anyString());
  }

  @Test
  void whenGetWordCountForWordReturnedByTranslator_thenWordCountIsOne() {
    when(translatorService.translate(HELLO.toLowerCase())).thenReturn(HELLO);

    WordToAdd hello = WordToAdd.builder().word(HELLO).build();
    wordAdditionService.addWord(hello);

    assertThat(wordAdditionService.getWordCount(hello)).isEqualTo(1);
    verify(translatorService, times(2)).translate(anyString());
  }

  @Test
  void whenGetWordCountForSameWordAddedInOtherLanguageTwice_thenWordCountInEitherLanguageIsTwo() {

    when(translatorService.translate(HELLO)).thenReturn(HELLO);
    when(translatorService.translate(HOLA)).thenReturn(HELLO);

    WordToAdd hello = WordToAdd.builder().word(HELLO).build();
    WordToAdd hola = WordToAdd.builder().word(HOLA).build();

    wordAdditionService.addWord(hello);
    wordAdditionService.addWord(hola);

    assertThat(wordAdditionService.getWordCount(hello)).isEqualTo(2);
    assertThat(wordAdditionService.getWordCount(hola)).isEqualTo(2);
    verify(translatorService, times(4)).translate(anyString());
  }

}