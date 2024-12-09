import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-owner-screen',
  templateUrl: './owner-screen.component.html',
  styleUrl: './owner-screen.component.scss'
})
export class OwnerScreenComponent {



  public constructor(private httpClient: HttpClient, private router: Router) {

  }

  Employee() {
    this.router.navigate(['/userheader/employee']);

  }
  Student() {
    this.router.navigate(['/userheader/student'])
  }
  Building() {
    this.router.navigate(['/userheader/building'])
  }
  Floor() {
    this.router.navigate(['/userheader/floor'])
  }
  Rooms() {
    this.router.navigate(['/userheader/room']);
  }
  navigateToReports() {
    this.router.navigate(['/userheader/reports']);

  }

}
