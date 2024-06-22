import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OwnerServiceService {

  constructor() { }
  ownerData:any
  addOwner(owner:any)
  {
    this.ownerData=owner;
  }
  getOwner():any{
    return this.ownerData
  }
}
