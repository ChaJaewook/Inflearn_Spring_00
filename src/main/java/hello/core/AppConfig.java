package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//AppConfig : 애플리케이션의 전체 동작 방식을 구성(config)하기위해 구현객체를 생성하고,
// "연결"하는 책임을 가지는 별도의 설정 클래스를 만들자
// 애플리케이션의 실제 동작에 필요한 "구현 객체를 생성"한다.
// 생성한 객체 인스턴스의 참조(레퍼런스)를 "생성자를 통해서 주입(연결)"해준다.

//어플리케이션의 구성(설정) 정보
// 설정정보에는 @Cofiguration을 적어준다.

//@Bean memberService -> new MemoryMemberRepository()
//@Bean orderService -> new MemoryMemberRepository()
//ㄴ> 얘네들은 싱글톤을 만족시키는 걸까?


@Configuration
//@Configuration이 바이트코드를 조작하는 CGLIB 기술을 사용해 싱글톤을 보장
public class AppConfig {

    //역할들이 잘 들어나게 리팩토링이 필요
    @Bean
    public MemberService memberService()
    {
        System.out.println("call AppConfig.memberService");
        //생성자 주입
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {

        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService()
    {
        System.out.println("call AppConfig.orderService");
        //return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        return new OrderServiceImpl(memberRepository(), discountPolicy());
        //return null;
    }
    @Bean
    public DiscountPolicy discountPolicy(){
        //AppConfig에서 할인 정책 역할을 담당하는 구현을 변경했다.
        //이제 할인정책인 변경돼도 애플리케이션의 구성역할을 담당하는 AppConfig만 변경하면 된다.
        //~Impl인 사용영역은 더이상 건드릴 필요가 없다.
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
