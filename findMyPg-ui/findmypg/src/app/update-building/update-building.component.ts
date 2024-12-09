import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { OwnerServiceService } from '../owner-service.service';
import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-building',
  templateUrl: './update-building.component.html',
  styleUrls: ['./update-building.component.scss']
})
export class UpdateBuildingComponent implements OnInit {
  updateBuildingForm: FormGroup;
  ownerId: any;
  listofBuildingsArray: any[] = [];
  buildingDetails: any[] = [];
  pgTypes = ['Boys', 'Girls', 'Co-Living'];
  maxNumberofFloors: number = 0; // Initialize maxNumberofFloors

  constructor(
    private ownerService: OwnerServiceService,
    private formBuilder: FormBuilder,
    private httpClient: HttpClient,
    private router:Router,
  ) {
    this.updateBuildingForm = this.formBuilder.group({
      selectedBuilding: ['', Validators.required],
      pgName: ['', Validators.required],
      pgType: ['', Validators.required],
      location: ['', Validators.required],
      numberofFloors: ['', [Validators.required, this.minFloorsValidator()]],
    });
  }

  ngOnInit(): void {
    this.ownerId = this.ownerService.getOwner().id;
    this.getListOfBuildings(this.ownerId);
  }

  getListOfBuildings(ownerId: any) {
    this.httpClient.get(`api/findmypg/building/getBuildingDetails?ownerId=${ownerId}`).subscribe((response: any) => {
      if (response && Array.isArray(response)) {
        this.buildingDetails = response;
        this.listofBuildingsArray = response.map(pg => pg.pgName);
      } else {
        console.error("Error fetching building details", response);
      }
    });
  }

  onItemSelected() {
    const selectedBuilding = this.updateBuildingForm.get('selectedBuilding')?.value;
    const building = this.buildingDetails.find(b => b.pgName === selectedBuilding);
    
    if (building) {
      this.maxNumberofFloors = building.numberofFloors;

      // Update validators with the new maxNumberofFloors value
      this.updateBuildingForm.get('numberofFloors')?.setValidators([Validators.required, this.minFloorsValidator()]);
      this.updateBuildingForm.get('numberofFloors')?.updateValueAndValidity();

      this.updateBuildingForm.patchValue({
        pgName: building.pgName,
        pgType: building.pgType,
        location: building.location,
        numberofFloors: building.numberofFloors
      });
    }
  }

  minFloorsValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      if (control.value && control.value < this.maxNumberofFloors) {
        return { minFloors: { requiredValue: this.maxNumberofFloors, actualValue: control.value } };
      }
      return null;
    };
  }

  onReset() {
    this.updateBuildingForm.reset();
  }

  updateBuilding() {
    if (this.updateBuildingForm.invalid) {
      return; // Stop if the form is invalid
    }

    const formValue = {
      ownerId: this.ownerId,
      ...this.updateBuildingForm.value
    };

    this.httpClient.put(`api/findmypg/building/updateBuilding`, formValue).subscribe(response => {
      if (response === true) {
        console.log("Updated Successfully");
        // setTimeout(() => {
        //   window.location.reload();
        // }, 2000);
        this.router.navigate([`userheader/updateFloor`])
      } else {
        console.error("Error updating building", response);
      }
    });
  }
}
