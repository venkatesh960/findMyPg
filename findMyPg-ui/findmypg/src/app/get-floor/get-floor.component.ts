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
 ownerId:any
 floorDetails:any;
  public constructor(private ownerService:OwnerServiceService,private httpClient:HttpClient,private router:Router)
  {
    
  }
  ngOnInit(): void {
   this.ownerId=this.ownerService.getOwner().id
    this.getFloorDetails();
  }
  printMe(){
    window.print();
  }
  getFloorDetails():void{
    this.httpClient.get(`api/findmypg/floor/getListOfFloors?ownerId=${this.ownerId}`).subscribe((response:any)=>{
      if (response!=null) {
        console.log(response+" from displaying floor side ");
        this.floorDetails=response;
      } else {
        console.log(" something went wrong while displaying the floor details "+response);
        
      }
    });
  }

}
