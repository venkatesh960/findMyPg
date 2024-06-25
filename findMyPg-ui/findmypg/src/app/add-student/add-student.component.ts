import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { OwnerServiceService } from '../owner-service.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-student',
  templateUrl: './add-student.component.html',
  styleUrl: './add-student.component.scss'
})
export class AddStudentComponent implements OnInit{
myForm: FormGroup;
ownerId:any;
public constructor(private formBuilder:FormBuilder,private httpClient:HttpClient,private ownerService:OwnerServiceService,private router:Router)
{
  this.myForm=formBuilder.group({
    'firstName':['',Validators.required],
    'lastName':['',Validators.required],
    'middleName':['',Validators.required],
    'emailId':['',Validators.required],
    'mobileNumber':['',Validators.required],
    'userName':['',Validators.required],
    'password':['',Validators.required]
  });
}
  ngOnInit(): void {
    this.ownerId=this.ownerService.getOwner().id;
    console.log("Owner id is "+ this.ownerId);
    
  }
  addStudent():any{
    const studentData={
      'id':this.ownerId,
      'firstName':this.myForm.get('firstName')?.value,
      'middleName':this.myForm.get('middleName')?.value,
      'lastName':this.myForm.get('middleName')?.value,
      'emailId':this.myForm.get('emailId')?.value,
      'mobileNumber':this.myForm.get('mobileNumber')?.value,
      'userName':this.myForm.get('userName')?.value,
      'password':this.myForm.get('password')?.value
    }
    this.httpClient.post('/api/findmypg/student/addStudent',studentData).subscribe((response:any)=>{
      if (response!=null) {
        console.log("student add successfully...."+ response);
        
      } else {
        console.log("something went wrong while adding student ",response);
        
      }
    });
  }
}
