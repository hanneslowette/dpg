package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (item in items) {

            if (item.name.lowercase().contains("backstage pass")) {
                if (item.sellIn == 0) {
                    item.quality = 0
                } else if (item.sellIn <= 5) {
                    adjustQuality(item, -DEFAULT_DEGRADATION_RATE * 3)
                } else if (item.sellIn <= 10) {
                    adjustQuality(item, -DEFAULT_DEGRADATION_RATE * 2)
                } else {
                    adjustQuality(item, -DEFAULT_DEGRADATION_RATE)
                }
            } else if (!item.name.lowercase().startsWith("sulfuras")) {
                var degradationRate = DEFAULT_DEGRADATION_RATE
                if (item.name.lowercase().contains("conjured")) {
                    degradationRate *= 2
                } else if (item.name.lowercase().contains("aged brie")) {
                    degradationRate *= -1
                }
                adjustQuality(item, if (item.sellIn <= 0) degradationRate * 2 else degradationRate)
            }

            if (!item.name.lowercase().startsWith("sulfuras")) {
                item.sellIn -= 1
            }
        }
    }

    private fun adjustQuality(item: Item, amount: Int) {
        item.quality = item.quality + amount
        if (item.quality > 50) {
            item.quality = 50
        } else if (item.quality < 0) {
            item.quality = 0
        }
    }

    companion object {
        const val DEFAULT_DEGRADATION_RATE = -1
    }

}

