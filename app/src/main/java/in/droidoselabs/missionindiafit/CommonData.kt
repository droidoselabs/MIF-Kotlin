package `in`.droidoselabs.missionindiafit

import android.net.Uri
import io.paperdb.Paper

/**
 * Created by android on 9/17/17.
 */
class CommonData {

    companion object {
        /**
         * Name
         */
        fun saveName(name: String) {
            Paper.book().write(PaperDbConstant.NAME, name)
        }

        fun getName(): String {
            return Paper.book().read(PaperDbConstant.NAME)
        }

        /**
         * Email
         */
        fun saveEmail(email: String) {
            Paper.book().write(PaperDbConstant.EMAIL, email)
        }

        fun getEmail(): String {
            return Paper.book().read(PaperDbConstant.EMAIL)
        }

        /**
         * Password
         */
        fun savePassword(password: String) {
            Paper.book().write(PaperDbConstant.PASSWORD, password)
        }

        fun getPassword(): String {
            return Paper.book().read(PaperDbConstant.PASSWORD)
        }

        /**
         * gender
         */
        fun saveGender(gender: String) {
            Paper.book().write(PaperDbConstant.GENDER, gender)
        }

        fun getGender(): String {
            return Paper.book().read(PaperDbConstant.GENDER)
        }

        /**
         * Height
         */
        fun saveHeight(height: Double) {
            Paper.book().write(PaperDbConstant.HEIGHT, height)
        }

        fun getHeight(): Double {
            return Paper.book().read(PaperDbConstant.HEIGHT)
        }

        /**
         * Weight
         */
        fun saveWeight(weight: Int) {
            Paper.book().write(PaperDbConstant.WEIGHT, weight)
        }

        fun getWeight(): Int {
            return Paper.book().read(PaperDbConstant.WEIGHT)
        }

        /**
         * Body Type
         */
        fun saveBodyType(bodyType: String) {
            Paper.book().write(PaperDbConstant.BODY_TYPE, bodyType)
        }

        fun getBodyType(): String {
            return Paper.book().read(PaperDbConstant.BODY_TYPE)
        }

        /**
         * Current Page
         */
        fun saveCurrentPage(currentPage: Int) {
            Paper.book().write(PaperDbConstant.CURRENT_PAGE, currentPage)
        }

        fun getCurrentPage(): Int {
            return Paper.book().read(PaperDbConstant.CURRENT_PAGE)
        }

        /**
         * Image Uri
         */
        fun saveProfilePic(profilePic: Uri) {
            Paper.book().write(PaperDbConstant.PROFILE_PIC, profilePic)
        }

        fun getprofilePic(): Uri {
            return Paper.book().read(PaperDbConstant.PROFILE_PIC)
        }

        /**
         * Clear Data
         */
        fun clearData() {
            Paper.book().destroy()
        }

        /**
         * Is logged in
         */
        fun saveLogin(isLogin: Boolean) {
            Paper.book().write(PaperDbConstant.IS_LOGIN, isLogin)
        }

        fun getLogin(): Boolean {
            return Paper.book().read(PaperDbConstant.IS_LOGIN)
        }
    }
}