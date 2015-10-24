package dev.wizrad.helloandroid.dagger.components

import dagger.Component
import dev.wizrad.helloandroid.dagger.modules.MainModule
import dev.wizrad.helloandroid.dagger.modules.MainProvider
import dev.wizrad.helloandroid.dagger.scopes.ActivityScope
import dev.wizrad.helloandroid.presenters.MainPresenter
import dev.wizrad.helloandroid.views.MainActivity
import dev.wizrad.helloandroid.views.MainView

@ActivityScope
@Component(dependencies = arrayOf(RootComponent::class), modules = arrayOf(MainModule::class))
interface ActivityComponent : MainProvider {
  fun inject(mainView: MainActivity)
}