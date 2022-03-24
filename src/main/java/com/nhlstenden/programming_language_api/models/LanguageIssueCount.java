package com.nhlstenden.programming_language_api.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "language_github_issues")
public class LanguageIssueCount {
    @Id
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="language_id")
    @ToString.Exclude
    private Language language;
    private int year;
    private int quarter;
    private int issueCount; //amount of issues reported for this language on Github during that year in that quarter
}
