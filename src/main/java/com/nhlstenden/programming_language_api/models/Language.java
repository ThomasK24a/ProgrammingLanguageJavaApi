package com.nhlstenden.programming_language_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
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
    @Column(name="id", nullable = false)
    private long id;
    @Column(name="language_name", nullable = false)
    private String languageName;
    @Column(name="file_extension")
    private String fileExtension;
    @Column(name="hello_world_program", nullable = false)
    private String helloWorldProgram;   //entire program necessary to write "Hello World" in this language
}
