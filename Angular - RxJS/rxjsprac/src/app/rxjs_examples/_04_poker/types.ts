export type Face = '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' | '10' | 'J' | 'Q' | 'K'
export type Suit = 'Hearts' | 'Diamonds' | 'Spades' | 'Clubs' 

export type Card = {
  face: Face
  suit: Suit
} | 'UNSET'

export type Player = {
  name: string
}

export type PlayerCards = {
  player: Player,
  cards: Tuple<Card, 13>
}

export type PlayerSubmittedCards = {
  player: Player,
  submittedCard: {
    header: [Card, Card, Card],
    body: [Card, Card, Card, Card, Card]
    footer: [Card, Card, Card, Card, Card]
  }
}

export type CardDistributedEvent = { 
  type: 'DISTRIBUTED', 
  player: Player,
  card: Card,
}

export type CardSubmittedEvent = {
  type: 'SUBMITTED',
  submittedCardsPerPlayer: [PlayerSubmittedCards, PlayerSubmittedCards, PlayerSubmittedCards, PlayerSubmittedCards],
}

export type GameState = {
  type: 'undistributed' | 'distributing' | 'distributed' | 'playing' | 'submitted',
  roundNo: number,
  currentPlayerCards: Tuple<PlayerCards, 4>
  matchHistory: [PlayerSubmittedCards, PlayerSubmittedCards, PlayerSubmittedCards, PlayerSubmittedCards][]
};





export const UNSET_CARDS: Tuple<Card, 13> = ['UNSET', 'UNSET', 'UNSET', 'UNSET', 'UNSET', 'UNSET', 'UNSET', 'UNSET', 'UNSET', 'UNSET', 'UNSET', 'UNSET', 'UNSET']

export const CARD_VALUE_ARR: Tuple<Card, 52> = [
  { suit: "Hearts", face: '1'},
  { suit: "Hearts", face: '2'},
  { suit: "Hearts", face: '3'},
  { suit: "Hearts", face: '4'},
  { suit: "Hearts", face: '5'},
  { suit: "Hearts", face: '6'},
  { suit: "Hearts", face: '7'},
  { suit: "Hearts", face: '8'},
  { suit: "Hearts", face: '9'},
  { suit: "Hearts", face: '10'},
  { suit: "Hearts", face: 'J'},
  { suit: "Hearts", face: 'Q'},
  { suit: "Hearts", face: 'K'},

  { suit: "Clubs", face: '1'},
  { suit: "Clubs", face: '2'},
  { suit: "Clubs", face: '3'},
  { suit: "Clubs", face: '4'},
  { suit: "Clubs", face: '5'},
  { suit: "Clubs", face: '6'},
  { suit: "Clubs", face: '7'},
  { suit: "Clubs", face: '8'},
  { suit: "Clubs", face: '9'},
  { suit: "Clubs", face: '10'},
  { suit: "Clubs", face: 'J'},
  { suit: "Clubs", face: 'Q'},
  { suit: "Clubs", face: 'K'},

  { suit: "Diamonds", face: '1'},
  { suit: "Diamonds", face: '2'},
  { suit: "Diamonds", face: '3'},
  { suit: "Diamonds", face: '4'},
  { suit: "Diamonds", face: '5'},
  { suit: "Diamonds", face: '6'},
  { suit: "Diamonds", face: '7'},
  { suit: "Diamonds", face: '8'},
  { suit: "Diamonds", face: '9'},
  { suit: "Diamonds", face: '10'},
  { suit: "Diamonds", face: 'J'},
  { suit: "Diamonds", face: 'Q'},
  { suit: "Diamonds", face: 'K'},

  { suit: "Spades", face: '1'},
  { suit: "Spades", face: '2'},
  { suit: "Spades", face: '3'},
  { suit: "Spades", face: '4'},
  { suit: "Spades", face: '5'},
  { suit: "Spades", face: '6'},
  { suit: "Spades", face: '7'},
  { suit: "Spades", face: '8'},
  { suit: "Spades", face: '9'},
  { suit: "Spades", face: '10'},
  { suit: "Spades", face: 'J'},
  { suit: "Spades", face: 'Q'},
  { suit: "Spades", face: 'K'},
]

export const INITIAL_GAME_STATE: GameState = {
  type: 'undistributed',
  matchHistory: [],
  roundNo: 0,
  currentPlayerCards: [
    { player: {name: 'Player001'}, cards: UNSET_CARDS },
    { player: {name: 'Player002'}, cards: UNSET_CARDS },
    { player: {name: 'Player003'}, cards: UNSET_CARDS },
    { player: {name: 'Player004'}, cards: UNSET_CARDS },
  ]
}

export class Deck {
  cards: Tuple<Card, 52>;

  constructor() {
    this.cards = [...CARD_VALUE_ARR];
    this.shuffle();
  }

  shuffle(): Tuple<Card, 52> {
    let shuffledCards: Tuple<Card, 52> = [...this.cards]; // Copying the original cards array
    
    let currentShuffle = 0;
    while (currentShuffle < 52) {
      const randomNumber = Math.floor(Math.random() * 52);
      
      // Swap current card with a randomly chosen card
      const temp = shuffledCards[currentShuffle];
      shuffledCards[currentShuffle] = shuffledCards[randomNumber];
      shuffledCards[randomNumber] = temp;
      
      currentShuffle++;
    }

    this.cards = shuffledCards;
    return shuffledCards;
  }

}

export type Tuple<
  T,
  N extends number,
  R extends T[] = [],
> = R['length'] extends N ? R : Tuple<T, N, [T, ...R]>;