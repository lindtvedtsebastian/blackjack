package blackjack

class Deck {
    var deck: MutableList<Card>;

    init {
        deck = GenerateDeck()
    }

    
    fun GenerateDeck: MutableList<Card> {
        var deck = mutableListOf<Card>()
        for (suit in enumValues<Card.Suit>()) {
            for (value in enumValues<Card.Value>()) {
                deck.add(Card(suit, value))
            }
        }
        return deck
    }

    class Card(suit: Suit, value: Value) {
        val suit: Suit;
        val value: Value;

        init {
            this.suit = suit;
            this.value = value;
        }

        public enum Suit {
            Clubs,
            Diamonds,
            Hearts,
            Spades
        }

        public enum Value {
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            10,
            J,
            Q,
            K,
            A
        }
    }
}
