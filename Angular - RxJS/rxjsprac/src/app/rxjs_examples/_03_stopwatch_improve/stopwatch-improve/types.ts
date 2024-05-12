

export interface CounterConfig {
  initialSetTo?: number;
  initialTickSpeed?: number;
  initialCountDiff?: number;
}

export interface CountDownState {
  isTicking: boolean;
  count: number; 
  countUp: boolean;
  tickSpeed: number;
  countDiff:number;
}

export type PartialCountDownState = 
  { isTicking: boolean } | 
  { count: number } | 
  { countUp: boolean } |
  { tickSpeed: number } |
  { countDiff:number};

export enum CounterStateKeys {
  isTicking = 'isTicking',
  count = 'count',
  countUp = 'countUp',
  tickSpeed = 'tickSpeed',
  countDiff = 'countDiff'
}

export enum ActionNames {
  Start, Pause, Reset, SetTo, Down, Up, TickSpeed, CountDiff
}

export class Counter {

}