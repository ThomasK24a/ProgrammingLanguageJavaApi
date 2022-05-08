package com.nhlstenden.programming_language_api.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name="language")
@ApiModel(value = "language", description = "How to say Hello World in different programming languages, for xml requires the root object language")
public class LanguageDto {
    private long id;
    private String languageName;
    private String fileExtension;
    private String helloWorldProgram;   //entire program necessary to write "Hello World" in this language
}
