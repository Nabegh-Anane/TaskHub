// src/app/signup/signup.component.ts
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../core/auth.service';

@Component({
  standalone: true,
  selector: 'app-signup',
  imports: [CommonModule, FormsModule],
  template: `
  <div class="auth-container">
    <h2>Sign Up</h2>
    <form #f="ngForm" (ngSubmit)="submit(f)">
      <input type="text" name="fullName" ngModel placeholder="Full name" required />
      <input type="email" name="email" ngModel placeholder="Email" required />
      <input type="password" name="password" ngModel placeholder="Password" required />
      <button class="btn btn-primary" [disabled]="f.invalid">Register</button>
    </form>
    <p>Already have an account? <a routerLink="/signin">Sign in</a></p>
  </div>
  `,
  styles: [`.auth-container{ /* idem Signin */ }`]
})
export class SignUpComponent {
  constructor(private auth: AuthService, private router: Router) {}

  submit(form: any) {
    if (form.invalid) return;
    this.auth.register(form.value).subscribe({
      next: () =>
        this.router.navigate(['/signup/success'], { queryParams: { email: form.value.email } }),
      error: err => alert(err.error)
    });
  }
}
