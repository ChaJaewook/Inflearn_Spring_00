package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//컴포넌트 스캔에 필요한 부분
@ComponentScan
        (
                //컴포넌트 스캔의 범위를 정해준다.
                // 왜 범위를 정해줄까?
                // 범위를 정하지 않으면 탐색하는 범위가 너무 많아 오래걸리기 때문이다.

                //default값은 어떻게 될까?
                //패키지 기준 하위를 다뒤진다.
                //basePackages ="hello.core.member",
               // basePackageClasses = AutoAppConfig.class,
                excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes= Configuration.class)
        )
//@Component Annotation붙은 것들을 찾아서 다 자동으로 Spring Bean에 등록시켜준다.
public class AutoAppConfig {

        //나는 기존에 MemoryMemberRepository말고
        //새로운 MemoryMemberRepository를 수동으로 등록했다.
        // 그럼 이름이 중복되지만 오류는 나지 않는다.
        // 그이유는 바로 수동으로 입력된게 우선순위를 가지기 때문이다.
        //최근 스프링 부트는 해당 사항도 오류가나게 수정됐다.
        // 만약 사용하고싶다면
        //application.properties에 spring.main.allow-bean-definition-overriding=true
        //옵션을 준다.

//        @Bean(name="memoryMemberRepository")
//        MemberRepository memberRepository()
//        {
//                return new MemoryMemberRepository();
//        }

}
