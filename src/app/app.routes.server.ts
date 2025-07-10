import { RenderMode, ServerRoute } from '@angular/ssr';

export const serverRoutes: ServerRoute[] = [
  { path: '', renderMode: RenderMode.Prerender },           // page d’accueil statique
  { path: 'signin', renderMode: RenderMode.Server },        // SSR dynamique
  { path: 'signup', renderMode: RenderMode.Server },
  { path: 'dashboard', renderMode: RenderMode.Server },
  { path: '**', renderMode: RenderMode.Prerender }          // fallback pour autres routes
];
