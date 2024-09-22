import { Component, OnInit } from '@angular/core';
import { OwnerServiceService } from '../owner-service.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-list-employee',
  templateUrl: './list-employee.component.html',
  styleUrl: './list-employee.component.scss'
})
export class ListEmployeeComponent implements OnInit {

  ownerId: any;
  listofEmployees: any[] = [];
  constructor(private onwerService: OwnerServiceService,
    private httpClient: HttpClient,
  ) {

  }
  ngOnInit(): void {
    this.ownerId = this.onwerService.getOwner().id;
    console.log("Owner Id ", this.ownerId);
    this.getLisOfEmployees(this.ownerId);

  }
  getLisOfEmployees(ownerId: any) {
    this.httpClient.get(`/api/findmypg/employee/getAllEmployee?ownerId=${ownerId}`).subscribe(response => {
      if (response != null && Array.isArray(response) && response.length > 0) {
        console.log("Response from the server is ", response);
        this.listofEmployees = response;
      } else {
        
      }
    })
  }
  removeEmployee(employee: any) {
    
  }
  printMe() {
    window.print();
  }

}
