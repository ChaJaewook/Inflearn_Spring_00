package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer()
    {
        AppConfig appConfig=new AppConfig();

        //1. 조회 : 호출할 때마다 객체를 생성
        MemberService memberService1=appConfig.memberService();

        //2. 조회 : 호출할 때마다 객체를 생성
        MemberService memberService2=appConfig.memberService();

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = "+memberService1);
        System.out.println("memberService1 = "+memberService2);
        // 1번과 2번이 다른 각각의 참조값을 반환한다.

        //memberService 1과 memberService2는 서로 다르다.
        assertThat(memberService1).isNotSameAs(memberService2);

        //만약 100개의 요청이 들어오면 100개의 객체가 생성된다 ==> 메모리 낭비
        //해결방안 : 하나의 객체를 만들고 공유하도록 한다==> 싱글톤패턴
    }

    // 싱글톤 패턴
    // 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 패턴
    // 하나의 자바서버에서는 객체 인스턴스가 하나만 생성돼야한다.
    // 두 개이상 생성되지 못하도록 막는다.
    // ㄴ> private 생성자를 사용해서 외부에서 임의로 new 키워드를 사용하지 못하도록 막는다.

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest()
    {
        SingletonService singletonService1=SingletonService.getInstance();
        SingletonService singletonService2=SingletonService.getInstance();

        System.out.println("singletonService1 = "+singletonService1);
        System.out.println("singletonService2 = "+singletonService2);


        assertThat(singletonService1).isSameAs(singletonService2);
    }

    //싱글톤 컨테이너는 싱글톤 패턴의 문제점을 해겨하면서, 객체 인스턴스를 싱글톤(1개만 생성)으로 관리한다.
    // 지금까지 우리가 학습한 스프링 빈이 바로 싱글톤으로 관리되는 빈이다.


    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer()
    {
//        AppConfig appConfig=new AppConfig();
        ApplicationContext ac =new AnnotationConfigApplicationContext(AppConfig.class);

        //1. 조회 : 호출할 때마다 객체를 생성
        MemberService memberService1=ac.getBean("memberService", MemberService.class);

        //2. 조회 : 호출할 때마다 객체를 생성
        MemberService memberService2=ac.getBean("memberService", MemberService.class);

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = "+memberService1);
        System.out.println("memberService1 = "+memberService2);
        // 1번과 2번이 다른 각각의 참조값을 반환한다.

        //memberService 1과 memberService2는 서로 다르다.
        assertThat(memberService1).isSameAs(memberService2);
    }
}

