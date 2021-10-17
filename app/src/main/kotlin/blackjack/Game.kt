package blackjack

class Game {
	val deck : Deck 
	val sam : Player
	val dealer : Player 
	
	val STARTING_CARDS = 2
	val BLACKJACK = 21

	constructor(filepath: String? = null) {
		deck = Deck(filepath)
		
		sam = Sam()
		dealer = Dealer(sam)
		Play()
	}

	fun Play() {
		for (i in 0..STARTING_CARDS-1) {
			sam.DrawCard(deck)
			dealer.DrawCard(deck)
		}
		
		var winner = CheckForBlackjackOrBust()
		if (winner == null) {
            sam.Play(deck)
			dealer.Play(deck)
			winner = DetermineWinner()
		}
		println(winner.Name())
		sam.PrintHand()
		dealer.PrintHand()
	}

	fun DetermineWinner() : Player {
		val samTotal = sam.hand.CalculateTotal()
		val dealerTotal = dealer.hand.CalculateTotal()

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
