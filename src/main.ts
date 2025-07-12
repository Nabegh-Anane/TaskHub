import { bootstrapApplication } from '@angular/platform-browser';
import { App } from './app/app';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { AuthInterceptor } from './app/interceptors/auth-interceptor';
import { appConfig } from './app/app.config';

bootstrapApplication(App, {
  ...appConfig,
  providers: [
    ...appConfig.providers!,
    provideHttpClient(withInterceptors([AuthInterceptor]))
  ]
}).catch((err) => console.error(err));
