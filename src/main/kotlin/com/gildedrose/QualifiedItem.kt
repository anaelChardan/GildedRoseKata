package com.gildedrose

interface QualifiedItem {
    fun toItem(): Item
    fun afterADay(): QualifiedItem
}

data class LegendaryItem(private val item: Item) : QualifiedItem {
    override fun toItem(): Item = item
    override fun afterADay(): QualifiedItem {
        return LegendaryItem(
                Item(
                        item.name,
                        SellIn(item.sellIn).decrease().sellIn,
                        LegendaryQuality(item.quality).update(SellIn(item.sellIn)).toInt()
                )
        )
    }

}