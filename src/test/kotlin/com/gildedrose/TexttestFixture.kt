package com.gildedrose

fun main(args: Array<String>) {

    var resultInit = "OMGHAI!\n" +
    "-------- day 0 --------\n" +
    "name, sellIn, quality\n" +
    "Item(name=+5 Dexterity Vest, sellIn=10, quality=20)\n" +
    "Item(name=Aged Brie, sellIn=2, quality=0)\n" +
    "Item(name=Elixir of the Mongoose, sellIn=5, quality=7)\n" +
    "Item(name=Elixir of the Mongoose, sellIn=-1, quality=7)\n" +
    "Item(name=Sulfuras, Hand of Ragnaros, sellIn=0, quality=80)\n" +
    "Item(name=Sulfuras, Hand of Ragnaros, sellIn=-1, quality=80)\n" +
    "Item(name=Backstage passes to a TAFKAL80ETC concert, sellIn=15, quality=20)\n" +
    "Item(name=Backstage passes to a TAFKAL80ETC concert, sellIn=10, quality=49)\n" +
    "Item(name=Backstage passes to a TAFKAL80ETC concert, sellIn=5, quality=49)\n" +
    "Item(name=Conjured Mana Cake, sellIn=3, quality=6)\n" +
    "\n" +
    "-------- day 1 --------\n" +
    "name, sellIn, quality\n" +
    "Item(name=+5 Dexterity Vest, sellIn=9, quality=19)\n" +
    "Item(name=Aged Brie, sellIn=1, quality=1)\n" +
    "Item(name=Elixir of the Mongoose, sellIn=4, quality=6)\n" +
    "Item(name=Elixir of the Mongoose, sellIn=-2, quality=5)\n" +
    "Item(name=Sulfuras, Hand of Ragnaros, sellIn=0, quality=80)\n" +
    "Item(name=Sulfuras, Hand of Ragnaros, sellIn=-1, quality=80)\n" +
    "Item(name=Backstage passes to a TAFKAL80ETC concert, sellIn=14, quality=21)\n" +
    "Item(name=Backstage passes to a TAFKAL80ETC concert, sellIn=9, quality=50)\n" +
    "Item(name=Backstage passes to a TAFKAL80ETC concert, sellIn=4, quality=50)\n" +
    "Item(name=Conjured Mana Cake, sellIn=2, quality=5)\n\n"

    var res = "OMGHAI!\n"

    val items = arrayOf(Item("+5 Dexterity Vest", 10, 20), //
            Item("Aged Brie", 2, 0), //
            Item("Elixir of the Mongoose", 5, 7), //
            Item("Elixir of the Mongoose", -1, 7), //
            Item("Sulfuras, Hand of Ragnaros", 0, 80), //
            Item("Sulfuras, Hand of Ragnaros", -1, 80),
            Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            // this conjured item does not work properly yet
            Item("Conjured Mana Cake", 3, 6))

    val app = GildedRose(items)

    var days = 2
    if (args.size > 0) {
        days = Integer.parseInt(args[0]) + 1
    }

    for (i in 0..days - 1) {
        res += "-------- day $i --------\n"
        res += "name, sellIn, quality\n"
        for (item in items) {
            res += (item.toString()+"\n")
        }
        res += "\n"
        app.updateQuality()
    }

    println("INIT")
    println(resultInit)
    println("NOW")
    println(res)

    println("Your refactoring is " + if (res == resultInit) "good" else "bad")

}
