import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { OwnerServiceService } from '../owner-service.service';

@Component({
  selector: 'app-display-buildings',
  templateUrl: './display-buildings.component.html',
  styleUrl: './display-buildings.component.scss'
})
export class DisplayBuildingsComponent implements OnInit {
  buildingId:any;
  buildings: any;
  ownerId:any;
  public constructor(private ownerService:OwnerServiceService,private httpClient:HttpClient){

  }
  ngOnInit(): void {
    this.ownerId=this.ownerService.getOwner().id;
    console.log("Owner Id = "+this.ownerService.getOwner().id);
    this.buildingId=this.ownerService.getOwner().id;
    this.displayBuildingDetails();
    
  }
  printMe(){
    window.print();
  }
  displayBuildingDetails() {
    this.httpClient.get(`api/findmypg/building/getBuildingDetails?ownerId=${this.ownerId}`).subscribe((response:any)=>{
      if (response!=null) {
        console.log("Building Detaild are ",response);
        this.buildings=response;
      } else {
        
      }
    });
  }
  getImageSrc(base64String: string): string {
    return `data:image/jpeg;base64,${base64String}`;
  }

}
