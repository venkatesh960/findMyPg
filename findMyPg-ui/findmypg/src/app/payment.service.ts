import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  apiUrl: any;

  constructor(private http: HttpClient) { }
   
  createOrder(amount: number, currency: string, receipt: string): Observable<any> {
    const date='2024-09-01';
    return this.http.get<any>(`api/findmypg/payment/create-order?amount=${amount}&currency=${currency}&receipt=${receipt}&mobileNumber=${9390661185}&userId=${1}&paymentDate=${date}`, {
      params: {
        amount: amount.toString(),
        currency: currency,
        receipt: receipt
      }
    });
  }
}
