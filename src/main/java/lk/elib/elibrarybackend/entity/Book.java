package lk.elib.elibrarybackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @Column(name = "isbn", nullable = false)
    private Integer id;

    @Size(max = 100)
    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "year")
    private Integer year;

    @Column(name = "cover_image")
    private byte[] coverImage;

    @Column(name = "pdf")
    private byte[] pdf;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}