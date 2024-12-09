import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { OwnerServiceService } from '../owner-service.service';
import { MatDialog } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SnackBarComponent } from '../snack-bar/snack-bar.component';
import { UserDataService } from '../user-data.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'] // Note the correct 'styleUrls'
})
export class LoginComponent implements OnInit {
<<<<<<< HEAD
=======

>>>>>>> e7737bb4dac11a21b92c32908008b933c4a6a901
  hide: boolean = true;
  private _snackBar = inject(MatSnackBar);

  formData: FormGroup;
  form: any;

  loadData: any;
<<<<<<< HEAD
=======

  // dialog: any;

>>>>>>> e7737bb4dac11a21b92c32908008b933c4a6a901
  public constructor(private router: Router,
    private httpClient: HttpClient,
    private ownerService: OwnerServiceService,
    private formBuilder: FormBuilder,
    private dialog: MatDialog,
    private toastr: ToastrService,
<<<<<<< HEAD
    // private snackBar: SnackBarComponent,
    private userDataService: UserDataService,
=======
    // private snackBar:SnackBarComponent,
>>>>>>> e7737bb4dac11a21b92c32908008b933c4a6a901
  ) {
    this.formData = this.formBuilder.group({
      'mobileNumber': ['', Validators.requiredTrue, Validators.pattern('[0-9]{10}')],
      'password': ['', Validators.required],
    });
  }
  ngOnInit(): void {
    // this.ownerService.clearOwner();
  }
<<<<<<< HEAD
  // togglePasswordVisibility() {
  //   this.hide = !this.hide
  // }
=======
  togglePasswordVisibility() {
    this.hide = !this.hide
  }
>>>>>>> e7737bb4dac11a21b92c32908008b933c4a6a901
  showToast() {
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

    this.httpClient.get('/api/findmypg/owner/login', { params }).subscribe((response: any) => {
<<<<<<< HEAD
      console.log(response, "Response form api call ");

      if (response !== null) {
        this.loadData = response;
        console.log("Login successful", response);
        UserDataService.mobileNumber = response.mobileNumber
=======
      if (response !== null) {
        this.loadData = response;
        console.log("Login successful", response);
>>>>>>> e7737bb4dac11a21b92c32908008b933c4a6a901
        this.ownerService.setOwner(response);
        this.openSnackBar("Login successful 🥳");
        this.router.navigate(['/userheader/owner-screen'])
      } else {
        console.log("Something went wrong", response);
      }
    }, (error: any) => {
      console.error("Login failed", error);
    });
  }
<<<<<<< HEAD
=======
  signUp() {
    this.router.navigate([`/userheader/owner-signup`]);
  }
>>>>>>> e7737bb4dac11a21b92c32908008b933c4a6a901
  openSnackBar(message: any) {
    this._snackBar.open(message, '', {
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
      duration: 1500,
    });
  }

  togglePasswordVisibility() {
    const passwordInput = document.getElementById('password') as HTMLInputElement | null;
    const eyeIcon = document.getElementById('eyeIcon');
  
    if (passwordInput && eyeIcon) {
      if (passwordInput.type === 'password') {
        passwordInput.type = 'text';
        eyeIcon.classList.replace('eye-closed-icon', 'eye-open-icon');  // Assuming you have classes for eye open and close
      } else {
        passwordInput.type = 'password';
        eyeIcon.classList.replace('eye-open-icon', 'eye-closed-icon');
      }
    }
  }
}


