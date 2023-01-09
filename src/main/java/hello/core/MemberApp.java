package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    //순수한 JAVA코드
    public static void main(String[] args) {
//        AppConfig appConfig=new AppConfig();
//
//
//        MemberService memberService=appConfig.memberService();
        //MemberService memberService=new MemberServiceImpl(memberRepository);

        //스프링 생성
        // 앞서 선언한 @Bean을 관리해준다.
        // parameter로 AppConfig를 넣어준다.
        // AppConfig의 환경정보를 갖고 Spring Container에서 관리해준다.
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(AppConfig.class);

        //첫번째 파라미터에는 메소드 이름, 두번째 메소드에는 반환받을 타입을 입력
        MemberService memberService=applicationContext.getBean("memberService",MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = "+member.getName());
        System.out.println("find Member = "+findMember.getName());

    }
}
 