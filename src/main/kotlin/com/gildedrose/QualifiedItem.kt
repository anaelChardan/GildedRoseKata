package com.gildedrose

interface QualifiedItem {
    fun toItem(): Item
    fun afterADay(): QualifiedItem
}

sealed class AbstractItem() : QualifiedItem {
    abstract val item: Item

    internal abstract fun quality(quality: Int): Quality
    internal abstract fun build(newItem: Item): QualifiedItem

    override fun toItem(): Item = item
    override  fun afterADay(): QualifiedItem {
        return build(
                Item(
                        item.name,
                        SellIn(item.sellIn).decrease().sellIn,
                        quality(item.quality).update(SellIn(item.sellIn)).toInt()
                )
        )

    }

    override fun toString() = item.toString()
}

data class IncreasingQualityItem(override val item: Item): AbstractItem() {
    override fun quality(quality: Int): Quality = IncreasingQuality(quality)
    override fun build(newItem: Item): QualifiedItem = IncreasingQualityItem(newItem)
}

data class DecreasingQualityItem(override val item: Item): AbstractItem() {
    override fun quality(quality: Int): Quality = DecreasingQuality(quality)
    override fun build(newItem: Item): QualifiedItem = DecreasingQualityItem(newItem)
}

data class LegendaryQualityItem(override val item: Item) : AbstractItem() {
    override fun quality(quality: Int): Quality = LegendaryQuality(quality)
    override fun build(newItem: Item): QualifiedItem = LegendaryQualityItem(newItem)
}

data class SpecialQualityItem(override val item: Item) : AbstractItem() {
    override fun quality(quality: Int): Quality = SpecialQuality(quality)
    override fun build(newItem: Item): QualifiedItem = SpecialQualityItem(newItem)
}