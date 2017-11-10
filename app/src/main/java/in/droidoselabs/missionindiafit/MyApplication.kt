package `in`.droidoselabs.missionindiafit

import android.support.multidex.MultiDexApplication
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

/**
 * Created by android on 8/26/17.
 */
class MyApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Montserrat-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }
}