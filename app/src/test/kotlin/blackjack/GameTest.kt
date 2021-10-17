package blackjack 

import kotlin.test.Test
import kotlin.test.assertIs

class GameTest {
	
	@Test fun play() {
		val testGame = Game("src/test/resources/deck.txt")

		assert(testGame.sam.hand.calculateTotal() == 20 )
		assert(testGame.dealer.hand.calculateTotal() == 30 )
	}
	
	@Test fun checkForBlackjackOrBust() {
		val testGame = Game("src/test/resources/deck.txt")

		assert(testGame.checkForBlackjackOrBust() == null)
	}

	@Test fun determineWinner() {
		val testGame = Game("src/test/resources/deck.txt")

		assertIs<Sam>(testGame.determineWinner())
	}

}
