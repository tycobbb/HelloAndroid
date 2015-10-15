package dev.wizrad.helloandroid.views

import dev.wizrad.helloandroid.R
import dev.wizrad.helloandroid.models.Summoner
import dev.wizrad.helloandroid.presenters.MainPresenterType
import dev.wizrad.helloandroid.dagger.modules.MainModule
import dev.wizrad.helloandroid.extensions.setItems

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner

import butterknife.bindView
import com.jakewharton.rxbinding.view.RxView
import com.jakewharton.rxbinding.widget.RxAdapterView
import com.jakewharton.rxbinding.widget.RxTextView
import rx.Observable

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

    //
    // MainView
    //

    override fun summonerName(): Observable<CharSequence> {
        return RxTextView.textChanges(nameField)
    }

    override fun selectedRegion(): Observable<Int> {
        return RxAdapterView.itemSelections(regionSpinner)
    }

    override fun action(): Observable<Any> {
        return RxView.clicks(findButton);
    }

    override fun didEnableSubmit(isEnabled: Boolean) {
        findButton.isEnabled = isEnabled
    }

    override fun didUpdateRegions(regions: List<String>) {
        regionSpinner.setItems(regions, this)
    }

    override fun didUpdateSummoner(summoner: Summoner) {
        Log.d("test", "view received: $summoner")
    }

}
