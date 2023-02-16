package com.nsa.musicwiki.extra

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.nsa.musicwiki.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Util {

    fun showToast(context: Context, text: String){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
    }

    fun showProgress(navController: NavController){
        navController.navigate(
            R.id.fragmentProgress
        )
    }
    fun hideProgress(navController: NavController,destination: Int){
        navController.popBackStack(destinationId = destination, inclusive = false, saveState = false)
    }
    fun getTimeFromSeconds(sec:String):String{
        var input=0
        return try{
            if(!sec.isNullOrEmpty()){
                input=sec.toInt()
            }
            if(input==0){
                return ""
            }
            val minutes: Int = input % 3600 / 60
            val seconds: Int = input % 3600 % 60

            " | $minutes:$seconds mins"

        }catch (e:Exception){
            ""
        }
    }
    fun getPlayCountText(count: Long): String? {
        if (count < 1000) return "" + count
        val exp = (Math.log(count.toDouble()) / Math.log(1000.0)).toInt()
        return String.format(
            "%.1f %c",
            count / Math.pow(1000.0, exp.toDouble()),
            "KMGTPE"[exp - 1]
        )
    }
    //loading image with coroutine
    fun loadImage(link: String, imageView: ImageView) {
        CoroutineScope(Dispatchers.IO).launch {
            Glide.with(imageView.context)
                .load(link)
                .centerCrop()
                .error(R.drawable.splach_bg)
                .into(object : CustomTarget<Drawable?>() {
                    override fun onLoadCleared(placeholder: Drawable?) {}
                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        super.onLoadFailed(errorDrawable)
                        CoroutineScope(Dispatchers.Main).launch {
                            imageView.setImageDrawable(errorDrawable)
                        }
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable?>?
                    ) {
                        CoroutineScope(Dispatchers.Main).launch {
                            imageView.setImageDrawable(resource)
                        }
                    }
                })
        }
    }

}