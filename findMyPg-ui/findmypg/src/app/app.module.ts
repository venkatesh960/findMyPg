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
// import { OwnerScreenComponent } from './owner-screen.component';

@NgModule({
  declarations: [
    AppComponent,
    UserHeaderComponent,
    OwnerSignupComponent,
    LoginComponent,
    OwnerScreenComponent,
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
  ],
  providers: [
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
