package com.example.nickolas.kpiweeks.di.scopes;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@javax.inject.Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface Scope {

    String value() default "";

}
