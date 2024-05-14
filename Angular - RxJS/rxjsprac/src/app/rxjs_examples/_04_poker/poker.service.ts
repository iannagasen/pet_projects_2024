import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Card, CardDistributedEvent, CardSubmittedEvent, GameState, INITIAL_GAME_STATE, PlayerCards, Tuple, UNSET_CARDS } from './types';

@Injectable({
  providedIn: 'root'
})
export class PokerService {
  
  private _gameStateSubject = new BehaviorSubject<GameState>(INITIAL_GAME_STATE);
  gameState$ = this._gameStateSubject.asObservable();
  
  private _isInitial: boolean = true;
  private _noOfCardsDistributed = 0;

  constructor() {}

  handle(event: CardDistributedEvent | CardSubmittedEvent) {
    this._isInitial = false;
    switch(event.type) {
      case 'DISTRIBUTED': return this.distributeCards(event);
      case 'SUBMITTED': return this.submitCards(event);
    }
  }

  private distributeCards(event: CardDistributedEvent) {
    const { player, card } = event;
    const currentState = this._gameStateSubject.getValue();
    let currentPlayerCards = currentState.currentPlayerCards;
    if (this._noOfCardsDistributed === 52) {
      // RESET
      currentPlayerCards = currentState.currentPlayerCards.map(pc => ({ ...pc, cards: UNSET_CARDS})) as Tuple<PlayerCards, 4>;
      this._noOfCardsDistributed = 0;
    }

    const index = currentPlayerCards.findIndex(pc => pc.player.name === player.name)
    const targetPlayerCards = currentPlayerCards[index].cards
    const firstUnsetIndex = targetPlayerCards.findIndex(card => card === 'UNSET');

    const newTargetPlayerCards = {
      player: player,
      cards: targetPlayerCards.slice(0, firstUnsetIndex).concat(card).concat(
        targetPlayerCards.slice(firstUnsetIndex+1)) as Tuple<Card, 13>
    }

    const newPlayerCards = 
        currentPlayerCards.slice(0, index).concat(newTargetPlayerCards).concat(
          currentPlayerCards.slice(index+1))

    const newState: GameState = {
      type: 'distributed',
      roundNo: currentState.roundNo++, // is this the right place to increment this?
      currentPlayerCards: newPlayerCards as Tuple<PlayerCards, 4>,
      matchHistory: currentState.matchHistory
    }

    this._noOfCardsDistributed++;

    this._gameStateSubject.next(newState);
  }

  private submitCards(event: CardSubmittedEvent) {
    const currentState = this._gameStateSubject.getValue()
    return {
      ...currentState,
      type: 'submitted',
      matchHistory: [ ...currentState.matchHistory, event.submittedCardsPerPlayer]
    }
  }
}
