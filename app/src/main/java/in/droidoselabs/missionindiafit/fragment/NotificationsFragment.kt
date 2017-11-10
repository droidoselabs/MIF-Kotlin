package `in`.droidoselabs.missionindiafit.fragment

import `in`.droidoselabs.missionindiafit.R
import `in`.droidoselabs.missionindiafit.adapter.NotificationsAdapter
import `in`.droidoselabs.missionindiafit.model.Notifications
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.cooltechworks.views.shimmer.ShimmerRecyclerView

/**
 * A simple [Fragment] subclass.
 */
class NotificationsFragment : Fragment() {
    var notificationItems: ArrayList<Notifications.NotificationItems> = ArrayList()

    companion object {
        fun getInstace(): NotificationsFragment {
            return NotificationsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val mView = inflater!!.inflate(R.layout.fragment_notifications, container, false) as View;
        val shimmerRecycler = mView.findViewById<ShimmerRecyclerView>(R.id.shimmer_recycler_view) as ShimmerRecyclerView
        shimmerRecycler.showShimmerAdapter()
        notificationItems = ArrayList()
        for (i in 1..5) {
            shimmerRecycler.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
            notificationItems.add(Notifications.NotificationItems(R.drawable.rajat,
                    "Rajat Dhamija commented on your post",
                    "Oct 21, 2017 12:00 am"))

            notificationItems.add(Notifications.NotificationItems(R.drawable.ranveer,
                    "Ranveer Singh mentioned you in a comment in a post by Mission India Fit",
                    "Oct 21, 2017 11:00 am"))

            notificationItems.add(Notifications.NotificationItems(R.drawable.pramod,
                    "Pramod Garg liked you post",
                    "Oct 21, 2017 10:00 am"))
        }
        shimmerRecycler.adapter = NotificationsAdapter(activity, notificationItems)
        return mView;
    }
}