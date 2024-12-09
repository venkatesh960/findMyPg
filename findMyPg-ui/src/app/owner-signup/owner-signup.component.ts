import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-owner-signup',
  templateUrl: './owner-signup.component.html',
  styleUrl: './owner-signup.component.scss'
})
export class OwnerSignupComponent implements OnInit {

  public constructor(private router: Router,
    private formBuilder: FormBuilder,
    private apiService: ApiService,
    private httpClient: HttpClient,
  ) {
    this.form = formBuilder.group({
      'firstName': ['', Validators.required],
      'middleName': [''],
      'lastName': ['', Validators.required],
      'mobileNumber': ['', Validators.required],
      'emailId': ['', Validators.required],
      'userName': ['', Validators.required],
      'password': ['', Validators.required]
    });
  }
  form: FormGroup;
  ngOnInit(): void {

  }
  onReset() {
    this.form.reset();
  }
  ownerRegistration() {
    console.log("Owner Registered here ...!!!");
    const formData = {
      'firstName': this.form.get('firstName')?.value,
      'middleName': this.form.get('middleName')?.value,
      'lastName': this.form.get('lastName')?.value,
      'emailId': this.form.get('emailId')?.value,
      'userName': this.form.get('userName')?.value,
      'mobileNumber': this.form.get('mobileNumber')?.value,
      'password': this.form.get('password')?.value
    }

    console.log("Form Data => " , formData);

    this.httpClient.post("/api/findmypg/owner/registration", formData).subscribe(response => {
      if (response == true) {
        console.log("Succesfully Registered");
        
        this.router.navigate(['userheader/loginPage']);

      } else {
        console.log("something went wrong ");

      }
    });
  }

}
