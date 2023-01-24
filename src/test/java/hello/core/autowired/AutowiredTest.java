package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption()
    {
        ApplicationContext ac=new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean{
        //스프링 컨테이너에서관리되는게 없다.
        //@Autowired
        //Autowired를 통해 Bean이 등록돼 있지 않기에 오류가 난다.
        @Autowired(required = false)
        //의존관계가 없어 메소드 자체가 호출되지 않는다.
        public void setNoBean1(Member noBean1)
        {
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2)
        {
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        //값이 없으면 Optional에 따라 값을 Empty로 주고
        // 값이 있다면 Optional로 감싸준다.
        public void setNoBean3(Optional<Member> noBean3)
        {
            System.out.println("noBean3 = " + noBean3);
        }


        //@Autowired가 겹치면?
        // 1) @Autowired 필드명 매칭
        // 2) @Qualifier 끼리 매칭 ==> 빈 이름 매칭
        // 3) @Primary 사용
    }
}
