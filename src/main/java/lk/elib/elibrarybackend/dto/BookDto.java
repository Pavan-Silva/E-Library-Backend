package lk.elib.elibrarybackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookDto {

    private Integer id;
    private String title;
    private Integer year;
    private byte[] coverImage;
    private byte[] pdf;
    private PublisherDto publisher;
    private AuthorDto author;
    private CategoryDto category;
}