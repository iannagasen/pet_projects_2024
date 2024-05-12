import { AfterViewInit, Component, ElementRef, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Card, Deck, GameState, Player, submitEventReducer, UNSET_CARDS } from '../types';
import { concatMap, from, fromEvent, interval, map, Observable, of, scan, take, tap } from 'rxjs';

@Component({
  selector: 'app-poker',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './poker.component.html',
})
export class PokerComponent implements AfterViewInit {
  gameState: GameState;

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

    console.log(this.gameState)
  }
  
  ngAfterViewInit(): void {
    const getPlayer = (i: number): Player => ({
      name: (i%4===0) ? 'Player001' :
            (i%4===1) ? 'Player002' :  
            (i%4===2) ? 'Player003'
                      : 'Player004'
    });

    fromEvent(this.clickStartBtn.nativeElement, 'click').pipe(
      concatMap(() => {
        const shuffleDeck = new Deck().shuffle();
        return interval(50).pipe(
          take(shuffleDeck.length),
          // tap(i => {
          //   console.log("index -- " + i);
          //   console.log(getPlayer(i))
          // }),
          map(i => ({ 
            player: getPlayer(i),
            card: (shuffleDeck[i]) 
          })),
        )
      }),
    ).subscribe(val => {
      this.gameState = submitEventReducer({
        type: 'DISTRIBUTED', 
        card: val.card, 
        player: val.player
      }, this.gameState)
    })
  }
}
