package com.kinetix.wordcounterservice.controller;

import com.kinetix.wordcounterservice.model.WordToAdd;
import com.kinetix.wordcounterservice.service.WordAdditionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@Slf4j
public class WordCounterController {

  private final WordAdditionService wordAdditionService;

  @GetMapping(value = "addWord/{word}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity addWord(@Validated final WordToAdd word) {
    wordAdditionService.addWord(word);
    return new ResponseEntity(HttpStatus.ACCEPTED);
  }

  @GetMapping(value = "wordCount/{word}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Integer> getWordCount(@Validated final WordToAdd wordToAdd) {
    return new ResponseEntity<>(wordAdditionService.getWordCount(wordToAdd), HttpStatus.OK);
  }
}
