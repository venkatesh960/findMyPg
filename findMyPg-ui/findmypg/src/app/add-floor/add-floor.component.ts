import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { OwnerServiceService } from '../owner-service.service';
import { CustomDialogComponent } from '../custom-dialog/custom-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-add-floor',
  templateUrl: './add-floor.component.html',
  styleUrls: ['./add-floor.component.scss']
})
export class AddFloorComponent implements OnInit {

  selectedBuilding: any;
  form: FormGroup;
  buildingId: any;
  ownerId: any;
  listofFloors: any[] = [];
  listofBuildingsArray: any[] = [];
  buildingIdsArray: any[] = [];
  floorCount = 0;
  showAdditionalFields: boolean = false;
  booleanVaue: boolean = true;

  constructor(private formBuilder: FormBuilder,
              private httpClient: HttpClient,
              private router: Router,
              private ownerService: OwnerServiceService,
              private dialog: MatDialog) {
    this.form = this.formBuilder.group({
      selectedBuilding: ['', Validators.required], // FormControl for selected building
      floorsData: this.formBuilder.array([]) // FormArray to hold floors dynamically
    });
  }

  ngOnInit(): void {
    this.ownerId = this.ownerService.getOwner().id;
    this.getListOfBuildings(this.ownerId);
    console.log("owner Id ==>> ", this.ownerId);
  }

  onItemSelected() {
    this.selectedBuilding = this.form.get('selectedBuilding')?.value;
    console.log('Selected Building:', this.selectedBuilding);
    this.buildingId = this.buildingIdsArray[this.listofBuildingsArray.indexOf(this.selectedBuilding)];
    console.log("building id ===>> ", this.buildingId);
    this.floorCount = this.listofFloors[this.listofBuildingsArray.indexOf(this.selectedBuilding)];
    console.log("floor count will be >>> ", this.floorCount);

    // Dynamically add floor fields based on the floor count
    this.addFloors();
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
    });
  }

  addFloors() {
    // Clear existing floorsData
    while (this.floorsData.length) {
      this.floorsData.removeAt(0);
    }

    // Add the required number of floor fields
    for (let i = 0; i < this.floorCount; i++) {
      const floorGroup = this.formBuilder.group({
        buildingId: [this.buildingId],
        numberofRooms: ['', Validators.required],
        floorNumber: ['', Validators.required],
      });
      this.floorsData.push(floorGroup);
    }
  }

  get floorsData() {
    return this.form.get('floorsData') as FormArray;
  }

  onReset() {
    this.form.reset();
    this.floorCount = 0;
  }

  onSubmit() {
    console.log("Form submitted with floors:", this.form.value);
    // Implement your form submission logic here, e.g., HTTP POST request
    this.httpClient.post('/api/findmypg/floor/addFloor', this.form.value).subscribe(response => {
      if (response != null) {
        console.log("Response from server:", response);
        console.log("Building Id ", this.buildingId);
        this.openCustomDialog(`Floor Added Successfully`);
        // this.router.navigate([`/addRoom`],{queryParams:{id:this.buildingId}});
      } else {
        this.openCustomDialog("Floor Already Exists");
        console.log("Somethig went wrong while adding the floor details ", response);
      }
    });
  }

  openCustomDialog(message: string): void {
    const dialogRef = this.dialog.open(CustomDialogComponent, {
      data: { message, config: { okLabel: 'OK' } },
      width: '500px',
      minHeight: '20px',
      disableClose: true,
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      console.log('Dialog result:', result);
      if (result) {
        this.router.navigate(['/addRoom']);
      }
    });
  }
}
