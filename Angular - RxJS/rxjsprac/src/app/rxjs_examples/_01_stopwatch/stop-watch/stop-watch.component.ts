import { AfterViewInit, Component, ElementRef, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { interval, map, merge, Observable, Subject, takeUntil } from 'rxjs';

@Component({
  selector: 'app-stop-watch',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './stop-watch.component.html',
})
export class StopWatchComponent implements AfterViewInit {
  // https://blog.stackademic.com/angular-pausing-resuming-and-restarting-a-stopwatch-timer-using-rxjs-cbee1c1f3a89

  stop$ = new Subject<boolean>();
  timer$!: Observable<number>;
  current = 0;

  @ViewChild('startBtn')
  startBtn!: ElementRef;

  @ViewChild('stopBtn')
  stopBtn!: ElementRef;

  ngAfterViewInit(): void {
  }

}
