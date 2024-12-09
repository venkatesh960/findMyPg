import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserDataService {

  static id:number
  static userName: string
  static mobileNumber: string
  static emailId: string
  static userType: string
  static password: string

  constructor() { }
}
