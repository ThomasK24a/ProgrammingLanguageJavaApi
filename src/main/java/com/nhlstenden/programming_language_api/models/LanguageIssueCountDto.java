package com.nhlstenden.programming_language_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageIssueCountDto {
    private long id;
    private long languageId;
    private int year;
    private int quarter;
    private int issueCount; //amount of issues reported for this language on Github during that year in that quarter
}
