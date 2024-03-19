package ua.javarush.film.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(schema = "movie", name = "film_text")
@Entity
public class FilmText {
    @Id
    @Column(name = "film_id", nullable = false)
    private Short id;

    @OneToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @Column(length = 255, nullable = false)
    private String title;

    @Column
    private String description;
}
