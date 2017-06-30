package cn.iven.mydemo.dagger.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by iven on 2017/6/19.
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface PerFragment {

}
