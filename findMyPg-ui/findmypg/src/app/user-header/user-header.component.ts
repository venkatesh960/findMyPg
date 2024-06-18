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
  {
       
  }
SignUpPage() {
  this.router.navigateByUrl("/owner-signup")
}

}
