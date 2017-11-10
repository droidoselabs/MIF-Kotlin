package `in`.droidoselabs.missionindiafit.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.NumberPicker.OnValueChangeListener
import `in`.droidoselabs.missionindiafit.activity.CommonData
import `in`.droidoselabs.missionindiafit.R
import `in`.droidoselabs.missionindiafit.adapter.WeightAdapter
import kotlinx.android.synthetic.main.fragment_weight.*
import java.text.DecimalFormat


/**
 * A simple [Fragment] subclass.
 */
class WeightFragment : Fragment() {
    lateinit var rvWeight: RecyclerView
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var npInches: NumberPicker
    lateinit var npFeet: NumberPicker
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val mView = inflater?.inflate(R.layout.fragment_weight, container, false) as View
        init(mView);
        setAdapter()
        return mView;
    }

    /**
     * Initializations
     */
    private fun init(mView: View) {
        rvWeight = mView.findViewById<RecyclerView>(R.id.rvWeight) as RecyclerView
        npFeet = mView.findViewById<NumberPicker>(R.id.npFeet) as NumberPicker
        npInches = mView.findViewById<NumberPicker>(R.id.npInches) as NumberPicker
    }

    /**
     * set adapter
     */
    private fun setAdapter() {
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rvWeight)
        linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvWeight.setLayoutManager(linearLayoutManager)
        rvWeight.adapter = WeightAdapter(199);
        scrollListener()
        npFeet.setOnValueChangedListener(OnValueChangeListener { picker, oldVal, newVal ->
            CommonData.saveHeight(((npFeet.value * 12) + npInches.value) * 2.54)
        })
        npInches.setOnValueChangedListener(OnValueChangeListener { picker, oldVal, newVal ->
            CommonData.saveHeight(((npFeet.value * 12) + npInches.value) * 2.54)
        })
    }

    /**
     * scroll listner for recycler view
     */
    private fun scrollListener() {
        rvWeight.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                setText()
            }
        })
    }

    /**
     * set weight text
     */
    private fun setText() {
        val kgWeight = (linearLayoutManager.findFirstVisibleItemPosition() + 20).toString() + getString(R.string.kg);
        tvKgWeight.setText(kgWeight)
        val twoDForm = DecimalFormat("#.#")
        val weight = java.lang.Double.valueOf(twoDForm.format((linearLayoutManager.findFirstVisibleItemPosition() + 20) * 2.2)).toString()
        val lbsWeight=weight + getString(R.string.lbs);
        tvLbsWeight.setText(lbsWeight)
        CommonData.saveWeight((linearLayoutManager.findFirstVisibleItemPosition() + 20))
        CommonData.saveHeight(((npFeet.value * 12) + npInches.value) * 2.54)
    }
}
