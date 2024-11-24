package com.developerxy

fun Byte.toSelectedDaysList(): List<Int> {
    return (0 until 8)
        .filter { (this.toInt() shr it) and 1 == 1 }
}

fun List<Int>.toSelectedDaysByte(): Byte {
    require(size <= 8) { "Byte can only represent up to 8 bits." }
    return fold(0) { acc, bit -> acc or (1 shl bit) }.toByte()
}

fun amOrPm(hours: Int): String {
    return if (hours < 12) "AM" else "PM"
}

fun formatAsDisplayTime(hours: Int, minutes: Int): String {
    return "${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}"
}