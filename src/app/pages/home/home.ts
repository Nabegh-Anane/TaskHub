import { Component, HostListener } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [FormsModule, RouterModule],
  templateUrl: './home.html',
  styleUrls: ['./home.css']
})
export class Home {

  // Variable pour contrôler l'affichage du bouton
  showScrollTopBtn = false;

  submitContact() {
    alert('Formulaire soumis !');
  }

  // Écoute le scroll sur la fenêtre
  @HostListener('window:scroll', [])
  onWindowScroll() {
    // Si scroll vertical > 200px on affiche le bouton
    this.showScrollTopBtn = window.pageYOffset > 200;
  }

  // Fonction pour remonter en haut de page en douceur
  scrollToTop() {
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }
}
