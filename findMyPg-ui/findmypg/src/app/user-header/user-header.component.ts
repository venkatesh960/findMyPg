import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { OwnerServiceService } from '../owner-service.service';

@Component({
  selector: 'app-user-header',
  templateUrl: './user-header.component.html',
  styleUrls: ['./user-header.component.scss']
})
export class UserHeaderComponent {

  public constructor(private ownerService:OwnerServiceService, private httpclient:HttpClient,private router:Router)
  {}
SignUpPage(){
 
  this.router.navigate(['/owner-signup']);
}
logOutPage(){
  this.ownerService.clearOwner();
  this.router.navigate(['/loginPage']);
}
loginPage() {
  console.log("routing towards  to login page ");
  this.router.navigate(['/loginPage']);
}

}
