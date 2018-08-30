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
    override fun update(sellByDate: SellIn): Quality{
        if (sellByDate.passed()) {
            return DecreasingQuality(if(quality >= 2) (quality - 2) else 0)
        }

        return DecreasingQuality(if (quality >= 1) (quality - 1) else 0)
    }
}

data class ConjuredQuality(override val quality: Int): AbstractQuality(50) {
    override fun update(sellByDate: SellIn): Quality{
        if (sellByDate.passed()) {
            return DecreasingQuality(if(quality >= 4) (quality - 4) else 0)
        }

        return DecreasingQuality(if (quality >= 2) (quality - 2) else 0)
    }

}

data class IncreasingQuality(override val quality: Int): AbstractQuality(50) {
    override fun update(sellByDate: SellIn): Quality = IncreasingQuality(if (quality < maximum) (quality + 1) else maximum)
}

data class SpecialQuality(override val quality: Int): AbstractQuality(50) {
    override fun update(sellByDate: SellIn): Quality {
        if (sellByDate.passed()) {
            return SpecialQuality(0)
        }

        if (sellByDate.restAtMax(5)) {
            if ((quality * 3) > maximum)
                return SpecialQuality(maximum)

            return SpecialQuality(quality * 3)
        }

        if (sellByDate.restAtMax(10)) {
            if ((quality * 2) > maximum)
                return SpecialQuality(maximum)

            return SpecialQuality(quality * 2)
        }

        return SpecialQuality(if (quality >= 1) (quality - 1) else 0)
    }
}

class LegendaryQuality(private val quality: Int = 80): Quality {
    override fun toInt(): Int = quality
    override fun update(sellByDate: SellIn): Quality = this
}


class TooBigQualityException(max: Int): Exception("Quality cannot be upper than " + max.toString())
class TooLowQualityException(): Exception("Quality cannot be negative")