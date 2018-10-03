package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun isAnIncreasingQuality(item: Item): Boolean {
        return item.name.equals("Aged Brie") || item.name.equals("Backstage passes to a TAFKAL80ETC concert")
    }

    fun treatClassicIncreasing(item: Item) {
        if (item.quality < 50) {
            item.quality += 1
        }

        item.sellIn-= 1

        if (item.sellIn < 0 && item.quality < 50) {
                item.quality += 1
        }
    }

    fun treatSpecialIncreasing(item: Item) {
        if (item.quality < 50) {
            if (item.sellIn <= 5) {
                item.quality += (50 - item.quality).coerceAtMost(3)

            } else if (item.sellIn <= 10) {
                item.quality += (50 - item.quality).coerceAtMost(2)

            } else {
                item.quality += 1
            }
        }

        item.sellIn -=1

        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }

    fun treatIncreasingQuality(item: Item): Unit {
        if (item.name.equals("Aged Brie")) {
            treatClassicIncreasing(item)

            return;
        }

        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            treatSpecialIncreasing(item)
        }
    }

    fun isALegendary(item: Item): Boolean {
        return item.name.equals("Sulfuras, Hand of Ragnaros")
    }

    fun treatDecreasingQuality(item: Item) {
        item.quality -= 1
        item.sellIn -= 1

        if (item.sellIn < 0) {
            item.quality -= 1
        }
    }





    fun updateQuality() {
//        this.items = items.map {
//            when (it.name) {
//                "Aged Brie" -> AgedBrie(it)
//                "Sulfuras, Hand of Ragnaros" -> Sulfuras(it)
//                "Backstage passes to a TAFKAL80ETC concert" -> BackStage(it)
//                else -> throw UnknownItemException()
//            }
//        }.map { it.updateQuality() }.map { it.toItem() }.toTypedArray()


        items.forEach( fun(item: Item) {
            if (isALegendary(item)) {
                return;
            }

            if (isAnIncreasingQuality(item)) {
                treatIncreasingQuality(item)

                return;
            }

            treatDecreasingQuality(item)
        })
    }

}

