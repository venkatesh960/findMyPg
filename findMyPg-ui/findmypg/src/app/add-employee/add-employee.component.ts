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
    'midddleName':['',Validators.required],
    'lastName':['',Validators.required],
    'emailId':['',Validators.required],
    'userName':['',Validators.required],
    'mobileNumber':['',Validators.required],
    'password':['',Validators.required]
  })
}
  ngOnInit(): void {
    this.ownerDetails=this.ownerService;
    this.ownerId=this.ownerDetails.id;
    console.log(this.ownerId+" >>>>");
    

  }
  addEmployee(){

  }
}
