import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OwnerSignupComponent } from './owner-signup/owner-signup.component'; // Assuming this is your signup component
import { UserHeaderComponent } from './user-header/user-header.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'userheader', component: UserHeaderComponent },
  { path: 'owner-signup', component: OwnerSignupComponent },
  // Add more routes as needed
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
