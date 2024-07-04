// owner.service.ts
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OwnerServiceService {
  private storageKey = 'currentOwner';

  constructor() {}

  setOwner(owner: any): void {
    localStorage.setItem(this.storageKey, JSON.stringify(owner));
  }

  getOwner(): any {
    const owner = localStorage.getItem(this.storageKey);
    return owner ? JSON.parse(owner) : null;
  }

  clearOwner(): void {
    localStorage.removeItem(this.storageKey);
  }
}

