package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest()
    {
        ApplicationContext ac =new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService=ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService=ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository=ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1=memberService.getMemberRepository();
        MemberRepository memberRepository2=orderService.getMemberRepository();

        System.out.println("memberRepository1 = " + memberRepository1);
        System.out.println("memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep()
    {
        ApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean=ac.getBean(AppConfig.class);

        System.out.println("bean.getClass() = " + bean.getClass());
        //bean.getClass() = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$957443c4
        //출력값을 보면 클래스가 뒤에 뭔가가.
        // 스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해 임의의 다른 클래스를 만들어 빈에 등록한다.

    }
}
