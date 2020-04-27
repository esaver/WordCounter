package com.kinetix.wordcounterservice.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kinetix.wordcounterservice.model.WordToAdd;
import com.kinetix.wordcounterservice.service.WordAdditionService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = WordCounterController.class)
public class WordCounterControllerTest {

  public static final String HELLO = "hello";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private WordAdditionService wordAdditionService;

  @InjectMocks
  private WordCounterController controller;

  @Test
  public void testAddWord() throws Exception {
    doNothing().when(wordAdditionService).addWord(WordToAdd.builder().word(HELLO).build());

    this.mockMvc.perform(get("/addWord/{word}", HELLO)
        .contentType(APPLICATION_JSON))
        .andExpect(status().isAccepted());
  }

  @Test
  void testGetWordCount() throws Exception {
    when(wordAdditionService.getWordCount(WordToAdd.builder().word(HELLO).build())).thenReturn(1);

    this.mockMvc.perform(get("/wordCount/{word}", HELLO)
        .contentType(APPLICATION_JSON_VALUE))
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().contentType(APPLICATION_JSON_VALUE))
        .andExpect(MockMvcResultMatchers.jsonPath("$", Is.is(1)));
  }
}