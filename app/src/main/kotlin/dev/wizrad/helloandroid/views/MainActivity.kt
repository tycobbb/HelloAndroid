package dev.wizrad.helloandroid.views

import dev.wizrad.helloandroid.R
import dev.wizrad.helloandroid.models.Summoner
import dev.wizrad.helloandroid.presenters.MainPresenterType
import dev.wizrad.helloandroid.dagger.modules.MainModule


import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem

public class MainActivity : BaseActivity<MainPresenterType>(), MainView {

    //
    // region Lifecycle
    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // inject dependencies
        this.component
            .mainModule(MainModule(this))
            .build().inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    // endregion

    //
    // region MainView
    //

    override fun didUpdateSummoner(summoner: Summoner) {
        Log.d("test", "view received: $summoner")
    }

    // endregion
}
