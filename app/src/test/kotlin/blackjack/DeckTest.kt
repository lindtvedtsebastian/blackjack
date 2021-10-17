package blackjack

import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNotEquals
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.assert

class DeckTest {
	@Test fun generateDeck() {
		val testDeck = Deck()
		assert(testDeck.deck.size == 52)
	}

	@Test fun drawCard() {
		val testDeck = Deck()
		assert(testDeck.deck.size == 52)
		val firstCard = testDeck.deck[0]
		val card = testDeck.DrawCard()
	    assert(testDeck.deck.size == 51)
		assert(card == firstCard)
	}
	
	@Test fun shuffleDeck() {
		val testDeck = Deck()
		val prevDeck = testDeck.deck.toList()
		assertContentEquals(testDeck.deck,prevDeck)
		testDeck.Shuffle()
		assert(testDeck.deck.toString() != prevDeck.toString())
	}
}
