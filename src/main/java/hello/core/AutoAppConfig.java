package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//컴포넌트 스캔에 필요한 부분
@ComponentScan
        (
                excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes= Configuration.class)
        )
//@Component Annotation붙은 것들을 찾아서 다 자동으로 Spring Bean에 등록시켜준다.
public class AutoAppConfig {

}
