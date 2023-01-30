package hello.core.scope;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind()
    {
        AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1=ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2=ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    @org.testng.annotations.Test
    void singletonClientUsePrototype()
    {
        AnnotationConfigApplicationContext ac=
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1=ac.getBean(ClientBean.class);
        int count1=clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clientBean2=ac.getBean(ClientBean.class);
        int count2=clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);

    }

    @Scope("singleton")
    static class ClientBean{
        //private final PrototypeBean prototypeBean; //생성시점에 이미 주입

        //@Autowired
        //private ObjectProvider<PrototypeBean> prototypeBeanProvider;
        //ObjectFactory도 사용가능

        //Provider로 대체하기
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        //생성자 하나기에 Autowired 생략가능
        //@Autowired
        /*public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }*/

        //이미 생성시점에 생성된 Prototype Bean을 사용한다.
        public int logic()
        {
            //provider를 통해 prototypeBean을 찾아준다.
            //Spring Contanier에서 prototypeBean을 찾아서 반환해준다.
            //찾아주는 기능만 제공해준다.
            //항상 새로운 프로토타입 빈이 생성되는것을 확인 할 수 있다.
            //특징
            //ObjectFactory : 기능이단순, 별도의 라이브러리 필요없음, 스프링에 의존
            //ObjectProvider : ObjectFactory 상속, 옵션, 스트림 처리등 편의 기능이 많고
            // 별도의 라이브러리 필요가 없음, 스프링에 의존
            //PrototypeBean prototypeBean= prototypeBeanProvider.getObject();

            PrototypeBean prototypeBean= prototypeBeanProvider.get();
            // 항상 새로운 프로토타입 빈이 생성된다.

            prototypeBean.addCount();
            int count=prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count=0;

        public void addCount()
        {
            count++;
        }

        public int getCount()
        {
            return count;
        }

        @PostConstruct
        public void init()
        {
            //this를 통해 '나'의 값을찍어줄 수 있다.
            System.out.println("PrototypeBean.init"+this);
        }

        @PreDestroy
        public void destroy()
        {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
