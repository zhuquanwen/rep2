package com.zqw.le.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Log {
	String name() default "";
	String remark() default ""; // 操作备注
	OperateType operateType() default OperateType.TEST; // 操作类型：Add/Update/Delete/Search/Login等
}
