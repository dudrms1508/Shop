package com.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MemberService memberService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//    http 요청에 대한 보안을 설정한다. 페이지 권한 설정, 로그인 페이지 설정, 로그아웃 메소드 등에 대한 설정을 작성
        http.formLogin()
                .loginPage("/members/login")
//                로그인 페이지 url 설정
                .defaultSuccessUrl("/")
//                로그인 성공 시 이동할 URL설정
                .usernameParameter("email")
//                로그인 시 사용할 파라미터 이름으로 email을 지정
                .failureUrl("/members/login/error")
//                로그인 실패시 이동할 url 설정
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
//                로그아웃 url 설정
                .logoutSuccessUrl("/")
//                로그아웃 성공 시 이동할 url 설정
        ;

        http.authorizeRequests()
//                시큐리티 처리에 httpServletRequest를 이용한다는 것을 의미
                .mvcMatchers("/", "/members/**", "/item/**", "/images/**").permitAll()
//                permiAll을 통해 모든 사용자가 인증 없이 해다 ㅇ경로에 접근할 수 있도록 설정
//                메인페이지, 회원관련 URL, 상품 상세 페이지, 상품 이미지를 불러오는 경로가 해당
                .mvcMatchers("/admin/**").hasRole("ADMIN")
//                /admin으로ㅓ 시작하는 경로는 해당 계정이 ADMIN일 경우에만 접근 가능하도록 설정
                .anyRequest().authenticated()
        ;

        http.exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
//        인증되지 않은 사용자가 리소스에 접근하였을 때 수행되는 핸들러를 등록
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
//        비밀번호를 데이터 베이스에 그대로 저장했을 경우, 데이터베이스가 해킹당하면 고객의 회원 정보가 그대로 노출됩니다.
//        이를 해결하기 위해 BCryptPasswordEncoder의 해시 함수를 이용하여 비밀번호를 암호화하여 저장한다.
//        BCryptPasswordEncoder를 빈으로 등록하여 사용
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        SpringSecurity에서 인증은 AuthenticationManagerBuilder를 통해 이루어지며
//        AuthenticationManagerBuilder가 AuthenticationManager를 생성한다.
//        userDetailService를 구현하고 있는 객체로 memberService를 지정해주며, 비밀번호 암호화를 위해
//        passwordEncoder를 지정해준다.
        auth.userDetailsService(memberService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
//        static 디렉터리의 하위 파일은 인증을 무시하도록 설정한다.
    }

}