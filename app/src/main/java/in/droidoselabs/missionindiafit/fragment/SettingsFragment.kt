package `in`.droidoselabs.missionindiafit.fragment

import `in`.droidoselabs.missionindiafit.R
import `in`.droidoselabs.missionindiafit.adapter.MenuAdapter
import `in`.droidoselabs.missionindiafit.model.Menu
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SimpleItemAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment : Fragment() {
    lateinit var menuItems: ArrayList<Menu.MenuItems>

    companion object {
        fun getInstace(): SettingsFragment {
            return SettingsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val mView = inflater!!.inflate(R.layout.fragment_settings, container, false) as View;
        val rvMain = mView.findViewById<RecyclerView>(R.id.rvMain) as RecyclerView
        rvMain.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        menuItems = ArrayList();
        menuItems.add(Menu.MenuItems(getString(R.string.nutritional_info), R.drawable.ic_002_milk_products))
        menuItems.add(Menu.MenuItems(getString(R.string.recipies), R.drawable.ic_chef))
        menuItems.add(Menu.MenuItems(getString(R.string.workout_plans), R.drawable.ic_001_stick_man_running_on_a_treadmill))
        menuItems.add(Menu.MenuItems(getString(R.string.action_settings), R.drawable.ic_settings))
        menuItems.add(Menu.MenuItems(getString(R.string.logout), R.drawable.ic_logout))
        rvMain.adapter = MenuAdapter(activity, menuItems)
        return mView;
    }
}