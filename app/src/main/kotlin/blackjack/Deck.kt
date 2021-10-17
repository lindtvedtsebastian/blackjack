package blackjack

class Deck {
    var deck: MutableList<Card>;

    init {
        deck = Generate()
    }

    fun Print() {
        for (card in deck) {
            print("${card.PrintableFormat()} ")
        }
    }
    
    fun Generate(): MutableList<Card> {
        var deck = mutableListOf<Card>()
        for (suit in enumValues<Card.Suit>()) {
            for (value in enumValues<Card.Value>()) {
                deck.add(Card(suit, value))
            }
        }
        return deck
    }

    fun Shuffle() {
        deck.shuffle()
    }

    class Card(suit: Suit, value: Value) {
        val suit: Suit;
        val value: Value;
        
        init {
            this.suit = suit;
            this.value = value;
        }

        fun PrintableFormat(): String {
            return "${suit.printableFormat}${value.printableFormat}"
        }

        enum class Suit(val printableFormat: String) {
            Clubs("C"),
            Diamonds("D"),
            Hearts("H"),
            Spades("S");
        }

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
