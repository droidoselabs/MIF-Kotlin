package `in`.droidoselabs.missionindiafit

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.View
import `in`.droidoselabs.missionindiafit.fragment.*
import kotlinx.android.synthetic.main.activity_intro.*


class IntroActivity : BaseActivity(), GetStartedFragment.ChangePage, View.OnClickListener {

    companion object {
        lateinit var pageradapter: MyPagerAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        init()
    }

    /**
     * Initialization
     */
    private fun init() {
        var fragments: MutableList<Fragment> = mutableListOf()
        fragments = getFragments()
        pageradapter = MyPagerAdapter(supportFragmentManager, fragments)
        vpMain.adapter = pageradapter
        vpMain.offscreenPageLimit = 0
    }

    class MyPagerAdapter(fm: FragmentManager, val fragmentList: MutableList<Fragment>)
        : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return this.fragmentList[position]
        }

        override fun getCount(): Int {
            return this.fragmentList.size
        }
    }

    private fun getFragments(): MutableList<Fragment> {
        val fragments: MutableList<Fragment> = mutableListOf()
        fragments.add(GetStartedFragment())
        fragments.add(BasicDetailsFragment())
        fragments.add(GenderFragment())
        fragments.add(BodyTypeFragment())
        fragments.add(WeightFragment())
        fragments.add(AlmostTherefragment())
        return fragments
    }

    override fun changePage() {
        vpMain.setCurrentItem(CommonData.getCurrentPage())
        if (CommonData.getCurrentPage() + 1 > 2) {
            llNext.visibility = View.VISIBLE
            llNext.setOnClickListener(this)
        }
    }

    override fun onClick(p0: View?) {
        if (CommonData.getCurrentPage() < 4) {
            CommonData.saveCurrentPage(CommonData.getCurrentPage() + 1)
            vpMain.setCurrentItem(CommonData.getCurrentPage())
        } else {
            llNext.visibility = View.INVISIBLE
            CommonData.saveCurrentPage(CommonData.getCurrentPage() + 1)
            vpMain.setCurrentItem(CommonData.getCurrentPage())
        }
    }
}
