import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OwnerServiceService } from '../owner-service.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-get-room',
  templateUrl: './get-room.component.html',
  styleUrl: './get-room.component.scss'
})
export class GetRoomComponent implements OnInit{
  myForm:FormGroup;
  listOfRooms:any
  ownerId:any
  listofBuildingsArray: any[] = [];
  buildingIdsArray: any[]=[];
  listofFloors: any[]=[];
  selectedBuilding:any;
  public constructor(private formBuilder:FormBuilder
    ,private ownerDataService:OwnerServiceService,
    private router:Router,
    private httpClient:HttpClient)
  {
    this.myForm=formBuilder.group({
      selectedBuilding:['',Validators.required],
    })
  }
  ngOnInit(): void {
    this.ownerId=this.ownerDataService.getOwner().id;
    this.getListOfBuildings(this.ownerId);
  }
  onItemSelected() {
   this.selectedBuilding=this.myForm.get('selectedBuilding')?.value;
   console.log("Selected Building ",this.selectedBuilding);
   
  }
  getListOfBuildings(ownerId: any) {
    this.httpClient.get(`api/findmypg/building/getBuildingDetails?ownerId=${ownerId}`).subscribe((response: any) => {
      if (response != null && Array.isArray(response)) {
        for (let index = 0; index < response.length; index++) {
          const ids = response[index].id;
          this.listofBuildingsArray.push(response[index].pgName);
          this.buildingIdsArray.push(ids);
          this.listofFloors.push(response[index].numberofFloors);
        }
        console.log(this.buildingIdsArray + " **  ");
        console.log("Building Details are ", this.listofBuildingsArray);
      } else {
        console.log("Something went wrong while displaying building ", response);
      }
    })
  }
  getRoomDetails():void{
    this.httpClient.get(`/api/findmypg/room/getListofRooms?floorId=${3}`).subscribe((response:any)=>{
      
      if (response!=null) {
        console.log("response from getting rooms ",response);
        this.listOfRooms=response;
        
      } else {
        console.log("something went wromg while displaying the room  details ",response);
        
      }
    });
  }

}
