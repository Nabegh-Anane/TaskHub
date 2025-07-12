import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  standalone: true,
  imports: [CommonModule],
  selector: 'app-thanks',
  templateUrl: './thanks.html',
  styleUrls: ['./thanks.css'],
})
export class ThanksComponent implements OnInit {

  constructor(private router: Router) {}

  ngOnInit(): void {
    setTimeout(() => {
      this.router.navigate(['/login']);
    }, 3000); // Redirection après 3 secondes
  }
}
