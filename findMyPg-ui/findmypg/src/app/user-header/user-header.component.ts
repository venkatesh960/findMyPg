import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-header',
  templateUrl: './user-header.component.html',
  styleUrls: ['./user-header.component.scss']
})
export class UserHeaderComponent {

  public constructor(private httpclient:HttpClient,private router:Router)
  {}
SignUpPage(){
  console.log("Routing toward signUp Page");
  this.router.navigate(['/owner-signup']);
}
loginPage() {
  console.log("routing towards  to login page ");
  this.router.navigate(['/loginPage']);
}

}
