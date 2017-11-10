package `in`.droidoselabs.missionindiafit.fragment


import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ScrollView
import `in`.droidoselabs.missionindiafit.CommonData
import `in`.droidoselabs.missionindiafit.IntroActivity
import `in`.droidoselabs.missionindiafit.R
import `in`.droidoselabs.missionindiafit.utils.ValidateEditText
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView


/**
 * A simple [Fragment] subclass.
 */
class BasicDetailsFragment : Fragment(), View.OnClickListener {

    lateinit var uri: Uri
    lateinit var animation: ObjectAnimator
    lateinit var profileImage: ImageView
    lateinit var svForm: ScrollView
    lateinit var btnContinue: Button
    lateinit var name: EditText
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var cpassword: EditText
    lateinit var introActivity: IntroActivity
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val mView = inflater?.inflate(R.layout.fragment_basic_details, container, false)
        profileImage = mView?.findViewById<ImageView>(R.id.profile_image) as ImageView
        btnContinue = mView.findViewById<Button>(R.id.btnContinue) as Button
        svForm = mView.findViewById<View>(R.id.svForm) as ScrollView
        name = mView.findViewById<EditText>(R.id.nameText) as EditText
        email = mView.findViewById<EditText>(R.id.emailText) as EditText
        password = mView.findViewById<EditText>(R.id.passwordText) as EditText
        cpassword = mView.findViewById<EditText>(R.id.cpasswordText) as EditText
        profileImage.setOnClickListener(this)
        btnContinue.setOnClickListener(this)
        introActivity = activity as IntroActivity
        return mView
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.profile_image -> imageCrop()
            R.id.btnContinue -> saveDetails()
        }

    }

    /**
     * Save Details
     */
    private fun saveDetails() {
        if (validated()) {
            CommonData.saveProfilePic(uri)
            CommonData.saveName(name.text.toString().trim())
            CommonData.saveEmail(email.text.toString().trim())
            CommonData.savePassword(password.text.toString().trim())
            CommonData.saveCurrentPage(2)
            introActivity.changePage()
        }
    }

    private fun validated(): Boolean {
        return ValidateEditText.checkName(name, true)
                && ValidateEditText.checkEmail(email)
                && ValidateEditText.checkPassword(password, false)
                && ValidateEditText.checkPassword(cpassword, true)
    }

    private fun imageCrop() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setCropShape(CropImageView.CropShape.OVAL)
                .setAspectRatio(1, 1)
                .start(getContext(), this);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                uri = result.uri
                CommonData.saveProfilePic(uri)
                profileImage.setImageURI(uri)
                val scale = PropertyValuesHolder.ofFloat(View.SCALE_X, 0.55F)
                val scaley = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.55F)
                val translate = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, -500.0F)
                animation = ObjectAnimator.ofPropertyValuesHolder(profileImage, scale, scaley, translate)
                animation.duration = 1000
                Handler().postDelayed({
                    animation.start()
                }, 400)
                Handler().postDelayed({
                    svForm.visibility = View.VISIBLE
                }, 1300)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
                Log.e(TAG, "onActivityResult: " + error)
            }
        }
    }
}