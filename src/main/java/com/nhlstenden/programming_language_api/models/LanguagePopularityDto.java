package com.nhlstenden.programming_language_api.models;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name="languagePopularity")
@ApiModel(value = "language popularity", description = "The popularity of languages recorded in january of their respective year")
public class LanguagePopularityDto {
    private long id;
    private String languageName;
    private int year;
    private float ratingPercentile;   //Percentage of projects using this language in that year
}
