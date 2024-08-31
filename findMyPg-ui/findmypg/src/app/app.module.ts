import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { OwnerSignupComponent } from './owner-signup/owner-signup.component';
import { UserHeaderComponent } from './user-header/user-header.component';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app.routes';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
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
import { MatNativeDateModule, MatOptionModule } from '@angular/material/core';
import { UpdateBuildingComponent } from './update-building/update-building.component';
import { UpdateFloorComponent } from './update-floor/update-floor.component';
import { UpdateRoomComponent } from './update-room/update-room.component';
import { MatDialogActions, MatDialogClose, MatDialogContent, MatDialogModule, MatDialogTitle } from '@angular/material/dialog';
import { DialogAnimationsExample } from './dialog-animations-example/dialog-animations-example.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { ToastModule } from 'primeng/toast';
import { AssignStudentComponent } from './assign-student/assign-student.component';
import { MatCardModule } from '@angular/material/card';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { CustomDialogComponentForQrcode, GetStudentComponent } from './get-student/get-student.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { VacantStudentComponent } from './vacant-student/vacant-student.component';
import { PaymentComponent } from './payment/payment.component';
import {MatPaginatorModule} from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { SnackBarComponent } from './snack-bar/snack-bar.component';
import { MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button'; 
import { MatChipsModule} from '@angular/material/chips';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import { AddAmenitiesComponent } from './add-amenities/add-amenities.component';






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
    UpdateBuildingComponent,
    UpdateFloorComponent,
    UpdateRoomComponent,
    DialogAnimationsExample,
    AssignStudentComponent,
    GetStudentComponent,
    VacantStudentComponent,
    PaymentComponent,
    SnackBarComponent,
    AddAmenitiesComponent,
    CustomDialogComponentForQrcode,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    MatIconModule,
    MatButtonModule,
    MatOptionModule,
    MatTableModule,

    MatDialogActions,
    MatDialogClose,
    MatDialogContent,
    MatDialogTitle,
    MatDialogModule,
    MatSnackBarModule,
    BrowserAnimationsModule, 
    ToastModule,
    MatCardModule,
    MatButtonModule,
    MatInputModule,
    MatNativeDateModule,
    MatDatepickerModule,
    MatProgressSpinnerModule,
    MatPaginatorModule,
    MatSortModule,
    ButtonModule,
    MatChipsModule,
    MatAutocompleteModule,



  ],
  providers: [
    MessageService,
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }






