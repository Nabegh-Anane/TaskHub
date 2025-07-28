import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { ThanksComponent } from './pages/thanks/thanks';
import { Home } from './pages/home/home';
import { NotFound } from './pages/not-found/not-found';
import { authGuard } from './guards/auth-guard';
import { guestGuard } from './guards/guest-guard';

// Import des composants enfants du dashboard
import { Users } from './pages/dashboard/pages/users/users';
import { Groupes } from './pages/dashboard/pages/groupes/groupes';
import { Projects } from './pages/dashboard/pages/projects/projects';
import { Epics } from './pages/dashboard/pages/epics/epics';
import { Sprints } from './pages/dashboard/pages/sprints/sprints';
import { Tasks } from './pages/dashboard/pages/tasks/tasks';
import { Notifications } from './pages/dashboard/pages/notifications/notifications';
import { Settings } from './pages/dashboard/pages/settings/settings';
import { Packs } from './pages/dashboard/pages/packs/packs';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'login', component: LoginComponent, canActivate: [guestGuard] },
  { path: 'register', component: RegisterComponent, canActivate: [guestGuard] },
  { path: 'thanks', component: ThanksComponent, canActivate: [guestGuard] },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [authGuard],
    children: [
      { path: '', redirectTo: 'users', pathMatch: 'full' },
      { path: 'users', component: Users },
      { path: 'groupes', component: Groupes },
      { path: 'projects', component: Projects },
      { path: 'epics', component: Epics },
      { path: 'sprints', component: Sprints },
      { path: 'tasks', component: Tasks },
      { path: 'notifications', component: Notifications },
      { path: 'settings', component: Settings },
      { path: 'packs', component: Packs },
    ]
  },
  { path: '**', component: NotFound },
];
