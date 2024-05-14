import { AfterViewInit, Component, ElementRef, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Card, Deck, GameState, INITIAL_GAME_STATE, Player, UNSET_CARDS } from '../types';
import { concatMap, from, fromEvent, interval, map, Observable, of, scan, switchMap, take, tap } from 'rxjs';
import { PokerService } from '../poker.service';

@Component({
  selector: 'app-poker',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './poker.component.html',
})
export class PokerComponent implements AfterViewInit {
  gameState$ = this._pokerService.gameState$;

  @ViewChild('clickStart') clickStartBtn!: ElementRef;

  constructor(private _pokerService: PokerService) {}
  
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
      this._pokerService.handle({
        type: 'DISTRIBUTED', 
        card: val.card, 
        player: val.player,
      })
    })
  }
}
