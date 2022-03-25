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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="language_id")
    @ToString.Exclude
    private Language language;
    @Column(name="year")
    private int year;
    @Column(name="rating_percentile")
    private float ratingPercentile;   //Percentage of projects using this language in that year
}
