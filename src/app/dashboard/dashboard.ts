// src/app/dashboard/dashboard.component.ts
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthService } from '../core/auth.service';

@Component({
  standalone: true,
  selector: 'app-dashboard',
  imports: [CommonModule, RouterModule],
  template: `
  <div class="container">
    <h1>Dashboard</h1>
    <p>Bienvenue ! Rôle détecté : <strong>{{ auth.role }}</strong></p>
    <button class="btn btn-outline" (click)="auth.logout()">Logout</button>
  </div>
  `
})
export class DashboardComponent {
  constructor(public auth: AuthService) {}
}
