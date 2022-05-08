package com.nhlstenden.programming_language_api.models;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name="languageIssueCount")
@ApiModel(value = "language issue count", description = "The amount of issues in each language in Github per quarter, for xml requires the root object languageIssueCount")
public class LanguageIssueCountDto {
    private long id;
    private String languageName;
    private int year;
    private int quarter;
    private int issueCount; //amount of issues reported for this language on Github during that year in that quarter
}
