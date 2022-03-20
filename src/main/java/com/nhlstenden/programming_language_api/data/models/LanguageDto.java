package com.nhlstenden.programming_language_api.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageDto {
    private long id;
    private String languageName;
    private String fileExtension;
    private String HelloWorldProgram;   //entire program necessary to write "Hello World" in this language
}
