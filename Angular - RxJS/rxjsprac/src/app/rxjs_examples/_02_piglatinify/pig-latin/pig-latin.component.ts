import { Component, ElementRef, ViewChild, AfterViewInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { from, fromEvent, map, mergeMap, Observable, reduce, tap } from 'rxjs';

@Component({
  selector: 'app-pig-latin',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pig-latin.component.html',
})
export class PigLatinComponent implements AfterViewInit{
  
  pigLatin$!: Observable<string>;

  @ViewChild('input') inputBtn!: ElementRef;

  ngAfterViewInit(): void {
    this.pigLatin$ = fromEvent<KeyboardEvent>(this.inputBtn.nativeElement, 'keyup')
    .pipe(
      tap(console.log),
      map(e => e.target.value as string),
      mergeMap(wordString =>
        from(wordString.split(/\s+/) )
        .pipe(
          map(this.pigLatinify),
          reduce((bigString, newWord) => bigString + ' ' + newWord, '')
        )
      )
    )
  }

  pigLatinify(word: string): string { // Handle single->letter case and empty strings 
    if (word.length < 2) { 
      return word; 
    } 
    
    return word.slice(1) + '-' + word[0] + 'ay'; 
  }
}
