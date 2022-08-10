package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun `normal behaviour`() {

        val items = arrayOf(
            Item("foo", 1, 10),
            Item("foo", 2, 20)
        )

        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(9, items[0].quality)
        assertEquals(0, items[0].sellIn)
        assertEquals(19, items[1].quality)
        assertEquals(1, items[1].sellIn)
    }

    @Test
    fun `Once the sell by date has passed, Quality degrades twice as fast`() {

        val items = arrayOf(Item("foo", 1, 20), Item("foo", 0, 20))

        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(19, items[0].quality)
        assertEquals(18, items[1].quality)
    }

    @Test
    fun `The Quality of an item is never negative`() {
        val items = arrayOf(Item("foo", 1, 0))

        val app = GildedRose(items)
        app.updateQuality()

        assertEquals(0, items[0].quality)
    }

    @Test
    fun `Aged Brie actually increases in Quality the older it gets`() {
        val items = arrayOf(Item("Aged Brie", 1, 0))

        val app = GildedRose(items)
        app.updateQuality()

        assertEquals(1, items[0].quality)
    }

    @Test
    fun `The Quality of an item is never more than 50`() {
        val items = arrayOf(Item("Aged Brie", 1, 50))

        val app = GildedRose(items)
        app.updateQuality()

        assertEquals(50, items[0].quality)
    }

    @Test
    fun `Sulfuras, being a legendary item, never has to be sold or decreases in Quality`() {
        val items = arrayOf(Item("Sulfuras, Hand of Ragnaros", 0, 80))

        val app = GildedRose(items)
        app.updateQuality()

        assertEquals(80, items[0].quality)
    }

    @Test
    fun `Backstage passes, like aged brie, increases in Quality as its SellIn value approaches`() {
        val items = arrayOf(
            Item("Backstage passes to a TAFKAL80ETC concert", 25, 10),
            Item("Backstage passes to a TAFKAL80ETC concert", 10, 10),
            Item("Backstage passes to a TAFKAL80ETC concert", 5, 10),
            Item("Backstage passes to a TAFKAL80ETC concert", 0, 10),
        )

        val app = GildedRose(items)
        app.updateQuality()

        assertEquals(11, items[0].quality)
        assertEquals(12, items[1].quality)
        assertEquals(13, items[2].quality)
        assertEquals(0, items[3].quality)
    }

    @Test
    fun `Conjured items degrade in Quality twice as fast as normal items`() {
        val items = arrayOf(
            Item("normal item", 0, 10),
            Item("Conjured mana cake", 0, 10),
            Item("normal item", 1, 10),
            Item("Conjured mana cake", 1, 10),
        )

        val app = GildedRose(items)
        app.updateQuality()

        assertEquals(8, items[0].quality)
        assertEquals(6, items[1].quality)
        assertEquals(9, items[2].quality)
        assertEquals(8, items[3].quality)
    }

}
