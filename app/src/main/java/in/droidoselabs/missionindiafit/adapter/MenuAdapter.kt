package `in`.droidoselabs.missionindiafit.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import `in`.droidoselabs.missionindiafit.R
import `in`.droidoselabs.missionindiafit.model.Menu

/**
 * Created by android on 8/26/17.
 */
class MenuAdapter(val context: Context, val menuList: ArrayList<Menu.MenuItems>) : RecyclerView.Adapter<MenuAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        val mView = LayoutInflater.from(parent?.context).inflate(R.layout.row_menu, parent, false)
        return MyViewHolder(mView)
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        holder?.bindItems(menuList[position], context)
    }

    override fun getItemCount(): Int = menuList.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(menuList: Menu.MenuItems, context: Context) {
            val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle) as TextView
            val ivIcon = itemView.findViewById<ImageView>(R.id.ivIcon) as ImageView
            tvTitle.text = menuList.title
            ivIcon.setImageResource(menuList.icon)
        }

    }
}