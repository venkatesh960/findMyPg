import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { OwnerServiceService } from '../owner-service.service';

@Component({
  selector: 'app-get-student',
  templateUrl: './get-student.component.html',
  styleUrl: './get-student.component.scss'
})
export class GetStudentComponent implements OnInit{
form: FormGroup;
  studentRoomsList: any[]=[];

  
  ownerId:any;
  ownerResponse:any[]=[];
  constructor(private httpClient:HttpClient,
              private formBuilder:FormBuilder,
              private router:Router,
              private ownerService:OwnerServiceService,
  )
  {
    this.form=this.formBuilder.group({
      'selectedBuilding':['',Validators.required],
    });
  }
  
  ngOnInit(): void {
   this.ownerId=this.ownerService.getOwner().id;
   this.getMyBuilding();
  }

  getMyBuilding(){
    this.httpClient.get(`/api/findmypg/building/getBuildingDetails?ownerId=${this.ownerId}`).subscribe(response=>{
      if (response!=null && Array.isArray(response)) {
        this.ownerResponse=response;
        console.log("Owner Building details are "+response);
        
      } else {
        console.log("Something went wrong while getting Ownerbuilding ",response);
        
      }
    })
  }
  onItemSelected() {
    const selectedBuilding=this.form.get('selectedBuilding')?.value;
    this.getAllStudentAssignedRoom(selectedBuilding);
  }

  getAllStudentAssignedRoom(pgName:any){
    this.httpClient.get(`/api/findmypg/student/getAssignedStudent?pgName=${pgName}`).subscribe(response=>{
      if (response!=null && Array.isArray(response)) {
        this.studentRoomsList=response;
        console.log("Student Rooms ",this.studentRoomsList);
      }else{
        console.log("Something went wrong while getting all student room deatisl ",response);
        
      }
    })
  }
  printMe(){
    window.print();
  }
  navigateToStudent(){
    this.router.navigate(['/students']);
  }
}
