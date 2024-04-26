import { Component, ElementRef, ViewChild, AfterViewInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { fromEvent, interval, Observable, timer, map, takeUntil, Subject } from 'rxjs';

@Component({
  selector: 'app-stop-watch4',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './stop-watch4.component.html',
})
export class StopWatch4Component implements AfterViewInit{
  
  stop$ = new Subject<boolean>;
  timer$!: Observable<number>;
  currentTime = 0;
  
  @ViewChild('startBtn') startBtn!: ElementRef;
  
  ngAfterViewInit(): void {
    fromEvent(this.startBtn.nativeElement, 'click')
      .subscribe(() => {
        this.startTimer();
      })
  }

  startTimer() {
    this.timer$ = interval(10)
      .pipe(
        map(val => val/100),
        takeUntil(this.stop$)
      )
  }

  stopTimer() {
    this.stop$.next(true);
  }

  resumeTimer() {
  }
}
