import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { OwnerSignupComponent } from './owner-signup/owner-signup.component';
import { UserHeaderComponent } from './user-header/user-header.component';

@NgModule({
  declarations: [
    AppComponent,
    UserHeaderComponent,
    OwnerSignupComponent, // Add your component here
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
