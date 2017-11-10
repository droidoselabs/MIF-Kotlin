package `in`.droidoselabs.missionindiafit.fragment


import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import `in`.droidoselabs.missionindiafit.activity.CommonData
import `in`.droidoselabs.missionindiafit.activity.IntroActivity
import `in`.droidoselabs.missionindiafit.R
import kotlinx.android.synthetic.main.fragment_get_started.*


/**
 * A simple [Fragment] subclass.
 */
class GetStartedFragment : Fragment(), View.OnClickListener {

    lateinit var animation: ObjectAnimator
    lateinit var animation2: ObjectAnimator
    lateinit var introActivity: IntroActivity
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var mView = inflater?.inflate(R.layout.fragment_get_started, container, false)
        init(mView)
        return mView
    }

    /**
     * Initialization
     */
    private fun init(mView: View?) {
        introActivity = activity as IntroActivity
        val btnStart = mView?.findViewById<Button>(R.id.btnStart) as Button
        btnStart.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        animations()
        Handler().postDelayed({
            CommonData.saveCurrentPage(1)
            introActivity.changePage()
        }, 800)


    }

    /**
     * Animations
     */
    private fun animations() {
        val scale = PropertyValuesHolder.ofFloat(View.SCALE_X, 0F)
        val expand = PropertyValuesHolder.ofFloat(View.SCALE_Y, 25.0F)
        val expand2 = PropertyValuesHolder.ofFloat(View.SCALE_X, 25.0F)
        val vanish = PropertyValuesHolder.ofFloat(View.ALPHA, 0.0F)
        animation = ObjectAnimator.ofPropertyValuesHolder(btnStart, scale)
        animation2 = ObjectAnimator.ofPropertyValuesHolder(viewCircle, expand, expand2, vanish)
        animation.duration = 400
        animation2.startDelay = 250
        animation2.duration = 600
        animation.start()
        animation2.start()
    }

    /**
     * Change Page
     */
    interface ChangePage {
        fun changePage()
    }

}
