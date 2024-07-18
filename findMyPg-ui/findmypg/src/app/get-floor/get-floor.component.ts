import { Component, OnInit } from '@angular/core';
import { OwnerServiceService } from '../owner-service.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-get-floor',
  templateUrl: './get-floor.component.html',
  styleUrl: './get-floor.component.scss'
})
export class GetFloorComponent implements OnInit{
 ownerId:any
 floorDetails:any;
 buildingIdArray:any[]=[];
  getFloorForm: FormGroup;
  selectedBuilding: any;
  listofBuildingsArray: any[]=[];
  buildingIdsArray: any[]=[];
  listofFloors: any[]=[];
  building:any;
  public constructor(private formBuilder:FormBuilder,private ownerService:OwnerServiceService,private httpClient:HttpClient,private router:Router)
  {
    this.getFloorForm=formBuilder.group({
      'selectedBuilding':['',Validators.required],
    }) 
  }
  ngOnInit(): void {
   this.ownerId=this.ownerService.getOwner().id;
   this.buildingIdArray=this.ownerService.getOwner().listofBuildings;
   console.log(this.buildingIdArray);
   this.getListOfBuildings(this.ownerId)
   
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
  onItemSelected() {
    this.selectedBuilding=this.getFloorForm.get('selectedBuilding')?.value;
    console.log("Selected Building ",this.selectedBuilding);
    this.building=this.buildingIdArray[this.listofBuildingsArray.indexOf(this.selectedBuilding)];
    console.log("Building id ",this.building);
    this.getFloorDetails(this.ownerId,this.building);
    
   }
  getFloorDetails(ownerId:any,buildingId:any):void{
    this.httpClient.get(`api/findmypg/floor/getListOfFloors?ownerId=${ownerId}&buildingId=${buildingId}`).subscribe((response:any)=>{
      if (response!=null) {
        console.log(response+" from displaying floor side ");
        this.floorDetails=response;
      } else {
        console.log(" something went wrong while displaying the floor details "+response);
        
      }
    });
    
  }
  displayBuildingDetails() {
    this.httpClient.get(`api/findmypg/building/getBuildingDetails?ownerId=${1}`).subscribe((response:any)=>{
      if (response!=null) {
        console.log("Building Detaild are ",response);
      } else {
        
      }
    });
  }
}
