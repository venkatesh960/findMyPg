import { Component, OnInit } from '@angular/core';
import { OwnerServiceService } from '../owner-service.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-get-floor',
  templateUrl: './get-floor.component.html',
  styleUrl: './get-floor.component.scss'
})
export class GetFloorComponent implements OnInit{

floorDetails:any;
  public constructor(private ownerService:OwnerServiceService,private httpClient:HttpClient,private router:Router)
  {
    
  }
  ngOnInit(): void {
   
    this.getFloorDetails();
  }

  getFloorDetails():void{
    this.httpClient.get(`api/findmypg/floor/getListOfFloors?buildingId=${1}`).subscribe((response:any)=>{
      if (response!=null) {
        console.log(response+" from displaying floor side ");
        this.floorDetails=response;
      } else {
        console.log(" something went wrong while displaying the floor details "+response);
        
      }
    });
  }

}
