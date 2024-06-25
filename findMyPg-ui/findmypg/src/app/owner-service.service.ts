import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OwnerServiceService {

  constructor() { }
  ownerData:any
 setOwner(owner:any){
  this.ownerData=owner
 }
  getOwner():any{
    return this.ownerData
  }

  
}
