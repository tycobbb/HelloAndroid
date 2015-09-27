package dev.wizrad.helloandroid.dagger.components

import dev.wizrad.helloandroid.views.MainActivity
import dev.wizrad.helloandroid.dagger.modules.MainModule
import dev.wizrad.helloandroid.dagger.scopes.ActivityScope

import dagger.Component

@ActivityScope
@Component(dependencies = arrayOf(RootComponent::class), modules = arrayOf(MainModule::class))
public interface ActivityComponent {
    public fun inject(mainActivity: MainActivity)
}