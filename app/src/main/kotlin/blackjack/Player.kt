package blackjack

/**
 * Player - The base class for a player
 *
 * @constructor Create new Player
 */
abstract class Player(name : String) {
	val hand : Hand
	val name : String

	init {
		this.hand = Hand()
        this.name = name
	}

	abstract fun play(deck: Deck)

    /**
     * Prints the name and hand of the player
     */
	fun printHand() {
		println("$name: ${hand.printableFormat()}")
	}

    /**
     * Draws a card from the deck provided
     *
     * @param deck - The deck to draw the card from
     */
	fun drawCard(deck: Deck) {
		hand.addToHand(deck.DrawCard())
	}

    /**
     * Hand - A collection of cards held by a player
     *
     * @constructor Create new Hand
     */
	inner class Hand {
		private val hand: MutableList<Deck.Card> = mutableListOf<Deck.Card>()

        /**
         * Add to hand
         *
         * @param card - The card to be added to the hand
         */
        fun addToHand(card : Deck.Card) {
			hand.add(card)
		}

        /**
         * Calculates the total value of all the cards in the hand
         *
         * @return - The total value of the hand
         */
		fun calculateTotal() : Int {
			return hand.sumOf { it.value.value } 
		}

        /**
         * Converts the hand to a printable string
         *
         * @return
         */
		fun printableFormat() : String  {
			val printableHand = mutableListOf<String>()
			for (card in hand) {
				printableHand.add(card.PrintableFormat())
			}
			return printableHand.joinToString(", ")
		}
	}
}

/**
 * Sam - A player
 *
 * @param name - The name of the player
 */
class Sam(name : String = "sam") : Player(name) {
    /**
     * Play - The logic of this player
     *
     * @param deck - The deck to draw cards from
     */
	override fun play(deck: Deck) {
		while (hand.calculateTotal() < 17) {
			drawCard(deck)
		}
	}
}

/**
 * Dealer - A player
 *
 * @param sam - The "sam" this player is playing against
 * @param name - The name of the player
 */
class Dealer(sam : Player,name: String = "dealer") : Player(name) {
	private val player: Player = sam

    /**
     * Play - The logic of this player
     *
     * @param deck - The deck to draw cards from
     */
    override fun play(deck: Deck) {
		val playerTotal = player.hand.calculateTotal()
		while (hand.calculateTotal() <= playerTotal && playerTotal <= 21) {
			drawCard(deck)
		}
	}
}
