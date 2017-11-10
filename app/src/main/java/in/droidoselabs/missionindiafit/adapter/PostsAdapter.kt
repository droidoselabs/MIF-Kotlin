package `in`.droidoselabs.missionindiafit.adapter

import `in`.droidoselabs.missionindiafit.R
import `in`.droidoselabs.missionindiafit.model.Menu
import `in`.droidoselabs.missionindiafit.model.Posts
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by android on 8/26/17.
 */
class PostsAdapter(val context: Context, val postList: ArrayList<Posts.PostsItems>) : RecyclerView.Adapter<PostsAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        val mView = LayoutInflater.from(parent?.context).inflate(R.layout.row_post, parent, false)
        return MyViewHolder(mView)
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        holder?.bindItems(postList[position], context)
    }

    override fun getItemCount(): Int = postList.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(postList: Posts.PostsItems, context: Context) {
            val ivOne = itemView.findViewById<ImageView>(R.id.imageOne) as ImageView
            val ivTwo = itemView.findViewById<ImageView>(R.id.imageTwo) as ImageView
            val ivThree = itemView.findViewById<ImageView>(R.id.imageThree) as ImageView
            ivOne.setImageResource(postList.images[0])
            ivTwo.setImageResource(postList.images[1])
            ivThree.setImageResource(postList.images[2])
//            tvTitle.text = menuList.title
//            ivIcon.setImageResource(menuList.icon)
        }

    }
}