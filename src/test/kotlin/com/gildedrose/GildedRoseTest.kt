package com.gildedrose

import org.junit.Assert.*
import org.junit.Test

class GildedRoseTest {

    @Test fun theQualityCannotBeLowerThan0() {
        val items = arrayOf<Item>(Item("foo", 1, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals("foo", app.items[0].name)
        assertEquals(0, app.items[0].quality)
    }

    @Test fun theQualityDecreasesAndSellInDateDecreasesToo() {
        val items = arrayOf<Item>(Item("foo", 1, 50))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals("foo", app.items[0].name)
        assertEquals(49, app.items[0].quality)
        assertEquals(0, app.items[0].sellIn)
    }

    @Test fun agedBrieIncreases() {
        val items = arrayOf<Item>(Item("Aged Brie", 1, 50))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals("Aged Brie", app.items[0].name)
        assertEquals(50, app.items[0].quality)
        assertEquals(0, app.items[0].sellIn)
    }
}


