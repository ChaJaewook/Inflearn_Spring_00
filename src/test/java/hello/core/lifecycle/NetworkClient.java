package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
// javax는 java에서 지원하는 package
// 스프링에 종속적인 기능이 아니다.
// 여전히 외부라이브러리에서는 사용할 수 없다.
// 그래서 외부라이브러리에서는 @Bean을 이용한 initmethod, destroyMethod를 사용하자.

//public class NetworkClient implements InitializingBean, DisposableBean {
public class NetworkClient{

    private String url;

    //default 생성자
    public NetworkClient() {
        System.out.println("생성자 호출, url="+url);

    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect()
    {
        System.out.println("connect : "+url);
    }

    //실제 연결된 상태의 call
    public void call(String message)
    {
        System.out.println("call: "+url+" message = "+message);
    }

    //서비스 종료시 호출
    public void disconnect()
    {
        System.out.println("close: "+url);
    }

    //의존관계 주입이 끝나면~이라는 뜻
    //@Override
    /*public void afterPropertiesSet() throws Exception {
        //스프링의 의존관계 주입이 끝나면 호출된다.
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }*/
    @PostConstruct
    public void init() {
        //스프링의 의존관계 주입이 끝나면 호출된다.
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    //Disconnect 호출
    //Bean이 종료될때 호출된다.
    //@Override
    /*public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }*/
    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }

    // 초기화, 소멸 인터페이스 단점
    // 해당코드가 스프링 전용 인터페이스에 의존한다
    // 초기화, 소멸 메서드 이름 변경불가
    // 내가 코드를 고칠 수 없는 외부라이브러리에서는 적용할 수 없다.
    // 현재는 잘 사용하지 않는다.
}
