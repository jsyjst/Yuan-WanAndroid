package com.example.yuan_wanandroid.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/21
 *     desc   : fragment的作用域
 * </pre>
 */


@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {
}
