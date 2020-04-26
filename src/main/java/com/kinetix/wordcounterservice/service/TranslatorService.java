package com.kinetix.wordcounterservice.service;

import java.util.HashMap;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@Slf4j
public class TranslatorService {

  private HashMap<String, String> translationMap = new HashMap<>();

  @PostConstruct
  public void initialiseTranslationMap() {
    translationMap.put("flower", "flower");
    translationMap.put("flor", "flower");
    translationMap.put("blume", "flower");
    translationMap.put("hello", "hello");
    translationMap.put("hallo", "hello");
    translationMap.put("hola", "hello");
  }

  public String translate(String wordToTranslate) {
    return translationMap.get(wordToTranslate);
  }

}
