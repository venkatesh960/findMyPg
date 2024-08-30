import { LiveAnnouncer } from '@angular/cdk/a11y';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import { Component, computed, EventEmitter, inject, model, Output, signal } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { MatChipInputEvent } from '@angular/material/chips';

@Component({
  selector: 'app-add-amenities',
  templateUrl: './add-amenities.component.html',
  styleUrl: './add-amenities.component.scss'
})
export class AddAmenitiesComponent {
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];
  readonly currentFruit = model('');
  readonly fruits = signal(['Wifi']);
  readonly allFruits: string[] = ['AC', 'TV', 'Fridge', 'Wifi', 'WashingMachine','Hot Water'];
  readonly filteredFruits = computed(() => {
    const currentFruit = this.currentFruit().toLowerCase();
    return currentFruit
      ? this.allFruits.filter(fruit => fruit.toLowerCase().includes(currentFruit))
      : this.allFruits.slice();
  });

  readonly announcer = inject(LiveAnnouncer);

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    
    if (value) {
      this.fruits.update(fruits => [...fruits, value]);
    }

    // Clear the input value
    this.currentFruit.set('');
  }

  remove(fruit: string): void {
    this.fruits.update(fruits => {
      const index = fruits.indexOf(fruit);
      if (index < 0) {
        return fruits;
      }

      fruits.splice(index, 1);
      this.announcer.announce(`Removed ${fruit}`);
      return [...fruits];
    });
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    this.fruits.update(fruits => [...fruits, event.option.viewValue]);
    this.currentFruit.set('');
    event.option.deselect();
  }
}