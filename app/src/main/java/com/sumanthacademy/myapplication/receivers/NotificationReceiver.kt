package com.sumanthacademy.myapplication.receivers

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.graphics.drawable.toBitmap
import com.sumanthacademy.myapplication.MainActivity
import com.sumanthacademy.myapplication.R

class NotificationReceiver: BroadcastReceiver() {

    private val CHANNEL_ID = "Notification"
    private var notificationId:Int = System.currentTimeMillis().toInt()

    override fun onReceive(context: Context?, p1: Intent?) {
        if (context != null) {
            val builder = NotificationCompat.Builder(context,CHANNEL_ID)
            val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            } else {
                PendingIntent.FLAG_UPDATE_CURRENT
            }
            val bitmap = context.getDrawable(R.drawable.quotation_img)?.toBitmap()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                var channel = NotificationChannel(CHANNEL_ID,CHANNEL_ID,NotificationManager.IMPORTANCE_HIGH)
                val manager:NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                manager.createNotificationChannel(channel)
                builder.setSmallIcon(R.drawable.mind)
                    .setContentTitle("Todo Remainder")
                    .setContentText("You have a ${p1?.getStringExtra("name")} todo which is ${p1?.getStringExtra("progress")}")
                    .setContentIntent(PendingIntent.getActivity(context,0,Intent(context,MainActivity::class.java),flags))  //for click and navigate to specified intent
                    .setAutoCancel(true) //clear notification after clicked and opened app
                    .setLargeIcon(bitmap)
                    .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null as Bitmap?))
            } else {
                builder.setSmallIcon(R.drawable.mind)
                    .setContentTitle("Todo Remainder")
                    .setContentText("You have a todo to complete")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentIntent(PendingIntent.getActivity(context,0,Intent(context,MainActivity::class.java),flags))
                    .setAutoCancel(true)
                    .setLargeIcon(bitmap)
                    .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null as Bitmap?))
            }
            with(NotificationManagerCompat.from(context)){
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                notify(notificationId,builder.build())
            }
        }
    }
}