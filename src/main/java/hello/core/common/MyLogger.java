package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;
//동시에 고객 요청이 오면 log가 섞일 수 있다.
//request scope를 사용하자!

//Provider와 프록시 둘다 핵심아이디어는 "진짜 객체 조회를 꼭 필요한 시점까지 지연처리한다는 점이다."

@Component
@Scope(value="request",proxyMode= ScopedProxyMode.TARGET_CLASS)
//proxy mdoe라는 parameter 추가
//프록시를 통해 가짜를 만든다.
//프록시 모드
// 적용 대상이 인터페이스 아닌 class면 Target_Class
// 적용 대상이 인터페이스면 interfaces를 주면 된다.
// 가짜 프록시 클래스를 만들고 미리 http를 주입한다.
//$$EnhancerBySpringCGLIB$$2699f515 이런 가짜 프록시 개체를 등록한다.

public class MyLogger {

    //여러 요청이 와도 객체를 각각 다룬다는데 핵심이 있다.
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message)
    {
        System.out.println("["+uuid+"]"+"[" +requestURL+"] "+message);
    }

    // 해당 Bean은 이제 HTTP 요청 당 하나씩 생성되고
    // HTTP 요청이 끝나는 시점에 소멸된다.
    @PostConstruct
    public void init()
    {
        //요청당 하나씩 생성돼 다른 http요청과 구분할 수 있다.
        uuid=UUID.randomUUID().toString();
        //this의 주소
        System.out.println("["+uuid+"] request scope bean create:"+this);
    }

    @PreDestroy
    public void close()
    {
        System.out.println("["+uuid+"] request scope bean close:"+this);
    }
}
