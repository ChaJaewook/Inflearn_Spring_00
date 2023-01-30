package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
//Autowired 대신 주입
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    //문자를 그대로 응답을 보낼 수 있다.
    public String logDemo(HttpServletRequest request) //고객 요청정보를 확인 할 수 있다.
    {
        //고객이 요청할 URL을 알 수 있다.
        String requestURL=request.getRequestURL().toString();

        //URL Setting
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
