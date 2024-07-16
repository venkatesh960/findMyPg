import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { OwnerSignupComponent } from './owner-signup/owner-signup.component';
import { UserHeaderComponent } from './user-header/user-header.component';
import { Router, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app.routes';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { MatFormField, MatInput, MatInputModule } from '@angular/material/input';
import { LoginComponent } from './login/login.component';
import { OwnerScreenComponent } from './owner-screen/owner-screen.component';
import { AddEmployeeComponent } from './add-employee/add-employee.component';
import { AddStudentComponent } from './add-student/add-student.component';
import { AddbuildingComponent } from './addbuilding/addbuilding.component';
import { MatSelectModule } from '@angular/material/select';
import { AddFloorComponent } from './add-floor/add-floor.component';
import { AddRoomComponent } from './add-room/add-room.component';
import { GetBuildingDetailsComponent } from './get-building-details/get-building-details.component';
import { DisplayBuildingsComponent } from './display-buildings/display-buildings.component';
import { BuildingComponent } from './building/building.component';
import { EmployeeComponent } from './employee/employee.component';
import { StudentComponent } from './student/student.component';
import { RoomComponent } from './room/room.component';
import { FloorComponent } from './floor/floor.component';
import { GetFloorComponent } from './get-floor/get-floor.component';
import { RemoveFloorComponent } from './remove-floor/remove-floor.component';
import { GetRoomComponent } from './get-room/get-room.component';
import { RemoveRoomComponent } from './remove-room/remove-room.component';
import { GetMyBuildingComponent } from './get-my-building/get-my-building.component';
import { ToastComponent } from './toast/toast.component';
import { CustomDialogComponent } from './custom-dialog/custom-dialog.component';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
// import { OwnerScreenComponent } from './owner-screen.component';

@NgModule({
  declarations: [
    AppComponent,
    UserHeaderComponent,
    OwnerSignupComponent,
    LoginComponent,
    OwnerScreenComponent,
    AddEmployeeComponent,
    AddStudentComponent,
    AddbuildingComponent,
    AddFloorComponent,
    AddRoomComponent,
    GetBuildingDetailsComponent,
    DisplayBuildingsComponent,
    BuildingComponent,
    EmployeeComponent,
    StudentComponent,
    RoomComponent,
    FloorComponent,
    GetFloorComponent,
    RemoveFloorComponent,
    GetRoomComponent,
    RemoveRoomComponent,
    GetMyBuildingComponent,
    ToastComponent,
    CustomDialogComponent,
    // OwnerScreenComponent, // Add your component here
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    MatInput,
    MatFormField,
    MatInputModule,
    MatSelectModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    MatIconModule,
    MatButtonModule,

  ],
  providers: [
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
