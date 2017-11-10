package `in`.droidoselabs.missionindiafit.fragment

import `in`.droidoselabs.missionindiafit.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {
    companion object {
        fun getInstace(): ProfileFragment {
            return ProfileFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val mView = inflater!!.inflate(R.layout.fragment_profile, container, false) as View;
//        val shimmerRecycler = mView.findViewById<ShimmerRecyclerView>(R.id.shimmer_recycler_view) as ShimmerRecyclerView
//        shimmerRecycler.showShimmerAdapter()
        return mView;
    }
}