package blackjack

abstract class Player(deck : Deck) {
	val deck : Deck
	val hand : Hand 

	init {
		this.deck = deck
		this.hand = Hand()
	}

	abstract fun Play()
	abstract fun Name() : String 

	fun DrawCard() {
		hand.AddToHand(this.deck.DrawCard())
	}

	inner class Hand {
		val hand: MutableList<Deck.Card>

		init {
			hand = mutableListOf<Deck.Card>()
		}

		fun AddToHand(card : Deck.Card) {
			hand.add(card)
		}

		fun CalculateTotal() : Int {
			return hand.sumOf { it.value.value } 
		}
	}
}

class Sam(deck : Deck) : Player(deck) {
	override fun Play() {
		while (hand.CalculateTotal() < 17) {
			DrawCard()
		}
	}
	override fun Name() : String {
		return "sam"
	}
}

class Dealer(deck : Deck, sam : Player) : Player(deck) {
	val player: Player

	init {
		player = sam
	}
	
	override fun Play() {
		while (hand.CalculateTotal() < player.hand.CalculateTotal()) {
			DrawCard()
		}
	}
	override fun Name() : String {
		return "dealer"
	}
}
