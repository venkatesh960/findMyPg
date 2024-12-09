import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrl: './update-employee.component.scss'
})
export class UpdateEmployeeComponent implements OnInit{
  isUpadateEmployee: boolean = true;
  constructor(private router:Router) {
    
  }
  ngOnInit(): void {
    this.router.navigate([`/list-employee`], {
      queryParams: {
      updateEmployee:this.isUpadateEmployee,
    }});
  }

}
