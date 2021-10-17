package blackjack

/**
 * Game
 *
 * @constructor Create new Game
 */
class Game {
	val deck : Deck 
	val sam : Player
	val dealer : Player 
	
	val STARTING_CARDS = 2
	val BLACKJACK = 21

    /**
     * Constructs a new game, if a filepath is provided a deck is loaded from file
     */
	constructor(filepath: String? = null) {
		deck = Deck(filepath)
		
		sam = Sam()
		dealer = Dealer(sam)
		Play()
	}

    /**
     * Play - Runs the game
     *
     */
	fun Play() {
		for (i in 0..STARTING_CARDS-1) {
			sam.drawCard(deck)
			dealer.drawCard(deck)
		}
		
		var winner = CheckForBlackjackOrBust()
		if (winner == null) {
            sam.play(deck)
			dealer.play(deck)
			winner = DetermineWinner()
		}
		println(winner.name)
		sam.printHand()
		dealer.printHand()
	}

    /**
     * Determines which player has won
     *
     * @return The player that won
     */
	fun DetermineWinner() : Player {
		val samTotal = sam.hand.calculateTotal()
		val dealerTotal = dealer.hand.calculateTotal()

		val maybeWinner = CheckForBlackjackOrBust()
		if (maybeWinner != null) return maybeWinner

		if (dealerTotal > BLACKJACK && samTotal <= BLACKJACK) {
			return sam
		}
		
		if (samTotal > dealerTotal && samTotal <= BLACKJACK) {
			return sam
		} else {
			return dealer
		}
	}

    /**
     * Check for blackjack or bust
     *
     * If the player and the dealer has blackjack, the player wins
     * If the player has busted (Over 21), the dealer wins
     *
     * @return The player that has won, or null
     */
	fun CheckForBlackjackOrBust() : Player? {
		val samTotal = sam.hand.calculateTotal()
		val dealerTotal = dealer.hand.calculateTotal()
		
		if (samTotal == BLACKJACK) {
			return sam
		} else if (dealerTotal == BLACKJACK || samTotal > BLACKJACK) {
			return dealer
		} else return null 
	}
}
