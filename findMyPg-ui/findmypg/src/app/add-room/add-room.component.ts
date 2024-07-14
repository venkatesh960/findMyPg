import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray, FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { OwnerServiceService } from '../owner-service.service';

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
  selectedBuilding: string = '';
  buildingId: number = 0; // Adjust type based on your API response
  ownerId: any; // Adjust type based on your API response
  listofBuildingsArray: string[] = [];
  buildingIdsArray: number[] = []; // Adjust type based on your API response
  listofFloorsArray: number[] = []; // Adjust type based on your API response
  floors: Floor[] = [];

  constructor(
    private ownerService: OwnerServiceService,
    private httpClient: HttpClient,
    private formBuilder: FormBuilder
  ) {
    this.myForm = this.formBuilder.group({
      selectedBuilding: ['', Validators.required],
      floorRooms: this.formBuilder.array([]), // Initialize as empty FormArray
    });
  }

  ngOnInit(): void {
    this.ownerId = this.ownerService.getOwner().id; // Adjust as per your service method
    this.getListOfBuildings(this.ownerId);
  }

  getListOfBuildings(ownerId: any): void {
    this.httpClient.get(`api/findmypg/building/getBuildingDetails?ownerId=${ownerId}`).subscribe((response: any) => {
      if (Array.isArray(response)) {
        this.listofBuildingsArray = response.map(building => building.pgName);
        this.buildingIdsArray = response.map(building => building.id); // Adjust as per your API response
        this.listofFloorsArray = response.map(building => building.numberofFloors); // Adjust as per your API response
        console.log("Building Details are ", this.listofBuildingsArray);
        console.log("List of Floors are ",this.listofFloorsArray);
        
      } else {
        console.log("Something went wrong while displaying buildings ", response);
      }
    });
  }

  onItemSelected(): void {
    this.selectedBuilding = this.myForm.get('selectedBuilding')?.value;
    this.buildingId=this.buildingIdsArray[this.listofBuildingsArray.indexOf(this.selectedBuilding)]
    console.log('Selected Building:', this.selectedBuilding);
    console.log("Building id: ",this.buildingId);
    

    // Clear existing floors
    this.floors = [];

    // Initialize floors array based on floor count
    const buildingIndex = this.listofBuildingsArray.indexOf(this.selectedBuilding);
    if (buildingIndex !== -1) {
      const numberOfFloors = this.listofFloorsArray[buildingIndex];
      for (let i = 0; i < numberOfFloors; i++) {
        const newFloor: Floor = { floorNumber: i, rooms: [] };
        this.floors.push(newFloor);
      }

      // Clear and rebuild floorRooms FormArray
      this.rebuildFloorRoomsArray();
    }
  }

  addRoom(floorIndex: number): void {
    const floorRoomsArray = this.myForm.get('floorRooms') as FormArray;
    const roomsArray = (floorRoomsArray.at(floorIndex) as FormGroup).get('rooms') as FormArray;

    const newRoomGroup = this.formBuilder.group({
      roomNumber: ['', Validators.required],
      shares: ['', Validators.required],
      rates: ['', Validators.required]
    });

    roomsArray.push(newRoomGroup);
  }

  removeRoom(floorIndex: number, roomIndex: number): void {
    const floorRoomsArray = this.myForm.get('floorRooms') as FormArray;
    const roomsArray = (floorRoomsArray.at(floorIndex) as FormGroup).get('rooms') as FormArray;

    roomsArray.removeAt(roomIndex);
  }

  rebuildFloorRoomsArray(): void {
    const floorRoomsArray = this.myForm.get('floorRooms') as FormArray;
    floorRoomsArray.clear();

    this.floors.forEach((floor, index) => {
      const floorGroup = this.formBuilder.group({
        floorNumber: [floor.floorNumber],
        rooms: this.formBuilder.array(floor.rooms.map(room => this.formBuilder.group({
          roomNumber: [room.roomNumber, Validators.required],
          shares: [room.shares, Validators.required],
          rates: [room.rates, Validators.required]
        })))
      });

      floorRoomsArray.push(floorGroup);
    });
  }

  getRoomsControls(floorIndex: number): FormArray {
    return (this.myForm.get('floorRooms') as FormArray).at(floorIndex).get('rooms') as FormArray;
  }

  onReset(): void {
    this.myForm.reset();
    this.floors = [];
  }

  onSubmit(): void {
    const formData = this.myForm.value;
    formData.buildingId = this.buildingId;
    console.log('Submitted Form Data:', formData);

    this.httpClient.post('/api/findmypg/room/addRooms', formData).subscribe(
      response => {
        console.log('Form submitted successfully:', response);
        this.myForm.reset();
        this.floors = [];
      },
      error => {
        console.error('Error submitting form:', error);
      }
    );
  }
}
