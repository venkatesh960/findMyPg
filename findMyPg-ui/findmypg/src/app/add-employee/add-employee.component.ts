import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { OwnerServiceService } from '../owner-service.service';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrl: './add-employee.component.scss'
})
export class AddEmployeeComponent implements OnInit{

myForm: FormGroup;
ownerDetails:any;
ownerId:any;

public constructor(private ownerService:OwnerServiceService ,private formBuilder:FormBuilder,private router:Router,private httpClient:HttpClient)
{
  this.myForm=formBuilder.group({
    'firstName':['',Validators.required],
    'middleName':['',Validators.required],
    'lastName':['',Validators.required],
    'emailId':['',Validators.required],
    'userName':['',Validators.required],
    'mobileNumber':['',Validators.required],
    'password':['',Validators.required]
  })
}
  ngOnInit(): void {
    this.ownerDetails=this.ownerService.getOwner();
    this.ownerId=this.ownerDetails.id;
    console.log(this.ownerId+" >>> this owner id ");
    console.log(this.ownerDetails+" >>> this is owner details ");
  }

  addEmployee(){
    const employee={
      'id':this.ownerId,
      'firstName':this.myForm.get('firstName')?.value,
      'middleName':this.myForm.get('middleName')?.value,
      'lastName':this.myForm.get('lastName')?.value,
      'emailId':this.myForm.get('emailId')?.value,
      'mobileNumber':this.myForm.get('mobileNumber')?.value,
      'userName':this.myForm.get('userName')?.value,
      'password':this.myForm.get('password')?.value
    }

    this.httpClient.post(`/api/findmypg/employee/addemployee`,employee).subscribe((response:any)=>{
      if (response!=null) {
        console.log("Employee added Suceesfully"); 
      } else {
        console.log("something went wrong while adding employee");
      }
    });    
  }
}
