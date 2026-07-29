import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  private readonly notificationSubject = new Subject<string>();

  readonly notification$: Observable<string> = this.notificationSubject.asObservable();

  notify(message: string): void {
    this.notificationSubject.next(message);
  }

}
