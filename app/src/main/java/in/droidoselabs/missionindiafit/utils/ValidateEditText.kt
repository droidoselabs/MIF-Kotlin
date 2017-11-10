package `in`.droidoselabs.missionindiafit.utils

import android.content.Context
import android.util.Patterns
import android.widget.EditText
import `in`.droidoselabs.missionindiafit.R

class ValidateEditText
/**
 * Empty Constructor
 * not called
 */
private constructor() {
    /**
     * Method to validate field is empty or not
     *
     * @param et           instance of edit text
     * @param errorMessage error message
     * @return boolean
     */
    fun genericEmpty(et: EditText, errorMessage: String): Boolean {
        return if (et.text.toString().trim { it <= ' ' }.isEmpty()) {
            setErrorAndRequestFoucs(et, errorMessage)
        } else true
    }

    companion object {
        private val REGEX_MORE_SPACE = "[ ]{2,}"
        private val PASSWORD_LENGTH = 6
        private val MIN_NAME_LENGTH = 2
        /**
         * @param et instance of edit text
         * @return boolean
         */
        fun genericEmpty(et: EditText): Boolean {
            return et.text.toString().trim { it <= ' ' }.isEmpty()
        }

        /**
         * Method to validate email id
         *
         * @param et instance of edit text
         * @return boolean
         */
        fun checkEmail(et: EditText): Boolean {
            val email = et.text.toString().trim { it <= ' ' }
            if (genericEmpty(et)) {
                return setErrorAndRequestFoucs(et, getContext(et).getString(R.string.error_email_field_empty))
            }
            return if (!email.matches(Patterns.EMAIL_ADDRESS.toString().toRegex())) {
                setErrorAndRequestFoucs(et, getContext(et).getString(R.string.error_invalid_email))
            } else true
        }

        /**
         * Method to validate password field
         *
         * @param et        instance of edit text
         * @param isConfirm (true for confirm password & false for password field)
         * @return boolean
         */
        fun checkPassword(et: EditText, isConfirm: Boolean): Boolean {
            val password = et.text.toString().trim { it <= ' ' }
            if (genericEmpty(et)) {
                val msg: String
                if (isConfirm) {
                    msg = getContext(et).getString(R.string.error_confirm_password_field_empty)
                } else {
                    msg = getContext(et).getString(R.string.error_password_field_empty)
                }
                return setErrorAndRequestFoucs(et, msg)
            }
            return if (password.length < PASSWORD_LENGTH) {
                setErrorAndRequestFoucs(et, getContext(et).getString(R.string.error_password_must_be_greater_than_5_character))
            } else true
        }

        /**
         * Method to validate phoneNumber Field
         *
         * @param et instance of edit text
         * @return boolean
         */
        fun checkPhoneNumber(et: EditText): Boolean {
            val phoneNumber = et.text.toString().trim { it <= ' ' }
            if (genericEmpty(et)) {
                return setErrorAndRequestFoucs(et, getContext(et).getString(R.string.error_phone_number_field_empty))
            }
            return if (phoneNumber.length < PASSWORD_LENGTH) {
                setErrorAndRequestFoucs(et, getContext(et).getString(R.string.error_invalid_phone_number))
            } else true
        }

        /**
         * Method to validate name field
         *
         * @param et          et instance of edit text
         * @param isFirstName (true for first name & false for last name)
         * @return boolean
         */
        fun checkName(et: EditText, isFirstName: Boolean): Boolean {
            val name = et.text.toString().trim { it <= ' ' }.replace(REGEX_MORE_SPACE.toRegex(), " ")
            et.setText(StringUtil.toCamelCase(name))
            if (genericEmpty(et)) {
                val msg: String
                if (isFirstName) {
                    msg = getContext(et).getString(R.string.error_first_name_field_empty)
                } else {
                    msg = getContext(et).getString(R.string.error_last_name_field_empty)
                }
                return setErrorAndRequestFoucs(et, msg)
            }
            //It takes alphabets and spaces and dots...
            if (!name.matches("^[\\p{L} .'-]+$".toRegex())) {
                val msg: String
                if (isFirstName) {
                    msg = getContext(et).getString(R.string.error_first_name_special_number_character)
                } else {
                    msg = getContext(et).getString(R.string.error_last_name_special_number_character)
                }
                return setErrorAndRequestFoucs(et, msg)
            }
            if (name.length < MIN_NAME_LENGTH) {
                val msg: String
                if (isFirstName) {
                    msg = getContext(et).getString(R.string.error_first_name_two_character_long)
                } else {
                    msg = getContext(et).getString(R.string.error_last_name_two_character_long)
                }
                return setErrorAndRequestFoucs(et, msg)
            }
            return true
        }

        /**
         * Method to validate password & confirm password field
         *
         * @param et  et instance of edit text
         * @param etc etc instance of edit text
         * @return boolean
         */
        fun comparePassword(et: EditText, etc: EditText): Boolean {
            if (et.text.toString().trim { it <= ' ' } != etc.text.toString().trim { it <= ' ' }) {
                setErrorAndRequestFoucs(et, getContext(et).getString(R.string.error_password_mismatch))
                return setErrorAndRequestFoucs(et, getContext(etc).getString(R.string.error_password_mismatch))
            }
            return true
        }

        /**
         * @param et           instance of edit text
         * @param errorMessage error msg
         * @return boolean
         */
        private fun setErrorAndRequestFoucs(et: EditText, errorMessage: String): Boolean {
            CustomAlertDialog.Builder(getContext(et))
                    .setMessage(errorMessage)
                    .setPositiveButton(R.string.text_ok, object : CustomAlertDialog.CustomDialogInterface.OnClickListener {
                        override fun onClick() {}
                    })
                    .show()
            //et.setError(errorMessage);
            et.setSelection(et.text.toString().length)
            et.isHovered = true
            et.requestFocus()
            return false
        }

        /**
         * @param et instance of edit text
         * @return context
         */
        private fun getContext(et: EditText): Context {
            return et.context
        }
    }
}