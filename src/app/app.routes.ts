import { provideRouter } from '@angular/router';
import { Routes } from '@angular/router';

import { Home } from './home/home';
import { SignInComponent } from './signin/signin.component';
import { SignUpComponent } from './signup/signup';
import { SignupSuccessComponent } from './signup-success/signup-success';
import { DashboardComponent } from './dashboard/dashboard';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'signin', component: SignInComponent },
  { path: 'signup', component: SignUpComponent },
  { path: 'signup-success', component: SignupSuccessComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: '**', redirectTo: '' }
];
