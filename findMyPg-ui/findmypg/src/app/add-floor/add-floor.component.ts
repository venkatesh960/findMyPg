import { Component, OnInit } from '@angular/core';
import { ObjectService } from '../object.service';

@Component({
  selector: 'app-add-floor',
  templateUrl: './add-floor.component.html',
  styleUrl: './add-floor.component.scss'
})
export class AddFloorComponent implements OnInit{

  public constructor(private objectDataService:ObjectService) {}


  ngOnInit(): void {
   console.log(this.objectDataService.getObject());
   
  }


}
