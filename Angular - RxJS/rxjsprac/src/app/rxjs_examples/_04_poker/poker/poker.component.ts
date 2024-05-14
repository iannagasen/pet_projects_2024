import { AfterViewInit, Component, ElementRef, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Card, Deck, GameState, INITIAL_GAME_STATE, Player, PokerGameEventHandler, UNSET_CARDS } from '../types';
import { concatMap, from, fromEvent, interval, map, Observable, of, scan, switchMap, take, tap } from 'rxjs';

@Component({
  selector: 'app-poker',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './poker.component.html',
})
export class PokerComponent implements AfterViewInit {
  gameState!: GameState;
  gameEventHandler!: PokerGameEventHandler;

  @ViewChild('clickStart') clickStartBtn!: ElementRef;

  constructor() {
    this.gameState = {
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
    this.gameEventHandler = PokerGameEventHandler.withInitial(this.gameState)
  }
  
  ngAfterViewInit(): void {
    const getPlayer = (i: number): Player => ({
      name: (i%4===0) ? 'Player001' :
            (i%4===1) ? 'Player002' :  
            (i%4===2) ? 'Player003'
                      : 'Player004'
    });

    fromEvent(this.clickStartBtn.nativeElement, 'click').pipe(
      switchMap(() => {
        const shuffleDeck = new Deck().shuffle();
        return interval(50).pipe(
          take(shuffleDeck.length),
          map(i => ({ 
            player: getPlayer(i),
            card: (shuffleDeck[i]),
          })),
        )
      }),
    ).subscribe(val => {
      this.gameState = this.gameEventHandler.handle({
        type: 'DISTRIBUTED', 
        card: val.card, 
        player: val.player,
      })
    })
  }
}
