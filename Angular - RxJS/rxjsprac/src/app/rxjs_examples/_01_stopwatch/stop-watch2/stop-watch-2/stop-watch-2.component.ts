import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { from, fromEvent, map, merge, Observable, of, repeat, scan, Subject, takeUntil, tap, timer } from 'rxjs';

@Component({
  selector: 'app-stop-watch-2',
  templateUrl: './stop-watch-2.component.html',
})
export class StopWatch2Component implements AfterViewInit {

  stop$ = new Subject<boolean>;
  timer$ = new Observable<number>;
  currentTime = 0;

  @ViewChild('startBtn', {static: false}) startBtn!: ElementRef;
  // @ViewChild('resumeBtn', {static: false}) resumeBtn!: ElementRef;

  ngAfterViewInit(): void {
    merge(
      fromEvent(this.startBtn.nativeElement, 'click').pipe(map(() => 0)),
      // fromEvent(this.resumeBtn.nativeElement, 'click').pipe(map(() => this.currentTime))
    )
    .subscribe(time => {
      this.startTimer();
    });
  }

  startTimer() {
    this.timer$ = of(this.currentTime)
      .pipe(
        scan((acc, cur) => this.currentTime++, this.currentTime),
        map(val => val / 100),
        repeat({delay: 10}),
        takeUntil(this.stop$)
      )
  }

  stopTimer() {
    this.stop$.next(true);
  }

}
