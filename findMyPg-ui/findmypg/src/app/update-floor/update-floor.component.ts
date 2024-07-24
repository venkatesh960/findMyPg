// import { HttpClient } from '@angular/common/http';
// import { Component, OnInit } from '@angular/core';
// import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import { Router } from '@angular/router';
// import { OwnerServiceService } from '../owner-service.service';

// @Component({
//   selector: 'app-update-floor',
//   templateUrl: './update-floor.component.html',
//   styleUrl: './update-floor.component.scss'
// })
// export class UpdateFloorComponent implements OnInit{

//   ownerId:any
//   floorDetails:any;
//   buildingIdArray:any[]=[];
//   updateFloorForm: FormGroup;
//    selectedBuilding: any;
//    listofBuildingsArray: any[]=[];
//    buildingIdsArray: any[]=[];
//    listofFloors: any[]=[];
//    building:any;
//    public constructor(private formBuilder:FormBuilder,private ownerService:OwnerServiceService,private httpClient:HttpClient,private router:Router)
//    {
//      this.updateFloorForm=this.formBuilder.group({
//        'selectedBuilding':['',Validators.required],
//      }) 
//    }
//    ngOnInit(): void {
//     this.ownerId=this.ownerService.getOwner().id;
//     this.buildingIdArray=this.ownerService.getOwner().listofBuildings;
//     console.log(this.buildingIdArray);
//     this.getListOfBuildings(this.ownerId)
    
//    }
//    getListOfBuildings(ownerId: any) {
//      this.httpClient.get(`api/findmypg/building/getBuildingDetails?ownerId=${ownerId}`).subscribe((response: any) => {
//        if (response != null && Array.isArray(response)) {
//          response.map(pgname=>this.listofBuildingsArray.push(pgname.pgName));
//          response.map(buildingId=>this.buildingIdsArray.push(buildingId.id));
//          response.map(listofRooms=>this.listofFloors.push(listofRooms.numberofFloors));
//          console.log(this.buildingIdsArray + " **  ");
//          console.log("Building Details are ", this.listofBuildingsArray);
//        } else {
//          console.log("Something went wrong while displaying building ", response);
//        }
//      })
//    }
//    onItemSelected() {
//      this.selectedBuilding=this.updateFloorForm.get('selectedBuilding')?.value;
//      console.log("Selected Building ",this.selectedBuilding);
//      this.building=this.buildingIdArray[this.listofBuildingsArray.indexOf(this.selectedBuilding)];
//      console.log("Building id ",this.building);
//      this.getFloorDetails(this.ownerId,this.building);
     
//    }
//    getFloorDetails(ownerId:any,buildingId:any):void{
//      this.httpClient.get(`api/findmypg/floor/getlistofFloorsForUpdate?ownerId=${ownerId}&buildingId=${buildingId}`).subscribe((response:any)=>{
//        if (response!=null) {
//          console.log(response+" from displaying floor side ");
//          this.floorDetails=response;
//        } else {
//          console.log(" something went wrong while displaying the floor details "+response);
         
//        }
//      });
//    }
//    onReset() {
//     this.updateFloorForm.reset();
//     }
//     updateFloor() {
    
//   }
//  }


import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import { Router } from '@angular/router';
import { OwnerServiceService } from '../owner-service.service';

@Component({
  selector: 'app-update-floor',
  templateUrl: './update-floor.component.html',
  styleUrls: ['./update-floor.component.scss']
})
export class UpdateFloorComponent implements OnInit {
  ownerId: any;
  floorDetails: any;
  buildingIdArray: any[] = [];
  updateFloorForm: FormGroup;
  selectedBuilding: any;
  listofBuildingsArray: any[] = [];
  buildingIdsArray: any[] = [];
  listofFloors: any[] = [];
  building: any;
  existingFloorCount: number = 0;

  constructor(
    private formBuilder: FormBuilder,
    private ownerService: OwnerServiceService,
    private httpClient: HttpClient,
    private router: Router
  ) {
    this.updateFloorForm = this.formBuilder.group({
      selectedBuilding: ['', Validators.required],
      floorsData: this.formBuilder.array([])  // Initialize as FormArray
    });
  }

  ngOnInit(): void {
    this.ownerId = this.ownerService.getOwner().id;
    // this.buildingIdArray = this.ownerService.getOwner().listofBuildings;
    this.getListOfBuildings(this.ownerId);
  }

  get floors(): FormArray {
    return this.updateFloorForm.get('floorsData') as FormArray;
  }

  createFloorFormGroup(floor: any): FormGroup {
    return this.formBuilder.group({
      buildingName: [floor.buildingName],
      floorNumber: [floor.floorNumber],
      numberofRooms: [floor.numberofRooms, Validators.required]
    });
  }

  getListOfBuildings(ownerId: any) {
    this.httpClient.get(`api/findmypg/building/getBuildingDetails?ownerId=${ownerId}`).subscribe((response: any) => {
      if (response != null && Array.isArray(response)) {
        response.map(pgname => this.listofBuildingsArray.push(pgname.pgName));
        response.map(buildingId => this.buildingIdsArray.push(buildingId.id));
        response.map(listofRooms => this.listofFloors.push(listofRooms.numberofFloors));
      } else {
        console.log("Something went wrong while displaying building ", response);
      }
    });
  }

  onItemSelected() {
    this.selectedBuilding = this.updateFloorForm.get('selectedBuilding')?.value;
    this.building = this.buildingIdsArray[this.listofBuildingsArray.indexOf(this.selectedBuilding)];
    this.getFloorDetails(this.ownerId, this.building);
  }

  getFloorDetails(ownerId: any, buildingId: any): void {
    this.httpClient.get(`api/findmypg/floor/getlistofFloorsForUpdate?ownerId=${ownerId}&buildingId=${buildingId}`).subscribe((response: any) => {
      if (response != null) {
        this.floorDetails = response;
        this.floors.clear();
        this.floorDetails.forEach((floor: any) => {
          this.floors.push(this.createFloorFormGroup(floor));
        });
      } else {
        console.log(" something went wrong while displaying the floor details ", response);
      }
    });
  }

  onReset() {
    this.updateFloorForm.reset();
    this.floors.clear();
  }

  updateFloor() {
    const formValue = {
      ownerId: this.ownerId,
      buildingId: this.building,
      floorsData: this.floors.value
    };
    console.log("While submiiting the floor details ",formValue);
    

    this.httpClient.put(`api/findmypg/floor/updateFloor`, formValue).subscribe(response => {
      if (response === true) {
        console.log("Floors updated successfully",response);
        setTimeout(() => {
          window.location.reload();
        }, 2000);
        this.router.navigate([`updateRoom`]);
      } else {
        console.error("Error updating floors", response);
      }
    });
  }
}

 