import { Component, OnInit } from '@angular/core';
import { ObjectService } from '../object.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-floor',
  templateUrl: './add-floor.component.html',
  styleUrl: './add-floor.component.scss'
})
export class AddFloorComponent implements OnInit{
form: FormGroup;
buildingId:any;

public constructor(private formBuilder:FormBuilder,private httpClient:HttpClient,private router:Router,private objectDataService:ObjectService) {
  this.form=formBuilder.group({
    'numberofRooms':['',Validators.required],
    'floor':['',Validators.required],
  })
}

ngOnInit(): void {
  console.log(this.objectDataService.getObject());
  this.buildingId=this.objectDataService.getObject().id;
}
addFloor() {
  const floorData={
    'id':this.buildingId,
    'numberofRooms':this.form.get('numberofRooms')?.value,
    'floor':this.form.get('floor')?.value,
  }
  this.httpClient.post('/api/findmypg/floor/addFloor',floorData).subscribe((response:any)=>{
    if (response!=null) {
      console.log("floor add succesfully with response ",response);
      
      this.objectDataService.setObject(response);
      this.router.navigate(['/addRoom'])
    } else {
      console.log("something went wrong while adding floor ",response);
      
    }
  });
}


}
