package blackjack

abstract class Player() {
	val hand : Hand 

	init {
		this.hand = Hand()
	}

	abstract fun Play(deck: Deck)
	abstract fun Name() : String
	
	fun PrintHand() {
		println("${Name()}: ${hand.PrintableFormat()}")
	}

	fun DrawCard(deck: Deck) {
		hand.AddToHand(deck.DrawCard())
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

		fun PrintableFormat() : String  {
			val PrintableHand = mutableListOf<String>()
			for (card in hand) {
				PrintableHand.add(card.PrintableFormat())
			}
			return PrintableHand.joinToString(", ")
		}
	}
}

class Sam() : Player() {
	override fun Play(deck: Deck) {
		while (hand.CalculateTotal() < 17) {
			DrawCard(deck)
		}
	}
	override fun Name() : String {
		return "sam"
	}
}

class Dealer(sam : Player) : Player() {
	val player: Player

	init {
		player = sam
	}
	
	override fun Play(deck: Deck) {
		val playerTotal = player.hand.CalculateTotal()
		while (hand.CalculateTotal() <= playerTotal && playerTotal <= 21) {
			DrawCard(deck)
		}
	}
	override fun Name() : String {
		return "dealer"
	}
}
