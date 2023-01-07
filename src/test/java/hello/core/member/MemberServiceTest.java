package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    //MemberService memberService=new MemberServiceImpl(memberRepository);
    MemberService memberService;

    @BeforeEach //테스트 실행전에 무조건 실행
    public void beforeEach(){
        AppConfig appConfig=new AppConfig();
        memberService=appConfig.memberService();
    }

    @Test
    void join(){
        //given (~환경이 주어졌을때)
        Member member=new Member(1l,"memberA",Grade.VIP);

        //when (~이렇게 했을 때)
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then (~ 이렇게 된다.)
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
