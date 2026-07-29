import { Component } from '@angular/core';
import { Observable } from 'rxjs';

import { Post } from '../../posts/models/post.model';
import { FavoritesService } from '../../shared/services/favorites.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.component.html',
  styleUrls: ['./favorites.component.css']
})
export class FavoritesComponent  {

  favorites$: Observable<Post[]> = this.favoritesService.favorites$;

  constructor(
    private favoritesService: FavoritesService,
    private authService: AuthService
  ) { }

  removeFavorite(post: Post): void {
    this.favoritesService.toggle(post);
  }

  logout(): void {
    this.authService.logout();
  }

}
