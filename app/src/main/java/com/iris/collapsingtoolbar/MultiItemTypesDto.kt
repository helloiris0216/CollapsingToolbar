package com.iris.collapsingtoolbar

//data class MultiItemTypesDto(
//    @SerializedName("name") val name: String,
//    @SerializedName("imageUrl") val imageUrl: String,
//    val viewType: Int
//)  {
//    companion object {
//        const val TYPE_CARD_SLIDER = 0
//        const val TYPE_ITEMS = 1
//    }
//}


open class MultiItemTypesDto(
    open val viewType: Int
) {
    companion object {
        const val TYPE_CARD_SLIDER = 0
        const val TYPE_ITEMS = 1
    }
}

data class CardSliderItem(
    val imageUrls: List<String>,
) : MultiItemTypesDto(TYPE_CARD_SLIDER)

data class Item(
    val name: String,
    val imageUrl: String
) : MultiItemTypesDto(TYPE_ITEMS)

