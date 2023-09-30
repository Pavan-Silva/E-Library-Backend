package lk.elib.elibrarybackend.util;

import lk.elib.elibrarybackend.dto.BookDto;
import lk.elib.elibrarybackend.dto.MemberDto;
import lk.elib.elibrarybackend.dto.StaffMemberDto;
import lk.elib.elibrarybackend.dto.TransactionDto;
import lk.elib.elibrarybackend.entity.Book;
import lk.elib.elibrarybackend.entity.Member;
import lk.elib.elibrarybackend.entity.StaffMember;
import lk.elib.elibrarybackend.entity.Transaction;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    /* Staff Mapping */
    public static List<StaffMemberDto> staffListToDto(List<StaffMember> list) {
        return list.stream().map(
                staffMember -> modelMapper.map(staffMember, StaffMemberDto.class))
                .collect(Collectors.toList());
    }

    public static StaffMemberDto staffMemberToDto(StaffMember staffMember) {
        return modelMapper.map(staffMember, StaffMemberDto.class);
    }

    public static StaffMember dtoToStaffMember(StaffMemberDto dto) {
        return modelMapper.map(dto, StaffMember.class);
    }

    /* Member Mapping */
    public static List<MemberDto> memberListToDto(List<Member> list) {
        return list.stream().map(
                        member -> modelMapper.map(member, MemberDto.class))
                .collect(Collectors.toList());
    }

    public static MemberDto memberToDto(Member member) {
        return modelMapper.map(member, MemberDto.class);
    }

    public static Member dtoToMember(MemberDto dto) {
        return modelMapper.map(dto, Member.class);
    }

    /* Transaction Mapping */
    public static List<TransactionDto> transactionListToDto(List<Transaction> list) {
        return list.stream().map(
                        transaction -> modelMapper.map(transaction, TransactionDto.class))
                .collect(Collectors.toList());
    }

    public static TransactionDto transactionToDto(Transaction transaction) {
        return modelMapper.map(transaction, TransactionDto.class);
    }

    public static Transaction dtoToTransaction(TransactionDto dto) {
        return modelMapper.map(dto, Transaction.class);
    }

    /* Book Mapping */
    public static BookDto bookToDto(Book book) {
        return modelMapper.map(book, BookDto.class);
    }

    public static Book dtoToBook(BookDto dto) {
        return modelMapper.map(dto, Book.class);
    }
}
