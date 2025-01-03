import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { OwnerServiceService } from '../owner-service.service';
import { CustomDialogComponent } from '../custom-dialog/custom-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrl: './add-employee.component.scss'
})
export class AddEmployeeComponent implements OnInit{
onFileSelected($event: Event) {
throw new Error('Method not implemented.');
}
onReset() {
throw new Error('Method not implemented.');
}

myForm: FormGroup;
ownerDetails:any;
ownerId:any;

  public constructor(private ownerService: OwnerServiceService,
    private formBuilder: FormBuilder,
    private router: Router,
    private httpClient: HttpClient,
    private  dialog: MatDialog)

{
  this.myForm=formBuilder.group({
    'firstName':['',Validators.required],
    'middleName':['',Validators.required],
    'lastName':['',Validators.required],
    'emailId':['',Validators.required],
    'userName':['',Validators.required],
    'mobileNumber':['',Validators.required],
    'password': ['', Validators.required],
    'salary':['',Validators.required],
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
      'password': this.myForm.get('password')?.value,
      'salary':this.myForm.get('salary')?.value,
    }

    this.httpClient.post(`/api/findmypg/employee/addemployee`,employee).subscribe((response:any)=>{
      if (response===0) {
        console.log("Employee added Suceesfully"); 
        this.openCustomDialog("Employee Added Successfully..")
      } else if(response===1){
        this.openCustomDialog("Mobile Number Already Exists ")
        console.log("something went wrong while adding employee");
      } else if (response===2) {
        this.openCustomDialog("Invalid Owner ")
      }
    });    
  }

  openCustomDialog(message: string): void {
    const dialogRef = this.dialog.open(CustomDialogComponent, {
      data: { message, config: { okLabel: 'OK' } },
      width: '500px',
      minHeight: '20px',
      disableClose: true,
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      console.log('Dialog result:', result);
      if (result) {
        this.router.navigate(['/employee']);
      }
    });
  }
}
