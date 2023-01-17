package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{
    //private final MemberRepository memberRepository=new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    //private final DiscountPolicy discountPolicy=new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy=new RateDiscountPolicy();
    //DIP 의존성을 위해 객체 할당은 하지 않고 선언
    //추상화 인터페이스에만 의존
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        //생성자를 통해 객체가 할당되듯~
        // 구체적인 클래스를 모르고
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        //회원정보 조회
        Member member=memberRepository.findById(memberId);

        //할인에 대해서는 알아서 해달라고 던져준다.
        int discountPrice=discountPolicy.discount(member,itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    public DiscountPolicy getDiscountPolicy() {
        return discountPolicy;
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
