package dev.wizrad.helloandroid.models

import dev.wizrad.helloandroid.R

enum class Region(
    val code: String,
    val nameKey: Int) {

    BRAZIL("br", R.string.region_br),
    EU_EAST("eune", R.string.region_eune),
    EU_WEST("euw", R.string.region_euw),
    KOREA("kr", R.string.region_kr),
    LA_NORTH("lan", R.string.region_lan),
    LA_SOUTH("las", R.string.region_las),
    NA("na", R.string.region_na),
    OCEANIA("oce", R.string.region_oce),
    RUSSIA("ru", R.string.region_ru),
    TURKEY("tr", R.string.region_tr)
}
