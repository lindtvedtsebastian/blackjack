package blackjack


/**
 * Deck
 *
 * @constructor Create empty Deck
 */
class Deck {
    var deck: MutableList<Card>;

    init {
        deck = Generate()
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
    fun Generate(): MutableList<Card> {
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
     * Card
     *
     * @constructor A new Card with a suit and value 
     *
     * @param suit - The suit of the card
     * @param value - The value of the card 
     */
    class Card(suit: Suit, value: Value) {
        val suit: Suit;
        val value: Value;

        init {
            this.suit = suit;
            this.value = value;
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
            Spades("S");
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
            J(10,"J"),
            Q(10,"Q"),
            K(10,"K"),
            A(11,"A"),
        }
    }
}
