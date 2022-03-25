package com.nhlstenden.programming_language_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name="languagePopularity")
public class LanguagePopularityDto {
    private long id;
    private String languageName;
    private int year;
    private float ratingPercentile;   //Percentage of projects using this language in that year
}
