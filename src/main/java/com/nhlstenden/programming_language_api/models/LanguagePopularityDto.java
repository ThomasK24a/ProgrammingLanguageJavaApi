package com.nhlstenden.programming_language_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguagePopularityDto {
    private long id;
    private long languageId;
    private int year;
    private int ratingPercentile;   //Percentage of projects using this language in that year
}
