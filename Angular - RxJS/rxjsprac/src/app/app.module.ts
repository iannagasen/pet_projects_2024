import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StopWatch2Component } from './rxjs_examples/_01_stopwatch/stop-watch2/stop-watch-2/stop-watch-2.component';

@NgModule({
  declarations: [
    AppComponent,
    StopWatch2Component
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
