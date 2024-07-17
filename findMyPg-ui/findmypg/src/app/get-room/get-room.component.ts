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
    this.getRoomDetails();
  }
  onItemSelected() {
   this.selectedBuilding=this.myForm.get('selectedBuilding')?.value;
   console.log("Selected Building ",this.selectedBuilding);
   
  }
  printMe(){
    window.print();
  }
  getListOfBuildings(ownerId: any) {
    this.httpClient.get(`api/findmypg/building/getBuildingDetails?ownerId=${ownerId}`).subscribe((response: any) => {
      if (response != null && Array.isArray(response)) {
        response.map(pgname=>this.listofBuildingsArray.push(pgname.pgName));
        response.map(buildingId=>this.buildingIdsArray.push(buildingId.id));
        response.map(listofRooms=>this.listofFloors.push(listofRooms.numberofFloors));
        console.log(this.buildingIdsArray + " **  ");
        console.log("Building Details are ", this.listofBuildingsArray);
      } else {
        console.log("Something went wrong while displaying building ", response);
      }
    })
  }
  getRoomDetails():void{
    this.httpClient.get(`/api/findmypg/room/getListofRooms?floorId=${this.ownerId}`).subscribe((response:any)=>{
      if (response!=null) {
        console.log("response from getting rooms ",response);
        this.listOfRooms=response;
        
      } else {
        console.log("something went wromg while displaying the room  details ",response);
        
      }
    });
  }

}
