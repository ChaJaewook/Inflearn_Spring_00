package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;
//동시에 고객 요청이 오면 log가 섞일 수 있다.
//request scope를 사용하자!

@Component
@Scope(value="request")
public class MyLogger {

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
