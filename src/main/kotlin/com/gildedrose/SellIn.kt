package com.gildedrose

class SellIn(val sellIn: Int) {
    fun decrease(): SellIn = SellIn(sellIn - 1)
    fun passed(): Boolean = sellIn < 0
    fun restAtMax(nDays: Int) = sellIn <= nDays
}