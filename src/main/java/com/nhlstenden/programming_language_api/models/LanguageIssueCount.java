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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="language_id")
    @ToString.Exclude
    private Language language;
    @Column(name="year")
    private int year;
    @Column(name="quarter")
    private int quarter;
    @Column(name="issue_count")
    private int issueCount; //amount of issues reported for this language on Github during that year in that quarter
}
