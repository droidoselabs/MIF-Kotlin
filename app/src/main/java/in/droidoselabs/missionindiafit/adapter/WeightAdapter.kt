package `in`.droidoselabs.missionindiafit.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import `in`.droidoselabs.missionindiafit.R

/**
 * Created by android on 9/17/17.
 */

class WeightAdapter(var count: Int) : RecyclerView.Adapter<WeightAdapter.MyViewHolder>() {
    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        if (((position + 1) % 5) == 0) {
            holder?.viewLarge?.setVisibility(View.VISIBLE);
        } else {
            holder?.viewLarge?.setVisibility(View.GONE);
        }
    }

    override fun getItemCount(): Int {
        return count
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): WeightAdapter.MyViewHolder {
        val mView = LayoutInflater.from(parent?.context).inflate(R.layout.weight_row, parent, false)
        return WeightAdapter.MyViewHolder(mView)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val viewLarge = itemView.findViewById<View>(R.id.viewLarge)
    }

}
