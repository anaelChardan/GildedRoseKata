package com.gildedrose

interface Quality {
    fun update(sellByDate: SellIn): Quality
    fun toInt(): Int
}

sealed class AbstractQuality constructor(max: Int): Quality {
    abstract val quality: Int
    var maximum: Int

    init {
        if (quality < 0) {
            throw TooLowQualityException()
        }
        if (quality > max) {
            throw TooBigQualityException(max)
        }

        this.maximum = max
    }

    override fun toInt(): Int = quality
}

data class DecreasingQuality(override val quality: Int): AbstractQuality(50) {
    override fun update(sellByDate: SellIn): Quality = DecreasingQuality(if (quality >= 1) (quality - 1) else 0)
}

data class IncreasingQuality(override val quality: Int): AbstractQuality(50) {
    override fun update(sellByDate: SellIn): Quality = IncreasingQuality(if (quality < maximum) (quality + 1) else maximum)
}

data class BackstageQuality(override val quality: Int): AbstractQuality(50) {
    override fun update(sellByDate: SellIn): Quality {
        if (sellByDate.passed()) {
            return BackstageQuality(0)
        }

        if (sellByDate.restAtMax(5)) {
            if ((quality * 3) > maximum)
                return BackstageQuality(maximum)

            return BackstageQuality(quality * 3)
        }

        if (sellByDate.restAtMax(10)) {
            if ((quality * 2) > maximum)
                return BackstageQuality(maximum)

            return BackstageQuality(quality * 2)
        }

        return BackstageQuality(if (quality >= 1) (quality - 1) else 0)
    }
}

data class LegendaryQuality(override val quality: Int): AbstractQuality(80) {
    override fun update(sellByDate: SellIn): Quality = LegendaryQuality(if (quality < maximum) (quality + 1) else maximum)
}


class TooBigQualityException(val max: Int): Exception("Quality cannot be upper than " + max.toString())
class TooLowQualityException(): Exception("Quality cannot be negative")