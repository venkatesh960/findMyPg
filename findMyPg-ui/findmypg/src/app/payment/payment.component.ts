import { Component, OnInit } from '@angular/core';
import { PaymentService } from '../payment.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { CustomDialogComponent } from '../custom-dialog/custom-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.scss'
})
export class PaymentComponent implements OnInit{
  myForm: FormGroup<any>;
  paymentInfo:any={}

  constructor(
    private paymentService: PaymentService,
    private route:ActivatedRoute,
    private dialog: MatDialog,
    private router:Router,
    private fromBuilder: FormBuilder,
    private httpClient: HttpClient) {
    this.myForm = this.fromBuilder.group({
      'amount': ['', Validators.compose([Validators.min(1), Validators.max(10000), Validators.required])],
      'currency': 'INR',
      'receipt': 'receipt#1',
      'mobileNumber':['',Validators.required]
    });
    
  }
  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.paymentInfo = {
        studentId: params['id'],
        amount: params['amount'],
        mobileNumber:params['mobileNumber']
      };
    });
    if (this.paymentInfo.amount && this.paymentInfo.studentId && this.paymentInfo.mobileNumber) {
      this.openCustomDialog("Are You sure you want to proceed with payment?").then((confirmed) => {
        if (confirmed) {
          console.log("User confirmed the payment.");
          console.log("Payment information ", this.paymentInfo);
          this.pay(this.paymentInfo.amount,this.paymentInfo.mobileNumber);
        } else {
          console.log('User cancel the payment.'); 
        }
      }).catch((error) => {
        console.error("Error in dialog handling:", error);
      });
      console.log("Payment information ",this.paymentInfo);
    } else {
      console.log("Payment information is empty");
    }
  }
  
  pay(amount: any, mobileNumber: any) {
    console.log('amount ',amount);
    console.log('mobileNumber ', mobileNumber);
    
    
    if (amount && mobileNumber) {    
      console.log("enterd Amount ", this.paymentInfo);
      this.paymentService.createOrder(amount, 'INR', 'receipt#1').subscribe(
        (order) => {
          if (!order) {
            console.error('Order creation failed.');
            return;
          }
    
          console.log('Order created successfully:', order);
    
          const options = {
            key: 'rzp_test_uAD2cVyYsz6fWc', // Razorpay Key ID (Test Key)
            amount: order.amount, // Amount in paise
            currency: order.currency,
            name: 'Find My PG', // Merchant name
            description: 'Test Transaction',
            order_id: order.id, // Razorpay Order ID
            handler: (response: any) => {
              console.log('Payment successful:', response);
    
              const paymentDetails = {
                paymentId: response.razorpay_payment_id,
                orderId: response.razorpay_order_id,
                signature: response.razorpay_signature,
                receipt: order.receipt,
                amount: amount, // Amount in INR
                currency: 'INR',
                userId: 1, // Replace with the actual user ID
                mobileNumber: mobileNumber, // Replace with the actual user's mobile number
                paymentDate: new Date().toISOString().slice(0, 19).replace('T', ' '), // Current date in 'yyyy-MM-dd HH:mm:ss'
                status: order.status, // Razorpay doesn't send status; you can set it manually or validate on the backend
              };
              
            console.log('Before payment api call ',paymentDetails);
            
            this.httpClient.post(`/api/findmypg/payment/addPayment`, paymentDetails).subscribe(response => {
              if (response!=null && response==true) {
                console.log('Payment details saved successfully', response);
                this.openCustomDialogfoCheck("Payment Paid Successfully..")
                
              }
            }, (error) => {
              console.error('Error saving payment details', error);            
            }
            );
    
              // Send payment details to the server for verification and saving
              // this.httpClient.post('/api/findmypg/payment/verify', paymentDetails).subscribe(
              //   (res: any) => {
              //     if (res.status === 'success') {
              //       console.log('Payment verified successfully on the server.');
              //     } else {
              //       console.log('Payment verification failed on the server.');
              //     }
              //   },
              //   (error) => {
              //     console.error('Error verifying payment on the server:', error);
              //   }
              // );
            },
            prefill: {
              name: 'John Doe', // Prefill user details
              email: 'john.doe@example.com',
              contact: '9999999999',
            },
            theme: {
              color: '#3399cc', // Theme color for the Razorpay payment modal
            },
          };
    
          const paymentObject = new (window as any).Razorpay(options);
          paymentObject.open();
        },
        (error) => {
          console.error('Error creating order:', error);
        }
      );
    }
  }
  async openCustomDialog(message: string): Promise<boolean> {
    const dialogRef = this.dialog.open(CustomDialogComponent, {
      data: { message, config: { okLabel: 'OK', cancelLabel: 'Cancel' } },
      width: '500px',
      minHeight: '20px',
      disableClose: true,
    });
  
    // Return a promise that resolves with the dialog's result
    return dialogRef.afterClosed().toPromise();
  }

  openCustomDialogfoCheck(message: string) {
    const dialogRef = this.dialog.open(CustomDialogComponent, {
      data: { message, config: { okLabel: 'OK' } },
      width: '500px',
      minHeight: '20px',
      disableClose: true,
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      console.log('Dialog result:', result);
      if (result) {
        this.router.navigate(['userheader/owner-screen']);
      } else {
        console.log("Cancel Button is clicked ");
      }
    });
  }
  
}