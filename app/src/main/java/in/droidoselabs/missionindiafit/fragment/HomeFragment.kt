package `in`.droidoselabs.missionindiafit.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import `in`.droidoselabs.missionindiafit.R
import android.graphics.Color
import com.cooltechworks.views.shimmer.ShimmerRecyclerView
import android.support.v4.view.ViewPager
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.widget.Toolbar
import android.graphics.PorterDuff
import android.graphics.Color.parseColor


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    private val tabIcons = intArrayOf(R.drawable.ic_newspaper,
            R.drawable.ic_turn_notifications_on_button,
            R.drawable.ic_menu_black_24dp)

    companion object {
        fun getInstace(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val mView = inflater!!.inflate(R.layout.fragment_home, container, false) as View;
        viewPager = mView.findViewById<ViewPager>(R.id.viewpager) as ViewPager
        setupViewPager(viewPager!!);

        tabLayout = mView.findViewById<TabLayout>(R.id.tabs) as TabLayout
        tabLayout!!.setupWithViewPager(viewPager);
        setupTabIcons()
        tabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.icon!!.setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_IN)

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.icon!!.setColorFilter(Color.parseColor("#E0E0E0"), PorterDuff.Mode.SRC_IN)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
        return mView;
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(fragmentManager)
        adapter.addFragment(FeedFragment.getInstace())
        adapter.addFragment(NotificationsFragment.getInstace())
        adapter.addFragment(SettingsFragment.getInstace())
        viewPager.adapter = adapter
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = mutableListOf<Fragment>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList.get(position)
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment) {
            mFragmentList.add(fragment)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return null
        }
    }

    private fun setupTabIcons() {
        tabLayout?.getTabAt(0)?.setIcon(tabIcons[0])
        tabLayout?.getTabAt(0)?.icon?.setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_IN)
        tabLayout?.getTabAt(1)?.setIcon(tabIcons[1])
        tabLayout?.getTabAt(1)?.icon?.setColorFilter(Color.parseColor("#E0E0E0"), PorterDuff.Mode.SRC_IN)
        tabLayout?.getTabAt(2)?.setIcon(tabIcons[2])
        tabLayout?.getTabAt(2)?.icon?.setColorFilter(Color.parseColor("#E0E0E0"), PorterDuff.Mode.SRC_IN)
    }
}
