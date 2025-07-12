import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';

@Component({
  standalone: true,
  imports: [CommonModule],
  selector: 'app-dashboard',
  template: `
    <h2>Dashboard</h2>
    <p>Token: {{ token }}</p>
    <button (click)="logout()">Logout</button>
  `,
})
export class DashboardComponent implements OnInit {
  token: string | null = null;

  constructor(private auth: AuthService) {}

  ngOnInit(): void {
    this.token = this.auth.getToken();
  }

  logout() {
    this.auth.logout();
  }
}
