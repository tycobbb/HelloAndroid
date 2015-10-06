package dev.wizrad.helloandroid.views

import dev.wizrad.helloandroid.R
import dev.wizrad.helloandroid.models.Summoner
import dev.wizrad.helloandroid.presenters.MainPresenterType
import dev.wizrad.helloandroid.dagger.modules.MainModule
import dev.wizrad.helloandroid.extensions.observe
import dev.wizrad.helloandroid.extensions.observeEnabled

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner

import butterknife.bindView
import com.jakewharton.rxbinding.view.RxView
import com.jakewharton.rxbinding.widget.RxAdapterView
import com.jakewharton.rxbinding.widget.RxTextView

public class MainActivity : BaseActivity<MainPresenterType>(), MainView {

    //
    // Outlets
    //

    private val nameField:     EditText by bindView(R.id.summoner_name_field)
    private val regionSpinner: Spinner  by bindView(R.id.summoner_region_field)
    private val findButton:    Button   by bindView(R.id.summoner_find_button)

    //
    // Lifecycle
    //

    final override fun onInject() {
        component
            .mainModule(MainModule(this))
            .build().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        // bind presenter observables to ui
        subscriptions
            .add(regionSpinner.observe(presenter.regions, this))
            .add(findButton.observeEnabled(presenter.canSubmit))

        // bind input sources to presenter
        presenter.bindName(RxTextView.textChanges(nameField))
        presenter.bindRegion(RxAdapterView.itemSelections(regionSpinner))
        presenter.bindAction(RxView.clicks(findButton))
    }

    //
    // MainView
    //

    override fun didUpdateSummoner(summoner: Summoner) {
        Log.d("test", "view received: $summoner")
    }

}
