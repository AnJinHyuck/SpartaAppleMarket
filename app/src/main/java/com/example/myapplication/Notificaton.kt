package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity


fun notification(context: Context) {
    val manager =context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (!NotificationManagerCompat.from(context).areNotificationsEnabled()) {
            // 알림 권한이 없다면, 사용자에게 권한 요청
            val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
            }
            context.startActivity(intent) //context 받은 부분
        }
    }


    val builder: NotificationCompat.Builder
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        // 26 버전 이상
        val channelId = "one-channel"
        val channelName = "My Channel One"

        val channel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            // 채널에 다양한 정보 설정
            description = "My Channel One Description"
            setShowBadge(true)
            val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ALARM)
                .build()
            setSound(uri, audioAttributes)
            enableVibration(true)
        }
        // 채널을 NotificationManager에 등록
        manager.createNotificationChannel(channel)

        // 채널을 이용하여 builder 생성
        builder = NotificationCompat.Builder(context, channelId)

    } else {
        // 26 버전 이하
        builder = NotificationCompat.Builder(context)
    }


    val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.downarrow)
    val intent = Intent(context, MainActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    val pendingIntent = PendingIntent.getActivity(
        context,
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
    // 알림의 기본 정보
    builder.run {
        setSmallIcon(R.mipmap.ic_launcher)
        setWhen(System.currentTimeMillis())
        setContentTitle("키워드 알림.")
        setContentText("설정한 키워드에 대한 알림이 도착했습니다!")
//        setStyle(
//
//            NotificationCompat.BigTextStyle()
//                .setSummaryText("지금")
//        )
//        setLargeIcon(bitmap)
//            setStyle(NotificationCompat.BigPictureStyle()
//                    .bigPicture(bitmap)
//                    .bigLargeIcon(null))  // hide largeIcon while expanding
//        addAction(
//            R.mipmap.ic_launcher, "Action", pendingIntent
//        )
    }
    manager.notify(11, builder.build())
}
