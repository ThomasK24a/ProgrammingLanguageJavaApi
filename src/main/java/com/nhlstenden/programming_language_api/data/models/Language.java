package com.nhlstenden.programming_language_api.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "language")
public class Language {
    @Id
    private long id;
    private String languageName;
    private String fileExtension;
    private String HelloWorldProgram;   //entire program necessary to write "Hello World" in this language
}
