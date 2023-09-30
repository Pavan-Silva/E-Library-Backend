package lk.elib.elibrarybackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class TransactionDto {

    private Integer id;
    private BookDto bookIsbn;
    private MemberDto memberUser;
    private Instant date;
}