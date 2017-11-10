package `in`.droidoselabs.missionindiafit.fragment

import `in`.droidoselabs.missionindiafit.R
import `in`.droidoselabs.missionindiafit.adapter.MenuAdapter
import `in`.droidoselabs.missionindiafit.adapter.PostsAdapter
import `in`.droidoselabs.missionindiafit.model.Menu
import `in`.droidoselabs.missionindiafit.model.Posts
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.cooltechworks.views.shimmer.ShimmerRecyclerView
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass.
 */
class FeedFragment : Fragment() {
    lateinit var postItems: ArrayList<Posts.PostsItems>
    lateinit var imagesArray: ArrayList<Int>

    companion object {
        fun getInstace(): FeedFragment {
            return FeedFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val mView = inflater!!.inflate(R.layout.fragment_feed, container, false) as View;
        val shimmerRecycler = mView.findViewById<ShimmerRecyclerView>(R.id.shimmer_recycler_view) as ShimmerRecyclerView
        shimmerRecycler.showShimmerAdapter()
        shimmerRecycler.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        postItems = ArrayList();
        for (i in 1..20) {
            if (i % 2 == 0) {
                imagesArray = ArrayList();
                imagesArray.add(R.drawable.four)
                imagesArray.add(R.drawable.thre)
                imagesArray.add(R.drawable.five)
                imagesArray.add(R.drawable.two)
                postItems.add(Posts.PostsItems(imagesArray))
            }
            else if (i % 3 == 0) {
                imagesArray = ArrayList();
                imagesArray.add(R.drawable.two)
                imagesArray.add(R.drawable.four)
                imagesArray.add(R.drawable.one)
                imagesArray.add(R.drawable.two)
                postItems.add(Posts.PostsItems(imagesArray))
            }
            else {
                imagesArray = ArrayList();
                imagesArray.add(R.drawable.one)
                imagesArray.add(R.drawable.five)
                imagesArray.add(R.drawable.one)
                imagesArray.add(R.drawable.two)
                postItems.add(Posts.PostsItems(imagesArray))
            }
        }

        shimmerRecycler.adapter = PostsAdapter(activity, postItems)
        return mView;
    }
}