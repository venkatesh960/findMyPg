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
  ownerData: any[]=[];
public constructor(private formBuilder:FormBuilder,private httpClient:HttpClient,private ownerService:OwnerServiceService,private router:Router)
{
  this.myForm=formBuilder.group({
    'firstName':['',Validators.required],
    'lastName':['',Validators.required],
    'middleName':['',Validators.required],
    'emailId':['',Validators.required],
    'mobileNumber':['',Validators.required],
    'idType':['',Validators.required],
    'idNumber':['',Validators.required]
  });
}
  ngOnInit(): void {
    this.ownerId=this.ownerService.getOwner().id;
    console.log("Owner id is "+ this.ownerId);
    this.getListofBuildings(this.ownerId);
  }
  Onsubmit():any{
    console.log("Befor Submitting ",this.myForm.value);
    
    const studentData={
      'id':this.ownerId,
      'firstName':this.myForm.get('firstName')?.value,
      'middleName':this.myForm.get('middleName')?.value,
      'lastName':this.myForm.get('middleName')?.value,
      'emailId':this.myForm.get('emailId')?.value,
      'mobileNumber':this.myForm.get('mobileNumber')?.value,
      'idNumber':this.myForm.get('idNumber')?.value,
      'idType':this.myForm.get('idType')?.value
    }
    console.log("Student data ",studentData);
    
    this.httpClient.post('/api/findmypg/student/addStudent',studentData).subscribe((response:any)=>{
      if (response===true) {
        console.log("student add successfully...."+ response);
        
      } else {
        console.log("something went wrong while adding student ",response);
        
      }
    });
  }
  getListofBuildings(ownerId:any){
    console.log("iam here ");
    
    this.httpClient.get(`/api/findmypg/owner/getBuildingDetails?ownerId=${ownerId}`).subscribe(response=>{
      if (response!=null && Array.isArray(response)) {
        console.log("goood ",response);
        this.ownerData=response;
      } else {
        console.log("bad ");
        
      }
    })
  }
  onReset() {
  this.myForm.reset(); 
  }
}
