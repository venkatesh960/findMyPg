import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-owner-screen',
  templateUrl: './owner-screen.component.html',
  styleUrl: './owner-screen.component.scss'
})
export class OwnerScreenComponent {


  public constructor(private httpClient:HttpClient,private router:Router)
  {

  }

addEmployee() {
  this.router.navigate(['/addEmployee']);
  
}
addStudent() {
  this.router.navigate(['/addStudent'])
}
addBuilding() {
  this.router.navigate(['/addBuilding'])
}
addFloor() {
 this.router.navigate(['/addFloor'])
  }
  addRooms() {
  this.router.navigate(['/addRoom']);
  }
  getBuildingDetails() {
    this.router.navigate(['/getBuilding']);
    }

}
