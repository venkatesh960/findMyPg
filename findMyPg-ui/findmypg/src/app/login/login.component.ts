import { HttpClient, HttpParams } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { OwnerServiceService } from '../owner-service.service';
import { CustomDialogComponent } from '../custom-dialog/custom-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { ToastService } from '../toast/toast.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'] // Note the correct 'styleUrls'
})
export class LoginComponent {
  formData: FormGroup;
  // dialog: any;

  public constructor(private router: Router, 
    private httpClient: HttpClient, 
    private ownerService:OwnerServiceService,
    private formBuilder: FormBuilder,
    private dialog:MatDialog,
    private toastService:ToastService,
    ) { 
    this.formData = this.formBuilder.group({
      'mobileNumber': ['', Validators.required],
      'password': ['', Validators.required],
    });
  }

  login() {
    const loginData = {
      'mobileNumber': this.formData.get('mobileNumber')?.value,
      'password': this.formData.get('password')?.value,
    };

    console.log("Login data: " + loginData.mobileNumber, loginData.password);

    const params = new HttpParams()
      .set('mobileNumber', loginData.mobileNumber)
      .set('password', loginData.password);

    this.httpClient.get('/api/findmypg/owner/login', { params }).subscribe((response:any) => {
      if (response!==null) {
        console.log("Login successful",response);
        this.ownerService.setOwner(response);
        this.toastService.setToast({
          message: 'Unknown error occured.',
          type: 'danger'
        });
        // this.openCustomDialog("Successfully Logged in ...!!")
        this.router.navigate(['/owner-screen'])
      } else {
        console.log("Something went wrong",response);
      }
    }, error => {
      console.error("Login failed", error);
    });
  }
  openCustomDialog(message: string): void {
    const dialogRef=this.dialog.open(CustomDialogComponent, {
      data: { message, config: { okLabel: 'OK' } },
      width: '50vw',
      minHeight:'20px',
      disableClose: true,
    });
   }

}
