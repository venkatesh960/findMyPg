import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-get-room',
  templateUrl: './get-room.component.html',
  styleUrl: './get-room.component.scss'
})
export class GetRoomComponent implements OnInit{
  listOfRooms:any
  public constructor(private router:Router,private httpClient:HttpClient)
  {
    
  }
  ngOnInit(): void {
    
    this.getRoomDetails();
  }

  getRoomDetails():void{

    this.httpClient.get(`/api/findmypg/room/getListofRooms?floorId=${1}`).subscribe((response:any)=>{

      if (response!=null) {
        console.log("response from getting rooms ",response);
        this.listOfRooms=response;
        
      } else {
        console.log("something went wromg while displaying the room  details ",response);
        
      }
    });
  }

}
