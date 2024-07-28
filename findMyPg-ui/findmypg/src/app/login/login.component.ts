import { HttpClient, HttpParams } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { OwnerServiceService } from '../owner-service.service';
import { CustomDialogComponent } from '../custom-dialog/custom-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { ToastService } from '../toast/toast.service';
import { Toast, ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'] // Note the correct 'styleUrls'
})
export class LoginComponent {
  hide:boolean=true;

  formData: FormGroup;
form: any;
  // dialog: any;

  public constructor(private router: Router, 
    private httpClient: HttpClient, 
    private ownerService:OwnerServiceService,
    private formBuilder: FormBuilder,
    private dialog:MatDialog,
    private toastr:ToastrService,
    ) { 
    this.formData = this.formBuilder.group({
      'mobileNumber': ['', Validators.requiredTrue,Validators.pattern('[0-9]{10}')],
      'password': ['', Validators.required],
    });
  }
  togglePasswordVisibility() {
    this.hide=!this.hide
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
        this.toastr.success('Hello world!', 'Toastr fun!');
        this.router.navigate(['/owner-screen'])
      } else {
        console.log("Something went wrong",response);
      }
    }, error => {
      console.error("Login failed", error);
    });
  }
}
