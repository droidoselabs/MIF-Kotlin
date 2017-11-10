package `in`.droidoselabs.missionindiafit.activity

import `in`.droidoselabs.missionindiafit.R
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.View.*
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar.*
import `in`.droidoselabs.missionindiafit.adapter.MenuAdapter
import `in`.droidoselabs.missionindiafit.fragment.HomeFragment
import `in`.droidoselabs.missionindiafit.model.Menu.MenuItems
import `in`.droidoselabs.missionindiafit.utils.CustomAlertDialog
import `in`.droidoselabs.missionindiafit.utils.RecyclerItemClickListeners
import android.app.FragmentTransaction
import com.google.firebase.auth.FirebaseAuth


class MainActivity : BaseActivity(), OnClickListener {

    var isOpened = false;
    lateinit var closeAnimation: ObjectAnimator
    lateinit var openAnimation: ObjectAnimator
    lateinit var mAuth: FirebaseAuth
    lateinit var menuItems: ArrayList<MenuItems>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            init()
            listeners()
        } else {
            startActivity(Intent(this@MainActivity, IntroActivity::class.java))
            finish()
        }
    }

    /**
     * Initializations
     */
    private fun init() {
        llMain.visibility = VISIBLE
        profile_image.setImageURI(CommonData.getprofilePic())
        tvName.text = CommonData.getName()
        tvEmail.text = CommonData.getEmail()
        rvMain.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayout.VERTICAL, false)
        menuItems = ArrayList();
        menuItems.add(MenuItems(getString(R.string.home), R.drawable.ic_003_home))
        menuItems.add(MenuItems(getString(R.string.nutritional_info), R.drawable.ic_002_milk_products))
        menuItems.add(MenuItems(getString(R.string.recipies), R.drawable.ic_chef))
        menuItems.add(MenuItems(getString(R.string.workout_plans), R.drawable.ic_001_stick_man_running_on_a_treadmill))
        menuItems.add(MenuItems(getString(R.string.logout), R.drawable.ic_logout))
        rvMain.adapter = MenuAdapter(this, menuItems)
        changeFragment(HomeFragment.getInstace(), getString(R.string.home));
    }

    /**
     * Change fragment
     */
    private fun changeFragment(targetFragment: HomeFragment, tag: String?) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, tag)
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
        closeDrawer()
    }

    /**
     * Open Drawer
     */
    private fun openDrawer() {
        val metrics = getResources().getDisplayMetrics()
        val width = metrics.widthPixels
        val pvhX = PropertyValuesHolder.ofFloat(TRANSLATION_X, width - 200.0F)
        val pvhXS = PropertyValuesHolder.ofFloat(SCALE_Y, 0.9F)
        openAnimation = ObjectAnimator.ofPropertyValuesHolder(llSub, pvhX, pvhXS)
        openAnimation.duration = 300 * 2
        openAnimation.start()
        isOpened = true
    }

    /**
     * Close Drawer
     */
    private fun closeDrawer() {
        val pvhX2 = PropertyValuesHolder.ofFloat(TRANSLATION_X, 0.0F)
        val pvhXS2 = PropertyValuesHolder.ofFloat(SCALE_Y, 1.0F)
        closeAnimation = ObjectAnimator.ofPropertyValuesHolder(llSub, pvhX2, pvhXS2)
        closeAnimation.duration = 300 * 2
        closeAnimation.start()
        isOpened = false
    }

    /**
     * listeners
     */
    private fun listeners() {
        ivMenu.setOnClickListener(this)
        rvMain.addOnItemTouchListener(
                RecyclerItemClickListeners(this, object : RecyclerItemClickListeners.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        when (position) {
                            0 -> changeFragment(HomeFragment.getInstace(), getString(R.string.home));
                            1 -> logoutUserDialog()
                            2 -> logoutUserDialog()
                            3 -> logoutUserDialog()
                            4 -> logoutUserDialog()
                            else -> {

                            }
                        }
                    }
                })
        )
    }

    /**
     * Logout User Dialog
     */
    private fun logoutUserDialog() {
        CustomAlertDialog.Builder(this@MainActivity)
                .setMessage("Are you sure you want to logout ?")
                .setPositiveButton(R.string.text_yes, object : CustomAlertDialog.CustomDialogInterface.OnClickListener {
                    override fun onClick() {
                        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
                        mAuth.signOut()
                        startActivity(Intent(this@MainActivity, IntroActivity::class.java))
                    }
                })
                .setNegativeButton(R.string.text_no, object : CustomAlertDialog.CustomDialogInterface.OnClickListener {
                    override fun onClick() {}
                })
                .show()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.ivMenu ->
                if (!isOpened) {
                    openDrawer()
                } else {
                    closeDrawer()
                }
            else -> {

            }
        }
    }

    override fun onBackPressed() {
        CustomAlertDialog.Builder(this@MainActivity)
                .setMessage("Are you sure you want to exit the application ?")
                .setPositiveButton(R.string.text_yes, object : CustomAlertDialog.CustomDialogInterface.OnClickListener {
                    override fun onClick() {
                        finishAffinity()
                    }
                })
                .setNegativeButton(R.string.text_no, object : CustomAlertDialog.CustomDialogInterface.OnClickListener {
                    override fun onClick() {}
                })
                .show()
    }
}
