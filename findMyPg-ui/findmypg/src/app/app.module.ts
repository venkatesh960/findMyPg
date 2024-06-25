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
  ],
  providers: [
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
