import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { Post } from '../../posts/models/post.model';

@Injectable({
  providedIn: 'root'
})
export class FavoritesService {

  private readonly favoritesSubject = new BehaviorSubject<Post[]>([]);

  readonly favorites$: Observable<Post[]> = this.favoritesSubject.asObservable();

  toggle(post: Post): void {
    const current = this.favoritesSubject.getValue();
    const exists = current.some(favorite => favorite.id === post.id);

    const next = exists
      ? current.filter(favorite => favorite.id !== post.id)
      : [...current, post];

    this.favoritesSubject.next(next);
  }

  isFavorite$(postId: number): Observable<boolean> {
    return this.favorites$.pipe(
      map(favorites => favorites.some(favorite => favorite.id === postId))
    );
  }
}
