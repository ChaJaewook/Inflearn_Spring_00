package hello.core.scan.filter;

import javax.xml.stream.events.EndElement;
import java.lang.annotation.*;

@Target(ElementType.TYPE) //type은 클래스 레벨에 붙는다.
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
