import { Component, OnInit } from '@angular/core';
import { OwnerServiceService } from '../owner-service.service';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-get-my-building',
  templateUrl: './get-my-building.component.html',
  styleUrl: './get-my-building.component.scss'
})
export class GetMyBuildingComponent implements OnInit{

  ownerId:any;
  ownerData:any;
  count=1;
  public constructor(private httpClient:HttpClient,private ownerService:OwnerServiceService)
  {

  }
  ngOnInit(): void {
   this.ownerId=this.ownerService.getOwner().id;
   console.log(this.ownerId);
   this.myBuilding(this.ownerId);
  }

  printMe() {
    window.print();
  }

  myBuilding(ownerId:any){
    console.log("iam here ");
    
    this.httpClient.get(`/api/findmypg/owner/getBuildingDetails?ownerId=${ownerId}`).subscribe(response=>{
      if (response!=null) {
        console.log("goood ",response);
        this.ownerData=response;
      } else {
        console.log("bad ");
        
      }
    })
  }

}
