package com.nhlstenden.programming_language.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GithubIssues {
    private long id;
    private Language language;
    private int year;
    private int quarter;
    private int issueCount; //amount of issues reported for this language on Github during that year in that quarter
}
