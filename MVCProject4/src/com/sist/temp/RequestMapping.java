package com.sist.temp;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
// @RequestMapping("aaa")
@Retention(RUNTIME)
@Target(METHOD)
public @interface RequestMapping {
	public String value();
}
