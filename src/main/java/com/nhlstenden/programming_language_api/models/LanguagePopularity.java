package com.nhlstenden.programming_language_api.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "language_popularity")
public class LanguagePopularity {
    @Id
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="language_id")
    @ToString.Exclude
    private Language language;
    private int year;
    private int ratingPercentile;   //Percentage of projects using this language in that year
}
