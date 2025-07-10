import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-signup-success',
  imports: [CommonModule, RouterModule],
  template: `
    <div class="auth-container">
      <h2>Vérifie ta boîte mail !</h2>
      <p>
        Nous avons envoyé un e‑mail de bienvenue à <strong>{{ email }}</strong
        >.
      </p>
      <button class="btn btn-primary" (click)="router.navigate(['/signin'])">
        Aller se connecter
      </button>
    </div>
  `,
})
export class SignupSuccessComponent implements OnInit {
  email = '';

  constructor(private route: ActivatedRoute, public router: Router) {}

  ngOnInit(): void {
    this.email = this.route.snapshot.queryParamMap.get('email') ?? '';
  }
}
