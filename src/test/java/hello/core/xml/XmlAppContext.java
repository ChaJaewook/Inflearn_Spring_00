package hello.core.xml;

import hello.core.member.MemberService;
import org.junit.jupiter.api.Assertions;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class XmlAppContext {

    @Test
    void xmlAppContext()
    {
        GenericXmlApplicationContext ac=new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService=ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
