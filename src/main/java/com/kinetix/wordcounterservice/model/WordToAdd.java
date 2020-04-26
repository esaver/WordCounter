package com.kinetix.wordcounterservice.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WordToAdd {

  protected static final String NON_ALPHABETIC_WORD_PATTERN = "[A-Za-z]+";

  @Pattern(regexp = NON_ALPHABETIC_WORD_PATTERN, message = "Non-alphabetic characters not allowed in the word")
  private String word;

}
