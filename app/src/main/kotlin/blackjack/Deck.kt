package blackjack

import java.io.File

/**
 * Deck
 *
 * @constructor Create empty Deck
 */
class Deck {
    var deck: MutableList<Card>;

	constructor(filepath: String? = null) {
		if (filepath != null) {
			val deckFile = File(filepath).readText().replace("\\s".toRegex(),"").split(",")
			
			deck = mutableListOf<Card>()
			for (card in deckFile) {
				deck.add(Card(parseSuit(card.substring(0,1)),parseValue(card.substring(1))))
			}
		} else {
			deck = Generate()
			Shuffle()
		}
	}

    /**
     * Parse suit
     *
     * @param suitAsString - The suit in string form
     * @return - The parsed suit
     */
    fun parseSuit(suitAsString: String): Card.Suit {
        return when (suitAsString) {
            "C" -> Card.Suit.Clubs
            "D" -> Card.Suit.Diamonds
            "H" -> Card.Suit.Hearts
            else -> Card.Suit.Spades
        }
    }

    /**
     * Parse value
     *
     * @param valueAsString - The value in string form
     * @return - The parsed value
     */
    fun parseValue(valueAsString: String) : Card.Value {
        return when (valueAsString) {
            "2"  -> Card.Value.Two
            "3"  -> Card.Value.Three
            "4"  -> Card.Value.Four
            "5"  -> Card.Value.Five
            "6"  -> Card.Value.Six
            "7"  -> Card.Value.Seven
            "8"  -> Card.Value.Eight
            "9"  -> Card.Value.Nine
            "10" -> Card.Value.Ten
            "J"  -> Card.Value.Jack
            "Q"  -> Card.Value.Queen
            "K"  -> Card.Value.King
            else -> Card.Value.Ace
        }
    }

    /**
     * Prints the deck 
     */
    fun Print() {
        for (card in deck) {
            print("${card.PrintableFormat()} ")
        }
    }


    /**
     * Generates a new sorted Deck 
     * @return - The generated deck 
     */
    fun Generate() : MutableList<Card> {
        var deck = mutableListOf<Card>()
        for (suit in enumValues<Card.Suit>()) {
            for (value in enumValues<Card.Value>()) {
                deck.add(Card(suit, value))
            }
        }
        return deck
    }

    /**
     * Shuffles the currently stored deck 
     */
    fun Shuffle() {
        deck.shuffle()
    }

	/** 
	 * Draws the top card from the deck
     * @return - The card that was drawn  
	 */
	fun DrawCard() : Card {
		var topCard = deck.first()
		deck.removeAt(0)
		return topCard
	}

    /**
     * Card
     *
     * @constructor A new Card with a suit and value 
     *
     * @param suit - The suit of the card
     * @param value - The value of the card 
     */
    class Card(suit: Suit, value: Value) {
        val suit: Suit
        val value: Value

        init {
            this.suit = suit
            this.value = value
        }



        /**
         * Printable format
         *
         * @return The card object in a printable format 
         */
        fun PrintableFormat(): String {
            return "${suit.printableFormat}${value.printableFormat}"
        }

        /**
         * Suit
         *
         * @property printableFormat How the Suit should be formatted when printed 
         * @constructor Create new Suit
         */
        enum class Suit(val printableFormat: String) {
            Clubs("C"),
            Diamonds("D"),
            Hearts("H"),
            Spades("S"),
        }

        /**
         * Value
         *
         * @property value - The value of the card in Int form 
         * @property printableFormat - How the value should be formatted when printed 
         * @constructor Create new Value
         */
        enum class Value(val value: Int, val printableFormat: String) {
            Two(2,"2"),
            Three(3,"3"),
            Four(4,"4"),
            Five(5,"5"),
            Six(6,"6"),
            Seven(7,"7"),
            Eight(8,"8"),
            Nine(9,"9"),
            Ten(10,"10"),
            Jack(10,"J"),
            Queen(10,"Q"),
            King(10,"K"),
            Ace(11,"A"),
        }
    }
}
