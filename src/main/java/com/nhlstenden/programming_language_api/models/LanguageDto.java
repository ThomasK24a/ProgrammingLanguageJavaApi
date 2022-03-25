package com.nhlstenden.programming_language_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name="language")
public class LanguageDto {
    private long id;
    private String languageName;
    private String fileExtension;
    private String helloWorldProgram;   //entire program necessary to write "Hello World" in this language
}
