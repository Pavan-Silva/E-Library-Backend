package lk.elib.elibrarybackend.util;

import lk.elib.elibrarybackend.dto.MemberDto;
import lk.elib.elibrarybackend.dto.StaffMemberDto;
import lk.elib.elibrarybackend.entity.Member;
import lk.elib.elibrarybackend.entity.StaffMember;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    private static final ModelMapper modelMapper = new ModelMapper();

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
}
