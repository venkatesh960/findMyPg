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
  floorsdata:any
  public constructor(private router:Router,private httpClient:HttpClient,private ownerService:OwnerServiceService)
  {
    
  }
  ngOnInit(): void {
   
    this.getFloorDetails();
  }

  getFloorDetails():void{
    this.httpClient.get(`/api/findmypg/floor/getListOfFloors?buildingId=${1}`).subscribe(response=>{
      if (response!=null) {
        console.log("successfully got response from get floor ",response);
        this.floorsdata=response;
        
      } else {
        console.log("something went wrong by getting floor details ",response);
        
      }
    });
  }

}
