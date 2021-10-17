package blackjack 

import kotlin.test.Test

class PlayerTest {
	
	@Test fun drawCard() {
		val testSam = Sam()
		val testDeck = Deck("src/test/resources/deck.txt")

		assert(testSam.hand.calculateTotal() == 0)
		
		testSam.drawCard(testDeck)
		assert(testSam.hand.calculateTotal() == 10)
	}

	@Test fun printHand() {
		val testSam = Sam()
		val testDeck = Deck("src/test/resources/deck.txt")

		testSam.drawCard(testDeck)
		assert(testSam.hand.printableFormat() == "D10")

		testSam.drawCard(testDeck)
		assert(testSam.hand.printableFormat() == "D10, S2")
	}
}
