package `in`.droidoselabs.missionindiafit.model

import android.net.Uri

/**
 * Created by android on 9/17/17.
 */
data class User(var Name: String,
                    var Email: String,
                    var Password: String,
                    var Height: Int,
                    var Weight: Int,
                    var Gender: String,
                    var BodyType: String,
                    var profileImage: Uri)