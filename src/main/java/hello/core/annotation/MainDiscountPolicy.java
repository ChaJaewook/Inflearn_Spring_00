package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

//Qualifier에 있는걸 복사
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented

@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}
// Annotation은 상속이라는 개념이 없다.
// 여러 Annotation을 모아주는건 Spring의 기능
