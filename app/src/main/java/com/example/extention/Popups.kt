package com.example.extention


import android.app.Activity
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.ViewTreeObserver.*
import android.widget.*
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.uicomponests.R
import com.google.android.material.card.MaterialCardView
import android.view.WindowManager
import androidx.cardview.widget.CardView


/** This function accepts layout resource as a parameter and show the dialog ,
with the help of this function you just need to use same ids in your layout
and pass the layout to the function and it will create the dialog for you
and also you can change the dialog color , image icon etc
Some Basic Requirements (Use Material CardView as a parent in your
layout as id {dialogView})
[titleText ID =dialog_titleTextView,message=dialog_messageTextView,
image=dialog_image,Positive Button=positiveBtn,Negative Button = negativeBtn]*/
/** if u need custom width not full dialog width then pass that width as a
parameter too in dialogWidth*/

fun Activity.showPopup(
    @LayoutRes viewResource: Int,
    onPositiveClick: (View) -> Unit,
    dialogWidth: Int? = null,
    title: String = "",
    message: String = "",
    isCancelable: Boolean = false,
    @ColorRes dialogColor: Int? = null,
    @DrawableRes iconId: Int? = null,
    onNegativeClick: ((View) -> Unit?)? = null,
) {
    try {
        val customDialogView = LayoutInflater.from(this).inflate(viewResource, null)
        val dialog = AlertDialog.Builder(this).setView(customDialogView).create()
        val inset = InsetDrawable(ColorDrawable(Color.TRANSPARENT), 10)
        dialog.window?.setBackgroundDrawable(inset)
        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
        val dialogCardView = customDialogView.findViewById<MaterialCardView>(R.id.dialogView)
        dialogColor?.let { color ->
            dialogCardView.setCardBackgroundColor(ContextCompat.getColor(this, color))
        }
        val positiveBtn = customDialogView.findViewById<Button>(R.id.positiveBtn)
        var negativeBtn: Button?
        var titleTextView: TextView?
        var messageTextView: TextView?
        var image: ImageView?
//        if (customDialogView.findViewById<Button>(R.id.negativeBtn) != null) {
//            negativeBtn = customDialogView.findViewById(R.id.negativeBtn)
//            negativeBtn.setOnClickListener {
//                onNegativeClick?.let {
//                    dialog.dismiss()
//                    it.invoke(customDialogView)
//                }
//            }
//        }
        if (customDialogView.findViewById<TextView>(R.id.dialog_titleTextView) != null) {
            titleTextView = customDialogView.findViewById(R.id.dialog_titleTextView)
            titleTextView.text = title
        }
        if (customDialogView.findViewById<TextView>(R.id.dialog_messageTextView) != null) {
            messageTextView = customDialogView.findViewById(R.id.dialog_messageTextView)
            messageTextView.text = message
        }
        if (customDialogView.findViewById<ImageView>(R.id.dialog_image) != null) {
            image = customDialogView.findViewById(R.id.dialog_image)
            iconId?.let {
                image.setImageResource(it)
            }
        }
        positiveBtn.setOnClickListener {
            dialog.dismiss()
            onPositiveClick(customDialogView)
        }
        dialog.setCancelable(isCancelable)
        dialog.show()


        dialogWidth?.let {
            val newWidth = it + 30
            dialog.window!!.setLayout(
                newWidth.toDp(),
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

    } catch (e: Exception) {
        Toast.makeText(this, "Error = ${e.message}", Toast.LENGTH_SHORT).show()
    }

}

fun View.addViewObserver(callback: (View) -> Unit) {
    val view = this
    view.viewTreeObserver.addOnGlobalLayoutListener(object :
        OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            view.viewTreeObserver.removeOnGlobalLayoutListener(this)
            callback(view)
        }
    })
}

fun Int.toDp() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()


