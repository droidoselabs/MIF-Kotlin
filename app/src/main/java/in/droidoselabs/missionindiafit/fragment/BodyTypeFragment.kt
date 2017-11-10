package `in`.droidoselabs.missionindiafit.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import `in`.droidoselabs.missionindiafit.CommonData

import `in`.droidoselabs.missionindiafit.R
import kotlinx.android.synthetic.main.fragment_body_type.*


/**
 * A simple [Fragment] subclass.
 */
class BodyTypeFragment : Fragment(), View.OnClickListener {

    var ivBodyTypes: MutableList<ImageView> = mutableListOf<ImageView>()
    lateinit var tvBodyType: TextView
    lateinit var mView: View
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mView = inflater!!.inflate(R.layout.fragment_body_type, container, false)
        return mView
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            init(mView)
            listeners()
        }
    }

    /**
     * Initializations
     */
    private fun init(mView: View?) {
        CommonData.saveBodyType("Ectomorph")
        ivBodyTypes.add(mView?.findViewById<ImageView>(R.id.ivBodyOne) as ImageView)
        ivBodyTypes.add(mView.findViewById<ImageView>(R.id.ivBodyTwo) as ImageView)
        ivBodyTypes.add(mView.findViewById<ImageView>(R.id.ivBodyThree) as ImageView)
        tvBodyType = mView.findViewById<TextView>(R.id.tvBodyType) as TextView
        if (CommonData.getGender().equals("Female")) {
            ivBodyOne.setImageResource(R.drawable.girl_one)
            ivBodyTwo.setImageResource(R.drawable.girl_twoo)
            ivBodyThree.setImageResource(R.drawable.girl_thre)
        } else {
            ivBodyOne.setImageResource(R.drawable.boy_one)
            ivBodyTwo.setImageResource(R.drawable.boy_two)
            ivBodyThree.setImageResource(R.drawable.boy_three)
        }
    }

    /**
     * Listeners
     */
    private fun listeners() {
        for (index in ivBodyTypes.indices)
            ivBodyTypes[index].setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.ivBodyOne -> setBodyType("Ectomorph")
            R.id.ivBodyTwo -> setBodyType("Mesomorph")
            R.id.ivBodyThree -> setBodyType("Endomorph")
        }
    }

    /**
     * Set body type
     */
    private fun setBodyType(bodyType: String) {
        tvBodyType.text = bodyType
        CommonData.saveBodyType(bodyType)
    }
}
