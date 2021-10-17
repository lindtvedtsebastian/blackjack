package blackjack

class Game {
	val deck : Deck 
	val sam : Player
	val dealer : Player 
	
	val STARTING_CARDS = 2
	val BLACKJACK = 21

	init {
		deck = Deck()
		
		sam = Sam(deck)
		dealer = Dealer(deck, sam)
		Play()
	}

	fun Play() {
		for (i in 0..STARTING_CARDS-1) {
			sam.DrawCard()
			dealer.DrawCard()
		}
		
		var winner = CheckForBlackjackOrBust()
		if (winner == null) {
            sam.Play()
			dealer.Play()
			winner = DetermineWinner()
		}
		print(winner.Name())
	}

	fun DetermineWinner() : Player {
		val samTotal = sam.hand.CalculateTotal()
		val dealerTotal = dealer.hand.CalculateTotal()

		val maybeWinner = CheckForBlackjackOrBust()
		if (maybeWinner != null) return maybeWinner

		if (samTotal > BLACKJACK) {
			return dealer
		}
		if (samTotal > dealerTotal && samTotal <= BLACKJACK) {
			return sam
		} else {
			return dealer
		}
	}

	fun CheckForBlackjackOrBust() : Player? {
		val samTotal = sam.hand.CalculateTotal()
		val dealerTotal = dealer.hand.CalculateTotal()
		
		if (samTotal == BLACKJACK) {
			return sam
		} else if (dealerTotal == BLACKJACK || samTotal > BLACKJACK) {
			return dealer
		} else return null 
	}
}
