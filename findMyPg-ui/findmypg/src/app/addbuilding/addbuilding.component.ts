import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { OwnerServiceService } from '../owner-service.service';
import { ObjectService } from '../object.service';

@Component({
  selector: 'app-addbuilding',
  templateUrl: './addbuilding.component.html',
  styleUrl: './addbuilding.component.scss'
})
export class AddbuildingComponent implements OnInit{

  selectedSection: any;
showSection(arg0: string) {
  this.selectedSection=arg0;
}

displayBuildingDetails() {
throw new Error('Method not implemented.');
}
removeBuilding() {
throw new Error('Method not implemented.');
}

myForm: FormGroup;
pgTypeArray=['Girls','Boys','Co-Living'];
selectedOption: any;
ownerId:any;
buildingId:any;

  public constructor(private objectDataService:ObjectService,private ownerService:OwnerServiceService,private formBuilder:FormBuilder,private httpClient:HttpClient,private router:Router)
  {
    this.myForm=formBuilder.group({
      'pgType':['',Validators.required],
      'location':['',Validators.required],
      'pgName':['',Validators.required],
      'numberofFloors':['',Validators.required],

    });
  }
  ngOnInit(): void {
    this.ownerId= this.ownerService.getOwner().id;
    console.log(`This is owner Id in addBuilding components `,this.ownerId);
    
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
          console.log(response);
          this.objectDataService.setObject(response);
          this.router.navigate(['/addFloor']);
          
          
        } else {
          console.log('something went wrong  while adding building ',response);
          
        }
    });
  }

}
