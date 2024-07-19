import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
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

  listofFloors: any[] = [];
  myForm: FormGroup;
  selectedBuilding: string = '';
  buildingId: number = 0;
  ownerId: any;
  listofBuildingsArray: string[] = [];
  buildingIdsArray: number[] = [];
  floors: Floor[] = [];
  numberofRoomsArray: any[] = [];
  listofFloorsArray: any[] = [];
  constructor(
    private ownerService: OwnerServiceService,
    private httpClient: HttpClient,
    private formBuilder: FormBuilder
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
    this.getListofFloors(this.ownerId, this.buildingId);
  }
  getListofFloors(ownerId: any, buildingId: any): void {
    this.numberofRoomsArray = [];
    this.listofFloorsArray = [];
    this.floorRooms.clear();
    this.httpClient.get(`api/findmypg/floor/getListOfFloors?ownerId=${ownerId}&buildingId=${buildingId}`).subscribe((response: any) => {
      if (response != null && Array.isArray(response)) {
        console.log("Get list of floors ", response);
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
        console.log(this.numberofRoomsArray);
        console.log(this.listofFloorsArray);
      } else {
        console.log("Something went wrong ", response);
      }
    });
  }
  onSubmit() {
    if (this.myForm.valid) {
      // Append buildingId and ownerId to the form data
      const formData = {...this.myForm.value, buildingId: this.buildingId, ownerId: this.ownerId };
      console.log("Form Submitted", formData);
      // Send form data to the API
      this.httpClient.post('/api/findmypg/room/addRooms', formData).subscribe(response => {
        if(response!==null){
          console.log("Submission response: ", response);

        }else{
          console.log("Rsponse is something ",response);
          
        }
      }, error => {
        console.error("Error during submission: ", error);
      });
    } else {
      console.log("Form is invalid");
    }
  }
  onReset() {
    this.myForm.reset();
}
}