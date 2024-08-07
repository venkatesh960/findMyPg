import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { PaymentService } from '../payment.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.scss'
})
export class PaymentComponent {

  constructor(private paymentService: PaymentService) { }

  // Method to handle payment
  pay() {
    this.paymentService.createOrder(500, 'INR', 'receipt#1').subscribe(order => {
      const options = {
        key: 'rzp_test_1gmkkQ36LK6RTm', // Replace with your Razorpay key ID
        amount: order.amount, // Amount in paise
        currency: order.currency,
        name: 'find MyPg',
        description: 'Test Transaction',
        order_id: order.id,
        handler: (response: any) => {
          console.log('Payment successful:', response);
          
        },
        prefill: {
          name: 'John Doe',
          email: 'john.doe@example.com',
          contact: '9999999999'
        },
        callback_url:{
                 callback_url:"https://eneqd3r9zrjok.x.pipedream.net/"
        }, 
        theme: {
          color: '#3399cc'
        }
      };

      const paymentObject = new (window as any).Razorpay(options);
      paymentObject.open();
    }, error => {
      console.error('Error creating order:', error);
    });
  }
}