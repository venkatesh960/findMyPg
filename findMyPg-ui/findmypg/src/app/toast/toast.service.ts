  
 import { Injectable } from "@angular/core";
 import { ToastModel } from "./toast.model";
 import { Subject } from "rxjs";
 
 @Injectable({providedIn: 'root'})
 export class ToastService {
 
   success(arg0: string) {
     throw new Error('Method not implemented.');
   }
   error(arg0: string) {
     throw new Error('Method not implemented.');
   }
   showSuccessToast(arg0: string, arg1: number) {
     throw new Error('Method not implemented.');
   }
   showErrorToast(arg0: string, arg1: number) {
     throw new Error('Method not implemented.');
   }
   toastSubject = new Subject<ToastModel|null>();
 
   setToast(toastMessage: ToastModel, duration: number = 5000) {
     this.toastSubject.next(toastMessage);
     setTimeout(() => {
       this.resetToast();
     }, duration);
   }
 
   resetToast() {
     this.toastSubject.next(null);
   }
 }
 