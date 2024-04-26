import { Component, ElementRef, ViewChild, AfterViewInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { fromEvent, map, Observable, of, repeat, scan, Subject, takeUntil, tap, timer } from 'rxjs';

@Component({
  selector: 'app-stop-watch-3',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './stop-watch-3.component.html',
})
export class StopWatch3Component implements AfterViewInit {
  
  stop$ = new Subject<boolean>;
  timer$!: Observable<number>;
  currentTime = 0;
  

  @ViewChild('startBtn') startBtn!: ElementRef;
  
  ngAfterViewInit(): void {
    fromEvent(this.startBtn.nativeElement, 'click')
      .subscribe(() => {
        this.startTimer();
      });
  }

  startTimer() {
    this.timer$ = of(this.currentTime)
      .pipe(
        scan((acc, cur) => this.currentTime++, this.currentTime),
        tap(x => console.log("clicked " + x + " ---")),
        repeat({ delay: 1000 }),
        takeUntil(this.stop$)
      )
  }

  stopTimer() {
    this.stop$.next(true);
  }

}
