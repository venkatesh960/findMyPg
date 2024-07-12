import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-get-building-details',
  templateUrl: './get-building-details.component.html',
  styleUrl: './get-building-details.component.scss'
})
export class GetBuildingDetailsComponent implements OnInit {

  constructor(private httpClient:HttpClient,private router:Router){

  }
  ngOnInit(): void {
    
  }



getBuildingDetails() {

}
getRoomDetails() {
throw new Error('Method not implemented.');
}
getFloorDetails() {
throw new Error('Method not implemented.');
}

}
