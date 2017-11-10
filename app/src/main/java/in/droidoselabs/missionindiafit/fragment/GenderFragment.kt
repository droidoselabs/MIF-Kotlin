package `in`.droidoselabs.missionindiafit.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import `in`.droidoselabs.missionindiafit.R
import kotlinx.android.synthetic.main.fragment_gender.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.ScaleAnimation
import `in`.droidoselabs.missionindiafit.CommonData


/**
 * A simple [Fragment] subclass.
 */
class GenderFragment : Fragment(), View.OnClickListener, Animation.AnimationListener {

    lateinit var femaleImage: ImageView
    lateinit var maleImage: ImageView
    lateinit var maleViewAnim: Animation
    lateinit var centerBlueViewAnim: Animation
    lateinit var centerBlackViewAnim: Animation
    lateinit var femaleViewAnim: Animation
    lateinit var maleView: View
    lateinit var femaleView: View
    lateinit var centerBlueView: View
    lateinit var centerBlackView: View
    var isMale: Boolean = false
    var isfemale: Boolean = false
    var isFirst: Boolean = true
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val mView = inflater?.inflate(R.layout.fragment_gender, container, false)
        init(mView)
        clickListeners()
        return mView
    }

    /**
     * Initializations
     */
    private fun init(mView: View?) {
        femaleImage = mView?.findViewById<ImageView>(R.id.female) as ImageView
        maleImage = mView?.findViewById<ImageView>(R.id.male) as ImageView
        maleView = mView.findViewById<View>(R.id.view_male) as View
        femaleView = mView.findViewById<View>(R.id.view_female) as View
        centerBlueView = mView.findViewById<View>(R.id.view_center) as View
        centerBlackView = mView.findViewById<View>(R.id.view_center_black) as View
        CommonData.saveGender("Male")
    }

    /**
     * Click Listeners
     */
    private fun clickListeners() {
        femaleImage.setOnClickListener(this)
        maleImage.setOnClickListener(this)
    }

    /**
     * Select male animation
     */
    private fun selectMale() {
        isfemale = false
        clearAnimations()
        maleViewAnim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                R.anim.expand_female_view)
        femaleViewAnim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                R.anim.shrink_male_view)

        animationListeners()

        maleView.startAnimation(maleViewAnim)
        femaleView.startAnimation(femaleViewAnim)
        val blueView = ScaleAnimation(0.0f, 1.0f, 1.0f, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f)
        val blackView = ScaleAnimation(0.0f, 1.0f, 1.0f, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f)
        blueView.duration = 500
        blackView.duration = 300
        blackView.startOffset = 400
        view_center_black.animation = blackView
        view_center.animation = blueView
        isMale = true
        CommonData.saveGender("Male")
    }

    /**
     * select female animation
     */
    private fun selectFemale() {
        isMale = false
        clearAnimations()
        if (isFirst) {
            centerBlueView.visibility = View.VISIBLE
            centerBlackView.visibility = View.VISIBLE
            femaleView.visibility = View.VISIBLE
            isFirst = false
        }

        maleViewAnim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                R.anim.shrink_male_view)
        femaleViewAnim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                R.anim.expand_female_view)
        centerBlueViewAnim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                R.anim.slide_centre_view)
        centerBlackViewAnim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                R.anim.slide_black_centre_view)

        animationListeners()

        maleView.animation = maleViewAnim
        femaleView.animation = femaleViewAnim
        centerBlueView.animation = centerBlackViewAnim
        centerBlackView.animation = centerBlueViewAnim
        maleView.startAnimation(maleViewAnim)
        femaleView.startAnimation(femaleViewAnim)
        centerBlueView.startAnimation(centerBlueViewAnim)
        centerBlackView.startAnimation(centerBlackViewAnim)
        isfemale = true
        CommonData.saveGender("Female")
    }

    /**
     * Animation Listeners
     */
    private fun animationListeners() {
        maleViewAnim.setAnimationListener(this)
        femaleViewAnim.setAnimationListener(this)
        centerBlueViewAnim.setAnimationListener(this)
        centerBlackViewAnim.setAnimationListener(this)
    }

    /**
     * Clear animations
     */
    private fun clearAnimations() {
        maleView.clearAnimation()
        femaleView.clearAnimation()
        centerBlackView.clearAnimation()
        centerBlueView.clearAnimation()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.female -> if (!isfemale) selectFemale()
            R.id.male -> if (!isMale) selectMale()
        }
    }

    override fun onAnimationRepeat(p0: Animation?) {
    }

    override fun onAnimationEnd(p0: Animation?) {
    }

    override fun onAnimationStart(p0: Animation?) {
    }
}
