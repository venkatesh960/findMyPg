

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { OwnerServiceService } from '../owner-service.service';
import { CustomDialogComponent } from '../custom-dialog/custom-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';

interface Room {
  roomNumber: number;
  shares: number;
  rates: number;
}

interface Floor {
  floorNumber: number;
  rooms: Room[];
}

@Component({
  selector: 'app-add-room',
  templateUrl: './add-room.component.html',
  styleUrls: ['./add-room.component.scss']
})
export class AddRoomComponent implements OnInit {

  myForm: FormGroup;
  listofBuildingsArray: string[] = [];
  buildingIdsArray: number[] = [];
  numberofRoomsArray: any[] = [];
  listofFloorsArray: any[] = [];
  buildingId: number = 0;
  ownerId: any;
  selectedBuilding: any;

  constructor(
    private ownerService: OwnerServiceService,
    private httpClient: HttpClient,
    private formBuilder: FormBuilder,
    private dialog: MatDialog,
    private router: Router
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

  getListOfBuildings(ownerId: any): void {
    this.httpClient.get(`api/findmypg/building/getBuildingDetails?ownerId=${ownerId}`).subscribe((response: any) => {
      if (Array.isArray(response)) {
        this.listofBuildingsArray = response.map(building => building.pgName);
        this.buildingIdsArray = response.map(building => building.id);
      } else {
        console.error("Error displaying buildings", response);
      }
    });
  }

  onItemSelected() {
    this.selectedBuilding = this.myForm.get('selectedBuilding')?.value;
    this.buildingId = this.buildingIdsArray[this.listofBuildingsArray.indexOf(this.selectedBuilding)];
    this.myForm.patchValue({ buildingId: this.buildingId, ownerId: this.ownerId });
    this.getListofFloors(this.ownerId, this.buildingId);
  }

  getListofFloors(ownerId: any, buildingId: any): void {
    this.numberofRoomsArray = [];
    this.listofFloorsArray = [];
    this.floorRooms.clear();
    this.httpClient.get(`api/findmypg/floor/getListOfFloors?ownerId=${ownerId}&buildingId=${buildingId}`).subscribe((response: any) => {
      if (response != null && Array.isArray(response)) {
        response.forEach((floor: any) => {
          this.numberofRoomsArray.push(floor.numberofRooms);
          this.listofFloorsArray.push(floor.floorNumber);
          const floorGroup = this.formBuilder.group({
            floorNumber: floor.floorNumber,
            rooms: this.formBuilder.array(
              Array(floor.numberofRooms).fill(0).map((_, roomIndex) => this.formBuilder.group({
                roomNumber: [roomIndex + 1, Validators.required],
                shares: ['', Validators.required],
                rates: ['', Validators.required]
              }))
            )
          });
          this.floorRooms.push(floorGroup);
        });
      } else {
        console.error("Error displaying floors", response);
      }
    });
  }

  onSubmit() {
    if (this.myForm.valid) {
      const formData = { ...this.myForm.value, buildingId: this.buildingId, ownerId: this.ownerId };
      console.log("Before Submiting the form valur for add roon ",this.myForm.value);
      
      this.httpClient.post('/api/findmypg/room/addRooms', formData).subscribe(response => {
        if (response !== null) {
          console.log("Room Added successfully ",response);
          this.openCustomDialog("Room Added Successfully");
        } else {
          console.log("Something went while adding room ",response);

          this.openCustomDialog("Room Already Exists");
        }
      }, error => {
        console.error("Error during submission", error);
      });
    } else {
      console.error("Form is invalid");
    }
  }

  onReset() {
    this.myForm.reset();
  }

  openCustomDialog(message: string): void {
    const dialogRef = this.dialog.open(CustomDialogComponent, {
      data: { message, config: { okLabel: 'OK' } },
      width: '500px',
      minHeight: '20px',
      disableClose: true,
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.router.navigate(['/owner-screen']);
      }
    });
  }
  amenities:string[] = [];

  handleAmenitiesAdded(amenities: any) {
    this.amenities.push(...amenities);
    console.log('Amenities added:', this.amenities);
  }
}
