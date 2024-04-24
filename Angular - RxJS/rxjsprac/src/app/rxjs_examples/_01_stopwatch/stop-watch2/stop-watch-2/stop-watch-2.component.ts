import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { fromEvent, map, Observable, of, repeat, scan, Subject, takeUntil, timer } from 'rxjs';

@Component({
  selector: 'app-stop-watch-2',
  templateUrl: './stop-watch-2.component.html',
})
export class StopWatch2Component implements AfterViewInit {
  
  stop$ = new Subject<boolean>();
  timer$ = new Observable<number>();
  currentTime = 0;

  @ViewChild('startBtn', {static: false}) startBtn!: ElementRef;
  
  ngAfterViewInit(): void {
    fromEvent(this.startBtn.nativeElement, 'click').pipe(
        map(() => 0)
      )
      .subscribe(startTime => {
        this.startTimer()
      })
  }

  startTimer() {
    this.timer$ = of(0).pipe(
      scan(
        (acc, cur) => (this.currentTime++ / 100),
        this.currentTime
      ),
      repeat({ delay: 10 }),
      takeUntil(this.stop$)
    )
  }

  stopTimer() {
    this.stop$.next(true);
  }
  
  
}
