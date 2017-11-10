package `in`.droidoselabs.missionindiafit.activity

import `in`.droidoselabs.missionindiafit.R
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cooltechworks.views.shimmer.ShimmerRecyclerView
import com.google.firebase.auth.FirebaseAuth


class HomeActivity : BaseActivity() {
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        checkLogin();
    }

    private fun checkLogin() {
        mAuth = FirebaseAuth.getInstance()
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            init()
            listeners()
        } else {
            startActivity(Intent(this@HomeActivity, IntroActivity::class.java))
            finish()
        }
    }

    private fun init() {
        val shimmerRecycler = findViewById<ShimmerRecyclerView>(R.id.shimmer_recycler_view) as ShimmerRecyclerView
        shimmerRecycler.showShimmerAdapter()
    }

    private fun listeners() {

    }
}
