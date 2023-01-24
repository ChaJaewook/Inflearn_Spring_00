package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    //MemberService memberService=new MemberServiceImpl(memberRepository);
    //OrderService orderService=new OrderServiceImpl();
    MemberService memberService;
    OrderService orderService;
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig=new AppConfig();
        memberService=appConfig.memberService();
        orderService=appConfig.orderService();
    }

    @Test
    void createOrder()
    {
        Long memberId=1L;
        Member member=new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order=orderService.createOrder(memberId,"itemA",10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }



/*    @Test
    void fieldInjectionTest()
    {

        //OrderServiceImpl orderService=new OrderServiceImpl();
        //orderService.createOrder(1L, "itemA",10000);
        //필드 Autowired를 이렇게 사용하려면 null point에러가 발생한다.
        //다시 setter를 또만들어야한다.
        OrderServiceImpl orderService=new OrderServiceImpl();
        orderService.setDiscountPolicy(new FixDiscountPolicy());
        orderService.setMemberRepository(new MemoryMemberRepository());
        orderService.createOrder(1L, "itemA",10000);

        //이렇게 필드를 따로 만드로 setter또 만드느니 그냥 setter에 autowired 거는게 더 낫다.
    }*/
}
