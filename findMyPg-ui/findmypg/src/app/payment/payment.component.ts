import { Component } from '@angular/core';
import { PaymentService } from '../payment.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.scss'
})
export class PaymentComponent {
  myForm: FormGroup<any>;

  constructor(private paymentService: PaymentService, private fromBuilder: FormBuilder) {
    this.myForm = this.fromBuilder.group({
      'amount': ['', Validators.compose([Validators.min(1), Validators.max(10000), Validators.required])],
      'currency': 'INR',
      'receipt': 'receipt#1',
    });
  }

  // Method to handle payment
  pay() {
    const amount: number = this.myForm.get('amount')?.value;
    this.paymentService.createOrder(amount, 'INR', 'receipt#1').subscribe((order: { amount: any; currency: any; id: any; }) => {
      const options = {
        key: 'rzp_test_D5SvNwVXYbEjvD', // Replace with your Razorpay key ID
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
        callback_url: {
          callback_url: "https://eneqd3r9zrjok.x.pipedream.net/"
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