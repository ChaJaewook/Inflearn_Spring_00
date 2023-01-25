package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
//final 붙은건 필수값, 필수값에 대한 생성자를 만들어 준다.
public class OrderServiceImpl implements OrderService{
    //private final MemberRepository memberRepository=new MemoryMemberRepository();

    //필드 사용시에는 final 생략필요
    //private final MemberRepository memberRepository;

    //필드에 의존관계를 바로 주입해준다.
    //그러나 권장되지 않는 방법이다.
    //외부에서 변경이 불가능하다는 단점이 있다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //rateDiscountPolicy
    //public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    //private final DiscountPolicy discountPolicy=new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy=new RateDiscountPolicy();
    //DIP 의존성을 위해 객체 할당은 하지 않고 선언
    //추상화 인터페이스에만 의존
    //private final DiscountPolicy discountPolicy;

    //수정자, setter 생성자를 통한 주입

    /*public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }
    */



    /*public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }*/
    //final로 설정한건==> 얘네는 무조건 값이 있어야한다는 뜻~

    //생성자 의존관계는 불편, 필수 의존관계에서 사용
    //생성자가 하나면 Autowired생략가능, 자동으로 의존성 주입

    //생성자 하나로 Autowired 생략
   /*public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        //생성자를 통해 객체가 할당되듯~
        // 구체적인 클래스를 모르고
        //System.out.println("memberRepository = " + memberRepository);
        //System.out.println("discountPolicy = " + discountPolicy);
        //System.out.println("OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

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

    //수정자 주입(setter 주입)
    // setter라 불리는 필드의 값을 변경하는 수정자 메소드를 통해서 의존관계를 주입하는 방법
    // - 선택, 변경 가능성이 있는 의존관계에 사용
    // - 자방빈 프로퍼티 규약의 수정자 메서드 방식을 사용하는 방법
    // 선택적이기에 옵션을 줄때는 required=false라고 Autowired옆에 기재해 주자.

    //일반 메서드 주입
    // 아무 메서드에서나 사용가능
    // 한번에 여러필드를 주입받을 수 있다.
    // 일반적으로 잘 사용하지 않는다.
    /*@Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy)
    {
        this.memberRepository=memberRepository;
        this.discountPolicy=discountPolicy;
    }*/
}
