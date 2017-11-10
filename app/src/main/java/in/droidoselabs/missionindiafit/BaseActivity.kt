package `in`.droidoselabs.missionindiafit

import android.content.Context
import android.support.v7.app.AppCompatActivity
import io.paperdb.Paper
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

/**
 * Created by android on 8/26/17.
 */
open class BaseActivity : AppCompatActivity() {
    override fun attachBaseContext(newBase: Context?) {
        Paper.init(newBase)
        super.attachBaseContext((CalligraphyContextWrapper.wrap(newBase)))
    }
}