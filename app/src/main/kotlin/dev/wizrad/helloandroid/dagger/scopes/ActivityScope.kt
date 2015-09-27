package dev.wizrad.helloandroid.dagger.scopes

import javax.inject.Scope;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy.RUNTIME;

@Scope
@Retention(RUNTIME)
public annotation class ActivityScope
