import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { OwnerServiceService } from '../owner-service.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CustomDialogComponent } from '../custom-dialog/custom-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-vacant-student',
  templateUrl: './vacant-student.component.html',
  styleUrl: './vacant-student.component.scss'
})
export class VacantStudentComponent implements OnInit{
  
  studentRoomsList: any[]=[];
  ownerId:any;
  ownerData: any[]=[];
  selectedpgName: any;
  myForm: FormGroup;
  pgType: any[] = ['Girls', 'Boys', 'Co-Living'];
  selectedStudentId: any;
  selectedStudent: string='';
  constructor( private httpClient:HttpClient,
                private ownerService:OwnerServiceService,
                private formBuilder:FormBuilder,
                private dialog:MatDialog,
                private router:Router)
  {

    this.myForm = this.formBuilder.group({
      'id': ['', Validators.required],
      'pgName':['',Validators.required],
    });

  }

  ngOnInit(): void {
    this.ownerId=this.ownerService.getOwner().id;
    console.log("Owner Id >> ",this.ownerId);
    this.getListofBuildings(this.ownerId);
    
  }
  onItemSelected() {
    this.selectedpgName = this.myForm.get('pgName')?.value;
    console.log("Selected pgName ",this.selectedpgName);
    this.getAllStudentAssignedRoom(this.selectedpgName);
    
  }
  onSelectedStudent() {
    this.selectedStudentId=this.myForm.get('id')?.value;
    const selectedItem=this.studentRoomsList.find(student=>student.id===this.selectedStudentId)
    console.log(selectedItem);
    
    if(selectedItem){
      console.log(selectedItem.firstName+" khkhk");
      
      this.selectedStudent=selectedItem.firstName+""+selectedItem.lastName
      console.log("Selected student is ",this.selectedStudentId);
      console.log(this.selectedStudent +" ### ");
    }
    console.log(selectedItem.firstName+" kjhk ");
    
    
    
  }

  getListofBuildings(ownerId: any) {
    this.httpClient.get(`/api/findmypg/building/getBuildingDetails?ownerId=${ownerId}`).subscribe((response: any) => {
      if (response != null && Array.isArray(response)) {
        this.ownerData = response
        console.log("Building details are ",this.ownerData);
        
      } else {
        console.log("Failed to fetch building details");
      }
    });
  }
  getAllStudentAssignedRoom(selectedpgName:any){
    this.httpClient.get(`/api/findmypg/student/getAssignedStudent?pgName=${selectedpgName}`).subscribe(response=>{
      if (response!=null && Array.isArray(response)) {
        this.studentRoomsList=response.filter(student=>student.status==='IN');
        console.log("Student Rooms ",this.studentRoomsList);
      }else{
        console.log("Something went wrong while getting all student room deatisl ",response);
        
      }
    })
  }
  onSubmit() {
    const dialogRef = this.dialog.open(CustomDialogComponent, {
      data: { message: 'Are you sure you..??' },
      width: '400px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.httpClient.put(`/api/findmypg/student/vacantStudent`, this.myForm.value).subscribe(response => {
          if (response === true) {
            this.openCustomDialog(`${this.selectedStudent} is successfully vacated..`);
          } else {
            this.openCustomDialog(`${this.selectedStudent} is not present.`);
          }
        });
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
        this.router.navigate(['/userheader/student']);
      }
    });
  }

  onReset() {
    this.myForm.reset();
    window.location.reload();
  }
}