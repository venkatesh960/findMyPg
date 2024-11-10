import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-service-page',
  templateUrl: './service-page.component.html',
  styleUrl: './service-page.component.scss'
})
export class ServicePageComponent implements OnInit {

  displaySideNavBar: boolean = false;

  constructor(private router: Router)
  {

  }

  ngOnInit(): void {

  }

  onClick() {

    this.router.navigate([`/userheader`]);
  }

  showContainer() {
    console.log("Iam here goes here ");

    this.displaySideNavBar = true;
  }


}
