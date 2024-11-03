package com.sumanthacademy.myapplication

import android.app.Dialog
import android.content.Context
import android.content.pm.ActivityInfo.WindowLayout
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Looper
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager2.widget.ViewPager2
import com.sumanthacademy.myapplication.adapter.IntroImageAdapter
import com.sumanthacademy.myapplication.databinding.PopupIntroBinding
import com.sumanthacademy.myapplication.model.IntroImageItem
import java.util.UUID

class PopupIntro(val fcontext: Context, val packageName:String):Dialog(fcontext),View.OnClickListener {
    private var binding:PopupIntroBinding? = null
    lateinit private var dotsImages:Array<ImageView>
    lateinit var pageChangeCallback: ViewPager2.OnPageChangeCallback
    lateinit var onResponse: (l:ResponseType) -> Unit
    var pagePosition:Int = 0

    enum class ResponseType{
        CANCEL
    }

    var params = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply {
        setMargins(8,0,8,0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        binding = PopupIntroBinding.inflate(layoutInflater)
        binding?.root?.let { setContentView(it) }

        window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        window?.setGravity(Gravity.CENTER)
        val metrics = DisplayMetrics()
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        display.getMetrics(metrics)
        window?.attributes?.width = (metrics.widthPixels * 0.8).toInt()  //90% of sccreen
        window?.attributes?.horizontalMargin = (metrics.widthPixels - 10).toFloat()

        binding?.let {
            it.close.setOnClickListener(this)

            window?.setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            window?.setGravity(Gravity.CENTER)
        }

        var imagesList = arrayListOf(
            IntroImageItem(
                UUID.randomUUID().toString(),
                "android.resource://" + packageName + "/" + R.drawable.add_todo,
                "Add Your Todos Here!"
            ),
            IntroImageItem(
                UUID.randomUUID().toString(),
                "android.resource://" + packageName + "/" + R.drawable.edit_todo,
                "Click on Todo item to edit"
            ),
            IntroImageItem(
                UUID.randomUUID().toString(),
                "android.resource://" + packageName + "/" + R.drawable.delete_todo,
                "Delete Your Todo Here!"
            )
        )

        binding?.let { it1 ->
            var adapter = IntroImageAdapter()
            it1.viewPager.adapter = adapter
            adapter.submitList(imagesList)

            dotsImages = Array(imagesList.size) { ImageView(context) }
            dotsImages.forEach {
                it.setImageResource(R.drawable.non_active_dot)
                it1.slideDots.addView(it,params)
            }
        }

        dotsImages[0].setImageResource(R.drawable.active_dot) //by default first one is selected

        pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                println("page selected")
                pagePosition = position
                super.onPageSelected(position)
                dotsImages.mapIndexed { index, imageView ->
                    if (position == index){
                        imageView.setImageResource(R.drawable.active_dot)
                    }else{
                        imageView.setImageResource(R.drawable.non_active_dot)
                    }
                }
            }
        }

        binding?.let{
            it.viewPager.registerOnPageChangeCallback(pageChangeCallback)
            it.viewPager.autoScroll(1500)
        }

    }

    fun showDialog(listener: (l:ResponseType) -> Unit) {
        onResponse = listener
        this.show()
    }

    private fun ViewPager2.autoScroll(interval:Long){
        val handler = android.os.Handler(Looper.getMainLooper())
        var runnable = object : Runnable {
            override fun run() {
                val count = adapter?.itemCount ?: 0
                setCurrentItem(pagePosition++ % count, true)
                handler.postDelayed(this,interval)
            }
        }
        handler.post(runnable)
    }

    override fun onClick(view: View?) {
        when(view){
            binding?.close -> {
                onResponse(ResponseType.CANCEL)
                this.cancel()
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        binding?.viewPager?.unregisterOnPageChangeCallback(pageChangeCallback)   //should unregister otherwise page selection will run continuously
    }

}