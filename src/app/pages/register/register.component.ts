import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { RouterModule } from '@angular/router';

@Component({
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  selector: 'app-register',
  templateUrl: './register.html',
  styleUrls: ['./register.css'],
})
export class RegisterComponent {
  email = '';
  password = '';
  fullName = '';
  message = '';
  error = '';

  constructor(private auth: AuthService, private router: Router) {}

register() {
  this.auth.register({
    email: this.email,
    password: this.password,
    fullName: this.fullName,
  }).subscribe({
    next: () => {
      this.message = 'Inscription réussie. Un e-mail vous a été envoyé.';
      this.router.navigate(['/thanks']);
    },
    error: (err) => {
      // err.error est un objet, on récupère la propriété 'message' si présente
      if (err.error && err.error.message) {
        this.error = err.error.message;
      } else {
        this.error = 'Une erreur est survenue.';
      }
    }
  });
}


}
