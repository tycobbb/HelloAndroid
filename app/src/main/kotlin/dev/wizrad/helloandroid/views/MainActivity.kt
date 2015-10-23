package dev.wizrad.helloandroid.views

import dev.wizrad.helloandroid.R
import dev.wizrad.helloandroid.models.Summoner
import dev.wizrad.helloandroid.presenters.MainPresenterType
import dev.wizrad.helloandroid.dagger.modules.MainModule

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

import butterknife.bindView
import com.jakewharton.rxbinding.view.RxView
import com.jakewharton.rxbinding.widget.RxTextView
import dev.wizrad.helloandroid.views.shared.OptionsField
import rx.Observable

public class MainActivity : BaseActivity<MainPresenterType>(), MainView {

    //
    // Outlets
    //

    private val nameField:    EditText     by bindView(R.id.summoner_name_field)
    private val optionsField: OptionsField by bindView(R.id.summoner_region_field)
    private val findButton:   Button       by bindView(R.id.summoner_find_button)

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
        return optionsField.selectedOption()
    }

    override fun action(): Observable<Any> {
        return RxView.clicks(findButton);
    }

    override fun didEnableSubmit(isEnabled: Boolean) {
        findButton.isEnabled = isEnabled
    }

    override fun didUpdateSelectedRegion(region: String) {
        optionsField.text = region
    }

    override fun didUpdateRegions(regions: List<String>) {
        optionsField.options = regions
    }

    override fun didUpdateSummoner(summoner: Summoner) {
        Log.d("test", "view received: $summoner")
    }

}
