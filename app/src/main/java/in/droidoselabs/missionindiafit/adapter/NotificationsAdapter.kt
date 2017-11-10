package `in`.droidoselabs.missionindiafit.adapter

import `in`.droidoselabs.missionindiafit.R
import `in`.droidoselabs.missionindiafit.model.Notifications
import `in`.droidoselabs.missionindiafit.model.Posts
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by android on 8/26/17.
 */
class NotificationsAdapter(val context: Context, val notificationList: ArrayList<Notifications.NotificationItems>) : RecyclerView.Adapter<NotificationsAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        val mView = LayoutInflater.from(parent?.context).inflate(R.layout.row_notifications, parent, false)
        return MyViewHolder(mView)
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        holder?.bindItems(notificationList[position], context)
    }

    override fun getItemCount(): Int = notificationList.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(notificationList: Notifications.NotificationItems, context: Context) {
            val ivOne = itemView.findViewById<ImageView>(R.id.profile_image) as ImageView
            val tvMessage = itemView.findViewById<TextView>(R.id.tvMessage) as TextView
            val tvTime = itemView.findViewById<TextView>(R.id.tvTime) as TextView
            ivOne.setImageResource(notificationList.image)
            tvMessage.text =  Html.fromHtml(notificationList.message)
            tvTime.text = notificationList.time
//            tvTitle.text = menuList.title
//            ivIcon.setImageResource(menuList.icon)
        }

    }
}