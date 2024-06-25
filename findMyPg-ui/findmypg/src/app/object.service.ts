import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ObjectService {

  constructor() { }
  private data:any
  setObject(object:any)
  {
    this.data=object;
  }

  getObject():any{
    return this.data;
  }
}
