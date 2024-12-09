import { HttpClient } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { OwnerServiceService } from '../owner-service.service';
import  QRCode  from 'qrcode';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';



@Component({
  selector: 'app-get-student',
  templateUrl: './get-student.component.html',
  styleUrl: './get-student.component.scss'
})
export class GetStudentComponent implements OnInit {
  form: FormGroup;
  studentRoomsList: any[] = [];

  displayedColumns: string[] = ['sNo', 'firstName', 'lastName', 'emailId', 'mobileNumber', 'roomNumber', 'floorNumber', 'joiningDate', 'action'];
  ownerId: any;
  ownerResponse: any[] = [];
  qrCodeUrl: any;
  constructor(private httpClient: HttpClient,
    private formBuilder: FormBuilder,
    private router: Router,
    private ownerService: OwnerServiceService,
    private dialog:MatDialog,
  ) {
    this.form = this.formBuilder.group({
      'selectedBuilding': ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.ownerId = this.ownerService.getOwner().id;
    this.getMyBuilding();
  }

  getMyBuilding() {
    this.httpClient.get(`/api/findmypg/building/getBuildingDetails?ownerId=${this.ownerId}`).subscribe(response => {
      if (response != null && Array.isArray(response)) {
        this.ownerResponse = response;
        console.log("Owner Building details are " + response);

      } else {
        console.log("Something went wrong while getting Ownerbuilding ", response);

      }
    })
  }
  onItemSelected() {
    const selectedBuilding = this.form.get('selectedBuilding')?.value;
    this.getAllStudentAssignedRoom(selectedBuilding);
  }

  getAllStudentAssignedRoom(pgName: any) {
    this.httpClient.get(`/api/findmypg/student/getAssignedStudent?pgName=${pgName}`).subscribe(response => {
      if (response != null && Array.isArray(response)) {
        this.studentRoomsList = response;
        console.log("Student Rooms ", this.studentRoomsList);
      } else {
        console.log("Something went wrong while getting all student room deatisl ", response);

      }
    })
  }
  viewQRcode(room:any) {
    const text:string=room.mobileNumber+","+room.floorNumber+","+room.roomNumber+","+room.amount;
    QRCode.toDataURL(text)
      .then(url => {
        this.qrCodeUrl = url;
        this.openCustomDialogForQRCode(url);
      })
      .catch(err => {
        console.error(err);
      });
  }
  openCustomDialogForQRCode(qrCodeUrl: string): void {
    
    this.dialog.open(CustomDialogComponentForQrcode, {
      data: { qrCodeUrl: qrCodeUrl },
      width: '300px',
      minHeight: '200px',
      disableClose: true
    });
  }
  printMe() {
    window.print();
  }
  navigateToStudent() {
    this.router.navigate(['/userheader/students']);
  }
}
@Component({
  selector: 'app-custom-dialog',
  template: `
    <h2 mat-dialog-title>QR Code</h2>
    <mat-dialog-content>
      <img [src]="data.qrCodeUrl" alt="QR Code" style="width: 100%; height: auto;">
    </mat-dialog-content>
    <mat-dialog-actions align="end">
      <button mat-button mat-dialog-close color="primary" >Close</button>
    </mat-dialog-actions>
  `,
  styles: [`
    mat-dialog-content {
      text-align: center;
    }
  `]
})
export class CustomDialogComponentForQrcode {
  constructor(@Inject(MAT_DIALOG_DATA) public data: { qrCodeUrl: string }) {}
}
