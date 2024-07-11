import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { ObjectService } from '../object.service';
import { OwnerServiceService } from '../owner-service.service';

@Component({
  selector: 'app-add-floor',
  templateUrl: './add-floor.component.html',
  styleUrls: ['./add-floor.component.scss']
})
export class AddFloorComponent implements OnInit {
  selectedItemId: any;
  form: FormGroup;
  buildingId: any;
  ownerId: any;
  listofFloors:any[]=[];
  listofBuildingsArray: any[] = [];
  buildingIdsArray: any[] = [];
  floorCount=0;
  showAdditionalFields: boolean = false;
  booleanVaue: boolean=true;

  constructor(private formBuilder: FormBuilder,
              private httpClient: HttpClient,
              private router: Router,
              private objectDataService: ObjectService,
              private ownerService: OwnerServiceService) {
    this.form = this.formBuilder.group({
      selectedItemId: ['', Validators.required], // FormControl for selected building
      floors: this.formBuilder.array([]) // FormArray to hold floors dynamically
    });
  }

  ngOnInit(): void {
    this.ownerId = this.ownerService.getOwner().id;
    this.getListOfBuildings(this.ownerId);
    console.log("owner Id ==>> ", this.ownerId);
  }

  onItemSelected() {
    this.selectedItemId = this.form.get('selectedItemId')?.value;
    console.log('Selected item:', this.selectedItemId);
    this.buildingId = this.buildingIdsArray[this.listofBuildingsArray.indexOf(this.selectedItemId)];
    console.log("building id ===>> ", this.buildingId);
    this.floorCount=this.listofFloors[this.listofBuildingsArray.indexOf(this.selectedItemId)]
    console.log("floor count will be >>> ",this.floorCount);
    
  }

  getListOfBuildings(ownerId: any) {
    this.httpClient.get(`api/findmypg/building/getBuildingDetails?ownerId=${ownerId}`).subscribe((response: any) => {
      if (response != null && Array.isArray(response)) {
        for (let index = 0; index < response.length; index++) {
          const ids = response[index].id;
          this.listofBuildingsArray.push(response[index].pgName);
          this.buildingIdsArray.push(ids);
          this.listofFloors.push(response[index].numberofFloors);
        }
        console.log(this.buildingIdsArray + " **  ");
        console.log("Building Details are ", this.listofBuildingsArray);
      } else {
        console.log("Something went wrong while displaying building ", response);
      }
    })
  }

  toggleAdditionalFields() {
    this.showAdditionalFields = !this.showAdditionalFields;
  }
 getFloorCount():number{
  return this.floorCount--;
 }
  addFloor() {
    if (this.getFloorCount()>0) {

      const floorGroup = this.formBuilder.group({
        buildingId: [this.buildingId],
        numberofRooms: ['', Validators.required],
        floor: ['', Validators.required],
      });
      this.floors.push(floorGroup);
    } else {
      this.booleanVaue=false;
    }

  }
  get floors() {
    return this.form.get('floors') as FormArray;
  }

  onSubmit() {
    console.log("Form submitted with floors:", this.form.value);
    // Implement your form submission logic here, e.g., HTTP POST request
    this.httpClient.post('/api/findmypg/floor/addFloors', this.form.value).subscribe(response => {
      console.log("Response from server:", response);
      // Handle response as needed
    });
  }
}
