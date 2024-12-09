import { Component, OnInit } from '@angular/core';
import { OwnerServiceService } from '../owner-service.service';
import { HttpClient } from '@angular/common/http';
import { CustomDialogComponent } from '../custom-dialog/custom-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-list-employee',
  templateUrl: './list-employee.component.html',
  styleUrl: './list-employee.component.scss'
})
export class ListEmployeeComponent implements OnInit {



  ownerId: any;
  listofEmployees: any[] = [];
  isUpdateEmployeee: boolean = false;
  updateEmployeeForm: FormGroup;
  constructor(private onwerService: OwnerServiceService,
    private httpClient: HttpClient,
    private dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) {
    this.updateEmployeeForm = this.formBuilder.group({
      'firstName': ['', Validators.required],
      'lastName': ['', Validators.required],
      'emailId': ['', Validators.required],
      'userName': ['', Validators.required],
      'mobileNumber': ['', Validators.required],
      'salary': ['', Validators.required],
      ownerId: [this.ownerId], 
    });
  }
  ngOnInit(): void {
    this.ownerId = this.onwerService.getOwner().id;
    console.log("Owner Id ", this.ownerId);
    this.route.queryParams.subscribe(params => {
      this.isUpdateEmployeee = params['updateEmployee'];
    });
    console.log(this.isUpdateEmployeee + " from update employee request ");

    this.getLisOfEmployees(this.ownerId);


  }
  getLisOfEmployees(ownerId: any) {
    this.httpClient.get(`/api/findmypg/employee/getAllEmployee?ownerId=${ownerId}`).subscribe(response => {
      if (response != null && Array.isArray(response) && response.length > 0) {
        console.log("Response from the server is ", response);
        this.listofEmployees = response;
      } else {
        this.openCustomDialog("No Employee Found..")
      }
    });
  }
  removeEmployee(employee: any) {
    const dialogRef = this.dialog.open(CustomDialogComponent, {
      data: { message: 'Are you sure you want to delete the employee ..??' },
      width: '400px'
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        console.log("Employee id ", employee.id);

        this.httpClient.delete(`/api/findmypg/employee/removeEmployee?employeeId=${employee.id}`,).subscribe(response => {
          if (response === 0) {
            this.openCustomDialog("Employee Removed Succesfully")
            console.log("Employee Removed successsfully ");
          } else {
            this.openCustomDialog("Employee Doesnot Exists");
          }
        })
      }
    });
  }
  onItemSelected(employee: any) {
    console.log("Selected Employee: ", employee);

    if (employee) {
      this.updateEmployeeForm.patchValue({
        firstName: employee.firstName,
        lastName: employee.lastName,
        emailId: employee.emailId,
        mobileNumber: employee.mobileNumber,
        salary: employee.salary
      });
    }
  }

  onReset() {
    this.updateEmployeeForm.reset();
  }
  updateEmployee() {
    const updatedEmployeeData = this.updateEmployeeForm.value;
  
   
    console.log('Updated Employee Data:', JSON.stringify(updatedEmployeeData));
  
    this.httpClient.post('/api/findmypg/employee/updateEmployee', updatedEmployeeData)
      .subscribe(response => {
        if (response==0) {
          console.log('Employee updated successfully', response);
        }
      }, error => {
        console.log('Error while updating employee', error);
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
  printMe() {
    window.print();
  }

}
