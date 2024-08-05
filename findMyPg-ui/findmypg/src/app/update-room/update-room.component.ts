import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { OwnerServiceService } from '../owner-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-room',
  templateUrl: './update-room.component.html',
  styleUrls: ['./update-room.component.scss']
})
export class UpdateRoomComponent implements OnInit {
  listofFloors: any[] = [];
  myForm: FormGroup;
  selectedBuilding: string = '';
  buildingId: number = 0;
  ownerId: any;
  listofBuildingsArray: string[] = [];
  buildingIdsArray: number[] = [];

  constructor(
    private ownerService: OwnerServiceService,
    private httpClient: HttpClient,
    private formBuilder: FormBuilder,
    private router:Router,
  ) {
    this.myForm = this.formBuilder.group({
      selectedBuilding: ['', Validators.required],
      floorRooms: this.formBuilder.array([]),
      buildingId: [''],
      ownerId: ['']
    });
  }

  ngOnInit(): void {
    this.ownerId = this.ownerService.getOwner().id;
    this.getListOfBuildings(this.ownerId);
  }

  get floorRooms(): FormArray {
    return this.myForm.get('floorRooms') as FormArray;
  }

  getRoomsControls(floorIndex: number): FormArray {
    return this.floorRooms.at(floorIndex).get('rooms') as FormArray;
  }

  getListofRoomsforUpdate(ownerId: any, buildingId: any): void {
    this.httpClient.get(`api/findmypg/room/getListofRoomforUpdate?ownerId=${ownerId}&buildingId=${buildingId}`).subscribe((response: any) => {
      if (response != null && Array.isArray(response)) {
        console.log("Successfully got the rooms ", response);
        this.floorRooms.clear(); // Clear existing FormArray

        const floors = response.reduce((acc, room) => {
          const floor = room.floorNumber;
          if (!acc[floor]) {
            acc[floor] = [];
          }
          acc[floor].push(room);
          return acc;
        }, {});

        for (const floorNumber in floors) {
          const floorGroup = this.formBuilder.group({
            floorNumber: [floorNumber],
            rooms: this.formBuilder.array(
              floors[floorNumber].map((room: { roomNumber: any; shares: any; rates: any; }) => this.formBuilder.group({
                roomNumber: [room.roomNumber, Validators.required],
                shares: [room.shares, Validators.required],
                rates: [room.rates, Validators.required]
              }))
            )
          });
          this.floorRooms.push(floorGroup);
        }
      } else {
        console.log("Something went wrong while getting the room details ", response);
      }
    });
  }

  getListOfBuildings(ownerId: any): void {
    this.httpClient.get(`api/findmypg/building/getBuildingDetails?ownerId=${ownerId}`).subscribe((response: any) => {
      if (Array.isArray(response)) {
        this.listofBuildingsArray = response.map(building => building.pgName);
        this.buildingIdsArray = response.map(building => building.id);
        console.log("Building Details are ", this.listofBuildingsArray);
      } else {
        console.log("Something went wrong while displaying buildings ", response);
      }
    });
  }

  onItemSelected() {
    this.selectedBuilding = this.myForm.get('selectedBuilding')?.value;
    console.log("Selected building is ", this.selectedBuilding);
    this.buildingId = this.buildingIdsArray[this.listofBuildingsArray.indexOf(this.selectedBuilding)];
    console.log("Building id = ", this.buildingId);
    this.myForm.patchValue({ buildingId: this.buildingId, ownerId: this.ownerId });
    this.getListofRoomsforUpdate(this.ownerId, this.buildingId);
  }

  onSubmit(): void {
    if (this.myForm.valid) {
      console.log("Form Submitted:", this.myForm.value);
      this.httpClient.put(`api/findmypg/room/updateRoom`, this.myForm.value).subscribe((response: any) => {
       if(response===true){
         console.log("Successfully updated the rooms ", response);
         this.router.navigate([`/owner-screen`]);
         
       }else{
        console.log("Something went wrong while update=ing room Details ",response);
       }
      });
    }
  }

  onReset(): void {
    this.myForm.reset();
  }
}
