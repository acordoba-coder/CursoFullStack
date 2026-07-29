import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription, Observable } from 'rxjs';
import { AuthService } from './auth/services/auth.service';
import { NotificationService } from './shared/services/notification.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'Reactive-Angular';
  toastMessage: string | null = null;
    username$: Observable<string | null> = this.authService.username$;

  private toastTimeoutId: ReturnType<typeof setTimeout> | undefined;
  private notificationSubscription: Subscription | undefined;

  constructor(
    private notificationService: NotificationService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.notificationSubscription = this.notificationService.notification$.subscribe(message => {
      this.toastMessage = message;
      clearTimeout(this.toastTimeoutId);
      this.toastTimeoutId = setTimeout(() => this.toastMessage = null, 3000);
    });

   // Con el tipo explícito en el parámetro
  this.username$ = this.authService.username$
  }

  logout(): void {
    this.authService.logout();
  }

  ngOnDestroy(): void {
    this.notificationSubscription?.unsubscribe();
    clearTimeout(this.toastTimeoutId);
  }
}
