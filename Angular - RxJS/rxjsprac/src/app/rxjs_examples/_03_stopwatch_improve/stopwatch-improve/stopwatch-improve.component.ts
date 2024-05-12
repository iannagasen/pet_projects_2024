import { AfterViewInit, Component, ElementRef, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { combineLatest, distinctUntilChanged, fromEvent, map, merge, NEVER, Observable, pipe, scan, shareReplay, startWith, Subject, switchMap, tap, timer, UnaryFunction } from 'rxjs';
import { ActionNames, CountDownState, CounterStateKeys, PartialCountDownState } from './types';

@Component({
  selector: 'app-stopwatch-improve',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './stopwatch-improve.component.html',
  styleUrls: ['./stopwatch-improve.component.sass']
})
export class StopwatchImproveComponent implements AfterViewInit {
  /**
   * CQRS - separate the commands to queries(state)
   */
  counterValue: number = 0;

  counterCommands$!:  Observable<PartialCountDownState>;
  programmaticCommandSubject = new Subject<PartialCountDownState>();
  counterState$!: Observable<CountDownState>;
  
  count$!:     Observable<number>;
  isTicking$!: Observable<boolean>;
  tickSpeed$!: Observable<number>;
  countDiff$!: Observable<number>;
  counterUpdateTrigger$!: Observable<number>;
  
  @ViewChild('startBtn') startBtn!: ElementRef;
  @ViewChild('pauseBtn') pauseBtn!: ElementRef;
  @ViewChild('setToBtn') setToBtn!: ElementRef;
  @ViewChild('resetBtn') resetBtn!: ElementRef;
  @ViewChild('upBtn')       upBtn!: ElementRef;
  @ViewChild('downBtn')   downBtn!: ElementRef;
  
  ngAfterViewInit(): void {
    const startCounter$ = this.getCommandObservable(this.startBtn, 'click', ActionNames.Start);
    const pauseCounter$ = this.getCommandObservable(this.pauseBtn, 'click', ActionNames.Pause);
    const setToCounter$ = this.getCommandObservable(this.setToBtn, 'click', ActionNames.SetTo);
    const resetCounter$ = this.getCommandObservable(this.resetBtn, 'click', ActionNames.Reset);
    const upCounter$    = this.getCommandObservable(this.upBtn, 'click', ActionNames.Up);
    const downCounter$  = this.getCommandObservable(this.downBtn, 'click', ActionNames.Down);

    this.counterCommands$ = 
      merge( 
        startCounter$.pipe(map((_) => ({ isTicking: true }))),
        pauseCounter$.pipe(map((_) => ({ isTicking: false }))),
        setToCounter$.pipe(map((n) => ({ count: n }))),
        this.programmaticCommandSubject.asObservable()
      );

    this.counterState$ = (this.counterCommands$ as Observable<CountDownState>)
      .pipe(
        startWith({
          isTicking: false, 
          count: 0,
          tickSpeed: 200, 
          countUp: true, 
          countDiff:1
        }),
        scan( (counterState: CountDownState, command): CountDownState => ( {...counterState, ...command} ) ),
        shareReplay(1)
      );

    // intermediate observables
    this.count$ = this.counterState$.pipe(map((val => val.count)));
    this.isTicking$ = this.counterState$.pipe(this.queryChange<CountDownState, boolean>(CounterStateKeys.isTicking));
    this.tickSpeed$ = this.counterState$.pipe(this.queryChange<CountDownState, number>(CounterStateKeys.tickSpeed));
    this.countDiff$ = this.counterState$.pipe(this.queryChange<CountDownState, number>(CounterStateKeys.countDiff));
    this.counterUpdateTrigger$ =
      combineLatest([this.isTicking$, this.tickSpeed$])
      .pipe(
        switchMap(([isTicking, tickSpeed]) => isTicking ? timer(0, tickSpeed) : NEVER)
      )
  }

  getCommandObservable(element: ElementRef, event: string, command: ActionNames): Observable<ActionNames> {
    return fromEvent(element.nativeElement, event).pipe(map((_  ) => command)); 
  }

  queryChange<T, I>(key: string): UnaryFunction<Observable<T>, Observable<I>> {
    return pipe(
      map((val: T) => (val as any)[key]),
      distinctUntilChanged<I>()
    );
  }
  
}
