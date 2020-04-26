package com.kinetix.wordcounterservice.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.sortByQualityValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kinetix.wordcounterservice.model.WordToAdd;
import com.kinetix.wordcounterservice.service.WordAdditionService;
import java.time.LocalDateTime;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = WordCounterController.class)
public class WordCounterControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private WordAdditionService wordAdditionService;

  @InjectMocks
  private WordCounterController controller;

  @Test
  public void testAddWord() throws Exception {
    doNothing().when(wordAdditionService).addWord(WordToAdd.builder().word("hello").build());

    this.mockMvc.perform(get("/addWord/{word}", "hello")
        .contentType(APPLICATION_JSON))
        .andExpect(status().isAccepted());
  }

  @Test
  void testGetWordCount() throws Exception {
    when(wordAdditionService.getWordCount(WordToAdd.builder().word("hello").build())).thenReturn(1);

    this.mockMvc.perform(get("/wordCount/{word}", "hello")
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
        .andExpect(MockMvcResultMatchers.jsonPath("$", Is.is(1)));
  }
}