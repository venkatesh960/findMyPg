import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { OwnerServiceService } from '../owner-service.service';

@Component({
  selector: 'app-assign-student',
  templateUrl: './assign-student.component.html',
  styleUrls: ['./assign-student.component.scss']
})
export class AssignStudentComponent implements OnInit {
  ownerId: any;
  ownerData: any[] = [];
  myForm: FormGroup;
  pgType: any[] = ['Girls', 'Boys', 'Co-Living'];
  selectedPgType: any;

  constructor(
    private httpClient: HttpClient,
    private formBuilder: FormBuilder,
    private router: Router,
    private ownerService: OwnerServiceService
  ) {
    this.myForm = formBuilder.group({
      'pgType': ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.ownerId = this.ownerService.getOwner().id;
    console.log("Owner id =", this.ownerId);
    // this.getListofBuildings(this.ownerId);
  }

  onItemSelected() {
    this.selectedPgType = this.myForm.get('pgType')?.value;
    this.getListofBuildings(this.ownerId);
  }
  navigateToAddStudent(owner: any) {
    console.log("Before Navigate ",owner.buildingId
      
    );
    
    this.router.navigate(['/addStudent'], {
      queryParams: {
        buildingId: owner.buildingId,
        floorId: owner.floorId,
        roomId: owner.roomId,
        pgName: owner.pgName,
        pgType: owner.pgType,
        floorNumber: owner.floorNumber,
        numberofRooms: owner.numberofRooms,
        roomNumber: owner.roomNumber,
        shareType: owner.shareType,
        rates: owner.rates
      }
    });
  }
  
  getListofBuildings(ownerId: any) {
    this.httpClient.get(`/api/findmypg/building/getAvailbleBuilding?ownerId=${ownerId}`).subscribe((response: any) => {
      if (response != null && Array.isArray(response)) {
        this.ownerData = response.filter((building: any) => building.pgType === this.selectedPgType);
      } else {
        console.log("Failed to fetch building details");
      }
    });
  }

  onSubmit() {
    // Handle form submission
  }
}
