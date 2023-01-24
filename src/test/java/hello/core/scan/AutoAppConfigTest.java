package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan(){
        AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService=ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

        OrderServiceImpl bean=ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository=bean.getMemberRepository();
        System.out.println("memberRepository = " + memberRepository);
    }

    //@Qualifier : 추가구분자를 붙여주는 방법, 주입시 추가적인 방법을 제공하는것이지 빈이름을 변경하는것은 아니다.
}
