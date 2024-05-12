import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StopWatchComponent } from './rxjs_examples/_01_stopwatch/stop-watch/stop-watch.component';
import { StopWatch2Component } from './rxjs_examples/_01_stopwatch/stop-watch2/stop-watch-2/stop-watch-2.component';
import { StopWatch3Component } from './rxjs_examples/_01_stopwatch/stop-watch-3/stop-watch-3.component';
import { StopWatch4Component } from './rxjs_examples/_01_stopwatch/stop-watch4/stop-watch4.component';
import { PigLatinComponent } from './rxjs_examples/_02_piglatinify/pig-latin/pig-latin.component';
import { StopwatchImproveComponent } from './rxjs_examples/_03_stopwatch_improve/stopwatch-improve/stopwatch-improve.component';
import { PokerComponent } from './rxjs_examples/_04_poker/poker/poker.component';

const routes: Routes = [
    { path: 'examples/stopwatch',
      component: StopWatchComponent },
      
    { path: 'examples/stopwatch/2',
      component: StopWatch2Component },
      
    { path: 'examples/stopwatch/3',
      component: StopWatch3Component },
      
    { path: 'examples/stopwatch/4',
    component: StopWatch4Component },
    
    { path: 'examples/piglatin',
    component: PigLatinComponent },
    
    { path: 'examples/stopwatch-improve',
    component: StopwatchImproveComponent },
    
    { path: 'examples/poker',
    component: PokerComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
