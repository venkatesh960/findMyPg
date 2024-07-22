import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { OwnerServiceService } from '../owner-service.service';

@Component({
  selector: 'app-update-room',
  templateUrl: './update-room.component.html',
  styleUrl: './update-room.component.scss'
})
export class UpdateRoomComponent implements OnInit{
  ownerId:any;
  constructor(private formBuilder:FormBuilder,
              private router:Router,
              private ownerService:OwnerServiceService)
  {

  }
  ngOnInit(): void {
   this.ownerId=this.ownerService.getOwner().id;
   console.log("Owner Id");
   
  }

}
