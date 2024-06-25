import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OwnerSignupComponent } from './owner-signup/owner-signup.component'; // Assuming this is your signup component
import { UserHeaderComponent } from './user-header/user-header.component';
import { LoginComponent } from './login/login.component';
import { OwnerScreenComponent } from './owner-screen/owner-screen.component';
import { AddEmployeeComponent } from './add-employee/add-employee.component';
import { AddStudentComponent } from './add-student/add-student.component';
import { AddbuildingComponent } from './addbuilding/addbuilding.component';

const routes: Routes = [
  { path: '', redirectTo: 'userheader', pathMatch: 'full' },
  { path: 'userheader', component: UserHeaderComponent },
  { path: 'owner-signup', component: OwnerSignupComponent },
  { path: 'loginPage',component: LoginComponent},
  { path:'owner-screen',component:OwnerScreenComponent},
  { path:'addEmployee',component:AddEmployeeComponent},
  { path:'addStudent',component:AddStudentComponent},
  { path:'addBuilding',component:AddbuildingComponent},

  
  // Add more routes as needed
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash:true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
