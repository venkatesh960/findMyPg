import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { OwnerServiceService } from '../owner-service.service';
import { MatDialog } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SnackBarComponent } from '../snack-bar/snack-bar.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'] // Note the correct 'styleUrls'
})
export class LoginComponent implements OnInit{
  hide:boolean=true;
  private _snackBar = inject(MatSnackBar);

  formData: FormGroup;
  form: any;

  loadData: any;
  
  // dialog: any;

  public constructor(private router: Router, 
    private httpClient: HttpClient, 
    private ownerService:OwnerServiceService,
    private formBuilder: FormBuilder,
    private dialog:MatDialog,
    private toastr:ToastrService,
    // private snackBar:SnackBarComponent,
    ) { 
    this.formData = this.formBuilder.group({
      'mobileNumber': ['', Validators.requiredTrue,Validators.pattern('[0-9]{10}')],
      'password': ['', Validators.required],
    });
  }
  ngOnInit(): void {
    // this.ownerService.clearOwner();
  }
  togglePasswordVisibility() {
    this.hide=!this.hide
}
showToast(){
  // this.snackBar.showToast("Hi","Close",1000);
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
        this.loadData=response;
        console.log("Login successful",response);
        this.ownerService.setOwner(response);
        this.openSnackBar("Login successful 🥳");
        this.router.navigate(['/owner-screen'])
      } else {
        console.log("Something went wrong",response);
      }
    }, (error: any) => {
      console.error("Login failed", error);
    });
  }
  openSnackBar(message:any) {
    this._snackBar.open(message, '', {
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
      duration:1500,
    });
  }

}
