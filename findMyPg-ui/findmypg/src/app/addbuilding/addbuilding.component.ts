import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { OwnerServiceService } from '../owner-service.service';

@Component({
  selector: 'app-addbuilding',
  templateUrl: './addbuilding.component.html',
  styleUrl: './addbuilding.component.scss'
})
export class AddbuildingComponent implements OnInit{


  public constructor(private ownerService:OwnerServiceService,private formBuilder:FormBuilder,private httpClient:HttpClient,private router:Router)
  {
    this.myForm=formBuilder.group({
      'pgType':['',Validators.required],
      'location':['',Validators.required],
      'pgName':['',Validators.required],
      'numberofFloors':['',Validators.required],

    });
  }
myForm: FormGroup;
pgTypeArray=['Girls','Boys','Co-Living'];
selectedOption: any;
ownerId:any;
buildingId:any;

  
  ngOnInit(): void {
    this.ownerId= this.ownerService.getOwner().id;
    console.log(`This is owner Id in addBuilding components `,this.ownerId);
  }
  onReset() {
    this.myForm.reset();
  }
  addBuilding() {
    const buildingData={
      'id':this.ownerId,
      'pgType':this.myForm.get('pgType')?.value,
      'location':this.myForm.get('location')?.value,
      'pgName':this.myForm.get('pgName')?.value,
      'numberofFloors':this.myForm.get('numberofFloors')?.value

    }
    this.httpClient.post(`api/findmypg/building/addBuilding`,buildingData).subscribe((response:any)=>{
        if (response!=null) {
          this.buildingId=response.id;
          console.log(`building id is `+this.buildingId);
          
          console.log('building added succesfully',response.id);
          this.router.navigate(['/addFloor']);
          // this.router.navigate(['/owner-screen']);
        } else {
          console.log('something went wrong  while adding building ',response);
          
        }
    });
  }

}
