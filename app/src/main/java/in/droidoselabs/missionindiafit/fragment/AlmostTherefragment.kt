package `in`.droidoselabs.missionindiafit.fragment


import `in`.droidoselabs.missionindiafit.R
import `in`.droidoselabs.missionindiafit.activity.CommonData
import `in`.droidoselabs.missionindiafit.activity.HomeActivity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


/**
 * A simple [Fragment] subclass.
 */
class AlmostTherefragment : Fragment() {
    lateinit var running: ImageView
    lateinit var mAuth: FirebaseAuth
    lateinit var mView: View
    // Write a message to the database
    lateinit var database: FirebaseDatabase
    lateinit var myUsersRef: DatabaseReference
    lateinit var storageRef: StorageReference
    var storage = FirebaseStorage.getInstance()
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mView = inflater!!.inflate(R.layout.fragment_almost_there, container, false)
        database = FirebaseDatabase.getInstance()
        myUsersRef = database.getReference("Users")
        return mView
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            mAuth = FirebaseAuth.getInstance()
            storageRef = storage.getReference("ProfileImages");
            running = mView.findViewById<ImageView>(R.id.running) as ImageView
            Glide.with(this)
                    .load(R.drawable.gymming)
                    .into(running)
            this.mAuth.createUserWithEmailAndPassword(CommonData.getEmail(), CommonData.getPassword()).addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    val firebaseUser = this.mAuth.currentUser!!
                    val userData = HashMap<String, String>()
                    userData.put("Name", CommonData.getName())
                    userData.put("Email", CommonData.getEmail())
                    userData.put("Gender", CommonData.getGender())
                    userData.put("Height", CommonData.getHeight().toString())
                    userData.put("Weight", CommonData.getWeight().toString())
                    userData.put("BodyType", CommonData.getBodyType())
                    val childRef = storageRef.child("image.jpg")

                    //uploading the image
                    val uploadTask = childRef.putFile(CommonData.getprofilePic())

                    uploadTask.addOnSuccessListener {
                        myUsersRef.push().setValue(userData)
                        startActivity(Intent(activity, HomeActivity::class.java))
                    }.addOnFailureListener { e ->
                        Toast.makeText(activity, "Upload Failed -> " + e, Toast.LENGTH_SHORT).show()
                    }

                } else {
                    //Registration error
                }
            }
        }
    }
}
