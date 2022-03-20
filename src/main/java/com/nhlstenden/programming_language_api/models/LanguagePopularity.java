package com.nhlstenden.programming_language_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguagePopularity {
    private long id;
    private Language language;
    private int year;
    private int ratingPercentile;   //Percentage of [...] using this language in that year
}
