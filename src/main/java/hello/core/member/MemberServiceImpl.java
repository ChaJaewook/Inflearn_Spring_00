package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//구현체가 하나면 관례랑 뒤에 Impl을 붙인다.
@Component
public class MemberServiceImpl implements MemberService{

    //MemberRepository가 아닌 override된 MemoryMemberRepository가 실행된다.
    //private final MemberRepository memberRepository=new MemoryMemberRepository();
    //기존에는 MemberService에서 MemberRepository를 설정했다.
    //이제 AppConfig에서 맡기자

    private final MemberRepository memberRepository;
    //생성자를 통해서 MemberRespository에 들어갈 요소를 결정한다.
    //MemoryMemberRepository가 없고 memberRepository만 있다.
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository()
    {
        return memberRepository;
    }
}
