package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void prototypeBeanFind()
    {
        AnnotationConfigApplicationContext ac =new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1=ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2=ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        //결과값에서 init까지나오고 destroy는 나오지 않는다.
        //또한 bean1과 bean2의 결과가 다르다.

        //prototype은 생성과 의존관계주입, 초기화까지만 관여하고 더는 관여하지 않는다.
        ac.close();

        //만약 destroy가 필요하다면 직접 호출하자
        //prototypeBean1.destroy();
        //prototypeBean2.destroy();
    }

    @Scope("prototype")
    //@Component 생략이유 : prototypeBeanFind에서 AnnotationConfigApplication~에서 자동으로 등록된것처럼 동작한다.

    static class PrototypeBean
    {
        @PostConstruct
        public void init()
        {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("SingletonBean.destroy");
        }
    }
}
