package com.shop.entity;

import com.shop.constant.Role;
import com.shop.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.persistence.*;

@Entity
@Table(name="member")
@Setter @Getter
@ToString
public class Member extends BaseEntity{

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;
//  회원의 이메일은 유일하게 구분해야 하기 때문에, 동일한 값이 DB에 들어오지 못하도록 unique속성을 지정
    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;
//  자바의 enum 타입을 엔티티의 속성으로 지정할 수 있습니다. Enum을 사용할 때 기본적으로 순서가 저장되는데,
//  enum의 순서가 바뀔경우 문제가 발생할 수 있으므로 "EnumType.STRING" 옵션을 사용해서 String으로 저장하기를 권장한다.

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {

        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
//        스프링 시큐리티 설정 클래스에 등록한 BCryptPasswordEncoder Bean을 파라미터로 넘겨 비밀번호를 암호화한다.
        member.setPassword(password);
        member.setRole(Role.ADMIN);
        return member;
    }


}
