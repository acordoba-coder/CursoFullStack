import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

const STORAGE_KEY = 'blog-reactivo.username';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly usernameSubject = new BehaviorSubject<string | null>(
    localStorage.getItem(STORAGE_KEY)
  );

  readonly username$: Observable<string | null> = this.usernameSubject.asObservable();
  currentUser$: any;

  get isLoggedIn(): boolean {
    return !!this.usernameSubject.getValue();
  }

  login(username: string): void {
    localStorage.setItem(STORAGE_KEY, username);
    this.usernameSubject.next(username);
  }

  logout(): void {
    localStorage.removeItem(STORAGE_KEY);
    this.usernameSubject.next(null);
  }
}
