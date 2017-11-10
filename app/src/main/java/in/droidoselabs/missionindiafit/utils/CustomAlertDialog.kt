package `in`.droidoselabs.missionindiafit.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.support.annotation.Nullable
import android.support.annotation.StringRes
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import `in`.droidoselabs.missionindiafit.R


/**
 * Created by android on 9/17/17.
 */
class CustomAlertDialog(builder: Builder) {
    fun CustomAlertDialog(builder: AlertDialog.Builder) {}
    interface CustomDialogInterface {
        /**
         * The interface On click listener.
         */
        interface OnClickListener {
            /**
             * On click.
             */
            fun onClick()
        }

        /**
         * The interface On dismiss listener.
         */
        interface OnDismissListener {
            /**
             * On dismiss.
             */
            fun onDismiss()
        }

        /**
         * The interface On cancel listener.
         */
        interface OnCancelListener {
            /**
             * On cancel.
             */
            fun onCancel()
        }
    }

    class Builder(context: Context) {
            var mContext: Context? = context
            var mInflater: LayoutInflater? = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var mTitle: CharSequence? = null
            var mMessage: CharSequence? = null
            var mPositiveButtonText: CharSequence? = null
            var mPositiveButtonListener: CustomDialogInterface.OnClickListener? = null
            var mNegativeButtonText: CharSequence? = null
            var mNegativeButtonListener: CustomDialogInterface.OnClickListener? = null
            var mCancelable: Boolean = true
            var mOnCancelListener: CustomDialogInterface.OnCancelListener? = null
            var mOnDismissListener: CustomDialogInterface.OnDismissListener? = null
            var mBuilder: AlertDialog.Builder? = null
            var mAlertDialog: AlertDialog? = null

        fun Builder(context: Context) {
            mContext = context
            mCancelable = true
            mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        fun setTitle(@StringRes titleId: Int): Builder {
            mTitle = mContext?.getText(titleId)
            return this
        }

        fun setTitle(@Nullable title: CharSequence): Builder {
            mTitle = title
            return this
        }

        fun setMessage(@StringRes messageId: Int): Builder {
            mMessage = mContext?.getText(messageId)
            return this
        }

        fun setMessage(message: CharSequence?): Builder {
            mMessage = message
            return this
        }

        fun setPositiveButton(@StringRes textId: Int, listener: CustomDialogInterface.OnClickListener): Builder {
            mPositiveButtonText = mContext?.getText(textId)
            mPositiveButtonListener = listener
            return this
        }

        fun setPositiveButton(text: CharSequence, listener: CustomDialogInterface.OnClickListener): Builder {
            mPositiveButtonText = text
            mPositiveButtonListener = listener
            return this
        }

        fun setNegativeButton(@StringRes textId: Int, listener: CustomDialogInterface.OnClickListener): Builder {
            mNegativeButtonText = mContext?.getText(textId)
            mNegativeButtonListener = listener
            return this
        }

        fun setNegativeButton(text: CharSequence, listener: CustomDialogInterface.OnClickListener): Builder {
            mNegativeButtonText = text
            mNegativeButtonListener = listener
            return this
        }

        fun setCancelable(cancelable: Boolean): Builder {
            mCancelable = cancelable
            return this
        }

        fun setOnCancelListener(onCancelListener: CustomDialogInterface.OnCancelListener): Builder {
            mOnCancelListener = onCancelListener
            return this
        }

        fun setOnDismissListener(onDismissListener: CustomDialogInterface.OnDismissListener): Builder {
            mOnDismissListener = onDismissListener
            return this
        }

        fun create(): AlertDialog? {
            mBuilder = AlertDialog.Builder(mContext)
            val dialogView = mInflater?.inflate(R.layout.dialog_custom, null)
            mBuilder?.setView(dialogView)
            mAlertDialog = mBuilder?.create()
            val tvTitle = dialogView?.findViewById<TextView>(R.id.tvTitle) as TextView
            val tvMessage = dialogView.findViewById<TextView>(R.id.tvMessage) as TextView
            val btnNegative = dialogView.findViewById<Button>(R.id.btnNegative) as Button
            val btnPositive = dialogView.findViewById<Button>(R.id.btnPositive) as Button
            //set title
            if (mTitle != null) {
                tvTitle.text = mTitle
            } else {
                tvTitle.visibility = View.GONE
            }
            if (mMessage != null) {
                tvMessage.text = mMessage
            }
            //set positive button
            if (mPositiveButtonText != null) {
                btnPositive.setText(mPositiveButtonText)
                btnPositive.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(v: View) {
                        mAlertDialog?.dismiss()
                        if (mPositiveButtonListener != null) {
                            mPositiveButtonListener?.onClick()
                        }
                    }
                })
            } else {
                btnPositive.setVisibility(View.GONE)
            }
            //set negative button
            if (mNegativeButtonText != null) {
                btnNegative.setText(mNegativeButtonText)
                btnNegative.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(v: View) {
                        mAlertDialog?.dismiss()
                        if (mNegativeButtonListener != null) {
                            mNegativeButtonListener?.onClick()
                        }
                    }
                })
            } else {
                btnNegative.setVisibility(View.GONE)
            }
            //set Cancelable
            mAlertDialog?.setCancelable(mCancelable)
            mAlertDialog?.setCanceledOnTouchOutside(mCancelable)
            //set cancel listener
            if (mOnCancelListener != null) {
                mAlertDialog?.setOnCancelListener(DialogInterface.OnCancelListener {
                    if (mOnCancelListener != null) {
                        mOnCancelListener?.onCancel()
                    }
                })
            }
            //set dismiss listener
            if (mOnDismissListener != null) {
                mAlertDialog?.setOnDismissListener(DialogInterface.OnDismissListener {
                    if (mOnDismissListener != null) {
                        mOnDismissListener?.onDismiss()
                    }
                })
            }
            return mAlertDialog as AlertDialog?
        }

        fun show(): AlertDialog? {
            val dialog = create()
            dialog?.show()
            return dialog
        }

        /**
         * Build custom alert dialog.
         *
         * @return the custom alert dialog
         */
        fun build(): CustomAlertDialog {
            return CustomAlertDialog(this)
        }
    }
}