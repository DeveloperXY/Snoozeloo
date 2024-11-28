package com.developerxy.domain

fun List<Int>.toSelectedDaysByte(): Byte {
    require(size <= 8) { "Byte can only represent up to 8 bits." }
    return fold(0) { acc, bit -> acc or (1 shl bit) }.toByte()
}