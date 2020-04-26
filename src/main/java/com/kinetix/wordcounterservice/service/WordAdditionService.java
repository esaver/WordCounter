package com.kinetix.wordcounterservice.service;

import static java.util.Objects.nonNull;

import com.kinetix.wordcounterservice.model.WordToAdd;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class WordAdditionService {

  private ConcurrentMap<String, Integer> wordCounter = new ConcurrentHashMap<>();

  private final TranslatorService translatorService;

  public void addWord(WordToAdd wordToAdd) {

    String englishWord = translatorService.translate(wordToAdd.getWord().toLowerCase());

    if (nonNull(englishWord)) {
      Optional.ofNullable(wordCounter.get(englishWord))
          .map(count -> wordCounter.put(englishWord, count + 1))
          .orElse(wordCounter.putIfAbsent(englishWord, 1));
    }
  }

  public Integer getWordCount(WordToAdd word) {
    Integer count = 0;

    String englishWord = translatorService.translate(word.getWord().toLowerCase());
    if (nonNull(englishWord)) {
      count = wordCounter.get(englishWord);
    }

    return count;
  }
}
