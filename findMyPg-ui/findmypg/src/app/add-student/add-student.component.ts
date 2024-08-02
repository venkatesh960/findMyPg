import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { OwnerServiceService } from '../owner-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { formatDate } from '@angular/common';
import { CustomDialogComponent } from '../custom-dialog/custom-dialog.component';
import { MatDialog } from '@angular/material/dialog';


@Component({
  selector: 'app-add-student',
  templateUrl: './add-student.component.html',
  styleUrl: './add-student.component.scss'
})
export class AddStudentComponent implements OnInit{

  myForm: FormGroup;
  ownerId:any;
  ownerData: any[]=[];
  buildingDetails: any={};
  minDate=new Date();
public constructor(private formBuilder:FormBuilder,
                  private httpClient:HttpClient,
                  private ownerService:OwnerServiceService,
                  private router:Router,
                  private route:ActivatedRoute,
                  private dialog:MatDialog)
{
  this.myForm=formBuilder.group({
    'firstName':['',Validators.required],
    'lastName':['',Validators.required],
    'middleName':['',Validators.required],
    'emailId':['',Validators.required],
    'mobileNumber':['',Validators.required],
    'idType':['',Validators.required],
    'idNumber':['',Validators.required],
    'joiningDate': ['', Validators.required]
  });
}
  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.buildingDetails = {
        pgName: params['pgName'],
        pgType: params['pgType'],
        floorNumber: params['floorNumber'],
        numberofRooms: params['numberofRooms'],
        roomNumber: params['roomNumber'],
        shareType: params['shareType'],
        rates: params['rates'],
        buildingId: params['buildingId'],
        floorId: params['floorId'],
        roomId: params['roomId']
      };
    });
    this.ownerId=this.ownerService.getOwner().id;
    console.log("Owner id is "+ this.ownerId);
    console.log(this.buildingDetails.floorId +" $$$ ");
    console.log(this.buildingDetails.buildingId +" ^^^ ");
    
    console.log(this.buildingDetails.roomId + " <<<<");
    console.log(this.buildingDetails.pgName +" %%%% ");
    
    
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
      'idType':this.myForm.get('idType')?.value,
      'joiningDate':formatDate(this.myForm.get('joiningDate')?.value, 'yyyy-MM-dd', 'en-US'),
      'buildingId':this.buildingDetails.buildingId,
      'floorId':this.buildingDetails.floorId,
      'roomId':this.buildingDetails.roomId

    }
    console.log("Student data ",studentData);
    
    this.httpClient.post('/api/findmypg/student/assignRoom',studentData).subscribe((response:any)=>{
      if (response===true) {
        console.log("student add successfully...."+ response);
        this.openCustomDialog(`Tenant ${this.myForm.get('lastName')?.value}Added Successfully `)
      } else {
        console.log("something went wrong while adding student ",response);
      }
    });
  }
  onReset() {
  this.myForm.reset(); 
  }
  openCustomDialog(message: string): void {
    const dialogRef=this.dialog.open(CustomDialogComponent, {
      data: { message, config: { okLabel: 'OK' } },
      width: '500px',
      minHeight:'20px',
      disableClose: true,
      
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      console.log('Dialog result:', result);
      if (result) {
        this.router.navigate(['/assign-student']);
      }
    });
  }

}
