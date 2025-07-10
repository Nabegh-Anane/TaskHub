// src/app/core/auth.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment';
import { jwtDecode } from 'jwt-decode';

interface JwtPayload { exp: number; role: string; }

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly TOKEN_KEY = 'taskhub_token';

  constructor(private http: HttpClient, private router: Router) {}

  register(data: { fullName: string; email: string; password: string }) {
    return this.http.post(`${environment.apiUrl}/auth/register`, data);
  }

  login(data: { email: string; password: string }) {
    return this.http.post<{ token: string }>(`${environment.apiUrl}/auth/login`, data)
      .subscribe({
        next: res => {
          localStorage.setItem(this.TOKEN_KEY, res.token);
          this.router.navigate(['/dashboard']);
        },
        error: () => alert('Invalid credentials')
      });
  }

  logout() {
    localStorage.removeItem(this.TOKEN_KEY);
    this.router.navigate(['/']);
  }

  get token(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  get isLoggedIn(): boolean {
    if (!this.token) return false;
    const { exp } = jwtDecode<JwtPayload>(this.token);
    return Date.now() < exp * 1000;
  }

  get role(): string | null {
    return this.token ? jwtDecode<JwtPayload>(this.token).role : null;
  }
}
