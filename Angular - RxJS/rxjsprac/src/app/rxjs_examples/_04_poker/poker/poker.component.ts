import { AfterViewInit, Component, ElementRef, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Card, Deck, GameState } from '../types';
import { concatMap, from, fromEvent, interval, map, Observable, of, scan, take, tap } from 'rxjs';

@Component({
  selector: 'app-poker',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './poker.component.html',
})
export class PokerComponent implements AfterViewInit {
  gameState: GameState;
  cards$!: Observable<Card[]>;

  @ViewChild('clickStart') clickStartBtn!: ElementRef;

  constructor() {
    this.gameState = {
      type: 'undistributed',
      matchHistory: [],
      roundNo: 0,
    }
  }
  
  ngAfterViewInit(): void {
    this.cards$ = fromEvent(this.clickStartBtn.nativeElement, 'click').pipe(
      concatMap(() => {
        const shuffleDeck = new Deck().shuffle();
        return interval(50).pipe(
          take(shuffleDeck.length),
          map(i => (shuffleDeck[i])),
          scan((acc: Card[], curr) => [...acc, curr], [])
        )
      }),
    )
  }
}
