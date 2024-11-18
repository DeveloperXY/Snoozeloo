package com.developerxy.domain

import android.content.Context
import android.media.RingtoneManager
import android.net.Uri
import com.developerxy.model.RingtoneInfo

class FetchDefaultRingtonesUseCase(private val context: Context) {
    operator fun invoke(): List<RingtoneInfo> {
        val ringtoneManager = RingtoneManager(context).apply {
            setType(RingtoneManager.TYPE_ALARM)
        }

        val ringtoneList = mutableListOf<RingtoneInfo>()
        val cursor = ringtoneManager.cursor
        while (cursor.moveToNext()) {
            val title = cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX)
            val uriString = cursor.getString(RingtoneManager.URI_COLUMN_INDEX)
            val id = cursor.getString(RingtoneManager.ID_COLUMN_INDEX)
            val uri = Uri.parse("$uriString/$id")

            ringtoneList.add(RingtoneInfo(id, title, uri))
        }

        return ringtoneList
    }
}