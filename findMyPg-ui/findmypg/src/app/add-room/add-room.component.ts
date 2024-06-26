import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ObjectService } from '../object.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-add-room',
  templateUrl: './add-room.component.html',
  styleUrl: './add-room.component.scss'
})
export class AddRoomComponent implements OnInit {
  myForm: FormGroup;
  floorId:any;
  public constructor(private httpClient:HttpClient,private objectService:ObjectService,private formBuilder:FormBuilder,private router:Router)
  {
    this.myForm=formBuilder.group({
      'roomNumber':['',Validators.required],
      'shares':['',Validators.required],
      'rates':['',Validators.required]
    })
  }
  ngOnInit(): void {
    this.floorId=this.objectService.getObject().id
  }
addRoom(): any{
  const roomData={
    'id':this.floorId,
    'roomNumber':this.myForm.get('roomNumber')?.value,
    'shares':this.myForm.get('shares')?.value,
    'rates':this.myForm.get('rates')?.value
  }
  this.httpClient.post('/api/findmypg/room/addRooms',roomData).subscribe((response:any)=>{
    if (response!=null) {
      console.log("Room added Successfully ",response);
    } else {
      console.log("something went wrong while adding room ",response);
      
    }
  })
}

}
