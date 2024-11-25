package com.developerxy

fun amOrPm(hours: Int): String {
    return if (hours < 12) "AM" else "PM"
}

fun formatAsDisplayTime(hours: Int, minutes: Int): String {
    return "${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}"
}