import { Component, OnInit } from '@angular/core';
import { OwnerServiceService } from '../owner-service.service';

@Component({
  selector: 'app-get-floor',
  templateUrl: './get-floor.component.html',
  styleUrl: './get-floor.component.scss'
})
export class GetFloorComponent implements OnInit{
  public constructor(private ownerService:OwnerServiceService)
  {
    
  }
  ngOnInit(): void {
   
  }

}
