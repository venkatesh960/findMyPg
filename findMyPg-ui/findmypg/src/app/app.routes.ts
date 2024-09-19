import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OwnerSignupComponent } from './owner-signup/owner-signup.component'; // Assuming this is your signup component
import { UserHeaderComponent } from './user-header/user-header.component';
import { LoginComponent } from './login/login.component';
import { OwnerScreenComponent } from './owner-screen/owner-screen.component';
import { AddEmployeeComponent } from './add-employee/add-employee.component';
import { AddStudentComponent } from './add-student/add-student.component';
import { AddbuildingComponent } from './addbuilding/addbuilding.component';
import { AddFloorComponent } from './add-floor/add-floor.component';
import { AddRoomComponent } from './add-room/add-room.component';
import { GetBuildingDetailsComponent } from './get-building-details/get-building-details.component';
import { DisplayBuildingsComponent } from './display-buildings/display-buildings.component';
import { BuildingComponent } from './building/building.component';
import { StudentComponent } from './student/student.component';
import { RoomComponent } from './room/room.component';
import { FloorComponent } from './floor/floor.component';
import { EmployeeComponent } from './employee/employee.component';
import { GetFloorComponent } from './get-floor/get-floor.component';
import { RemoveFloorComponent } from './remove-floor/remove-floor.component';
import { GetRoomComponent } from './get-room/get-room.component';
import { RemoveRoomComponent } from './remove-room/remove-room.component';
import { GetMyBuildingComponent } from './get-my-building/get-my-building.component';
import { UpdateBuildingComponent } from './update-building/update-building.component';
import { UpdateFloorComponent } from './update-floor/update-floor.component';
import { UpdateRoomComponent } from './update-room/update-room.component';
import { DialogAnimationsExample } from './dialog-animations-example/dialog-animations-example.component';
import { AssignStudentComponent } from './assign-student/assign-student.component';
import { GetStudentComponent } from './get-student/get-student.component';
import { VacantStudentComponent } from './vacant-student/vacant-student.component';
import { PaymentComponent } from './payment/payment.component';
import { SnackBarComponent } from './snack-bar/snack-bar.component';
import { ToastComponent } from './toast/toast.component';
import { AddAmenitiesComponent } from './add-amenities/add-amenities.component';
import { ListEmployeeComponent } from './list-employee/list-employee.component';
import { UpdateEmployeeComponent } from './update-employee/update-employee.component';
import { RemoveEmployeeComponent } from './remove-employee/remove-employee.component';


const routes: Routes = [
  { path: '', redirectTo: '', pathMatch: 'full' }, // Redirect to another default route,
  { path: 'userheader', component: UserHeaderComponent },
  { path: 'owner-signup', component: OwnerSignupComponent },
  { path: 'loginPage',component: LoginComponent},
  { path:'owner-screen',component:OwnerScreenComponent},
  { path:'getMyBuilding',component:GetMyBuildingComponent},

  { path:'employee',component:EmployeeComponent},
  { path: 'addEmployee', component: AddEmployeeComponent },
  { path: 'list-employee', component: ListEmployeeComponent },
  { path: 'update-employee', component: UpdateEmployeeComponent },
  { path: 'remove-employee', component: RemoveEmployeeComponent },

  { path:'student',component:StudentComponent},
  { path:'addStudent',component:AddStudentComponent},
  { path:'assign-student',component:AssignStudentComponent},
  { path:'getStudent',component:GetStudentComponent},
  { path:'vacant-student',component:VacantStudentComponent},

  { path:'floor',component:FloorComponent},
  { path:'addFloor',component:AddFloorComponent},
  { path:'getFloor',component:GetFloorComponent},
  { path:'updateFloor',component:UpdateFloorComponent},
  { path:'removeFloor',component:RemoveFloorComponent},

  { path:'room',component:RoomComponent},
  { path:'addRoom',component:AddRoomComponent},
  { path:'getRoom',component:GetRoomComponent},
  { path:'removeRoom',component:RemoveRoomComponent},
  { path:'updateRoom',component:UpdateRoomComponent},

  { path:'building',component:BuildingComponent}, 
  { path:'addBuilding',component:AddbuildingComponent},
  { path:'getBuilding',component:GetBuildingDetailsComponent},
  { path:'displayBuilding',component:DisplayBuildingsComponent},
  { path:'updateBuilding',component:UpdateBuildingComponent},

  { path:'custom-dialg-box',component:DialogAnimationsExample},
  { path:'payment',component:PaymentComponent},
  { path:'snack-bar',component:SnackBarComponent},
  { path:'toast-service',component:ToastComponent},
  { path:'add-amenities',component:AddAmenitiesComponent},
  


  

  
  // Add more routes as needed
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash:true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
