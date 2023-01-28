package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest()
    {
        //ApplicationContext ac =new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        ConfigurableApplicationContext ac =new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        //close를 위해서 Configurable~을 사용한다.
        NetworkClient client=ac.getBean(NetworkClient.class);

        //
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig{
        //@Bean(initMethod = "init", destroyMethod = "close")
        //Annotation으로 대체하기에 @Bean만 쓰면 된다.
        @Bean
        // 설정정보 사용 특징
        // 메서드 이름을 자유롭게 줄 수 있다.
        // 스프링빈이 스프링 코드에 의존하지 않는다.
        // 코드가 아니라 설정정보를 사용해 코드를 고칠 수 없는 외부라이브러리에서도 사용이 가능

        //@Bean으로만 등록할때 destroyMethod의 특이함
        // destroyMethod의 default 값은 "(inffered)" '추론하다'이다.
        // 대부분 라이브러리는 close나 shutdown메서드를 사용한다.
        // close놔 shutdown을 inffered가 알아서 찾아서 처리해주기도한다.
        public NetworkClient networkClient()
        {
            //아직 url정보가 없다.
            //스프링 빈 라이프사이클
            //1)객체생성 2)의존관계 주입
            NetworkClient networkClient=new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }

    //스프링 빈의 이벤트 라이프사이클
    //스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료

    //초기화 콜백 : 빈이 생성되고 빈의 의존관계 주입이 완료된 후 호출
    //소멸전 콜백 : 빈이 소멸되기 직전에 호출

}
