import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  standalone: true,
  imports: [CommonModule, RouterModule],
  selector: 'app-dashboard',
  templateUrl: './dashboard.html',
  styleUrls: ['./dashboard.css'],
})
export class DashboardComponent implements OnInit {
  token: string | null = null;
  sidebarOpen = true;
  selectedSection = 'users';
  showSettings = false;

  constructor(
    private auth: AuthService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.token = this.auth.getToken();
    this.router.navigate(['users'], { relativeTo: this.route });
  }

  logout() {
    this.auth.logout();
  }

  selectSection(section: string) {
    this.selectedSection = section;
    this.router.navigate([section], { relativeTo: this.route });
  }



  toggleSettings() {
    this.showSettings = !this.showSettings;
    if (this.showSettings) {
      this.router.navigate(['settings'], { relativeTo: this.route });
    }
  }
}
