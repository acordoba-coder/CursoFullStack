import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';

import { NotificationService } from './shared/services/notification.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'Reactive-Angular';
  toastMessage: string | null = null;

  private toastTimeoutId: ReturnType<typeof setTimeout> | undefined;
  private notificationSubscription: Subscription | undefined;

  constructor(private notificationService: NotificationService) { }

  ngOnInit(): void {
    this.notificationSubscription = this.notificationService.notification$.subscribe(message => {
      this.toastMessage = message;
      clearTimeout(this.toastTimeoutId);
      this.toastTimeoutId = setTimeout(() => this.toastMessage = null, 3000);
    });
  }

  ngOnDestroy(): void {
    this.notificationSubscription?.unsubscribe();
    clearTimeout(this.toastTimeoutId);
  }
}
