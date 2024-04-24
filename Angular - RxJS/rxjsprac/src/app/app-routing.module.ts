import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StopWatchComponent } from './rxjs_examples/_01_stopwatch/stop-watch/stop-watch.component';
import { StopWatch2Component } from './rxjs_examples/_01_stopwatch/stop-watch2/stop-watch-2/stop-watch-2.component';

const routes: Routes = [
    { path: 'examples/stopwatch',
      component: StopWatchComponent },
      
    { path: 'examples/stopwatch/2',
      component: StopWatch2Component }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
