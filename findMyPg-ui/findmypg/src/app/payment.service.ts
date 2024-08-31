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
    return this.http.get<any>(`/api/findmypg/payment/create-order?amount=${amount}&currency=${currency}&receipt=${receipt}`, {
      params: {
        amount: amount.toString(),
        currency: currency,
        receipt: receipt
      }
    });
  }
}
