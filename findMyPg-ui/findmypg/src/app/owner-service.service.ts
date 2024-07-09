// owner.service.ts
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OwnerServiceService {
  private storageKey = 'currentOwner';

  constructor() {}

  setOwner(owner: any): void {
    sessionStorage.setItem(this.storageKey, JSON.stringify(owner));
  }

  getOwner(): any {
    const owner = sessionStorage.getItem(this.storageKey);
    return owner ? JSON.parse(owner) : null;
  }

  clearOwner(): void {
    sessionStorage.removeItem(this.storageKey);
  }
}

