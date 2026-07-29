import { Component, OnInit } from '@angular/core';
import { Observable, combineLatest } from 'rxjs';
import { PostService } from '../services/post.service';
import { debounceTime, distinctUntilChanged, map, startWith, switchMap, tap } from 'rxjs/operators';


import { Post } from '../models/post.model';
import { PostsStateService } from '../state/posts-state.service';
import { FormControl } from '@angular/forms';

import { FavoritesService } from '../../shared/services/favorites.service';

interface PostViewModel extends Post {
  isFavorite: boolean;
}

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {

  userIdControl = new FormControl('');

  posts$: Observable<PostViewModel[]>;
  loading$: Observable<boolean>;

  constructor(private postsState: PostsStateService,
              private postService: PostService,
              private favoritesService: FavoritesService) { }

  ngOnInit(): void {
    this.loading$ = this.postsState.loading$;
    this.postsState.loadPosts();

    const filteredPosts$ = this.userIdControl.valueChanges.pipe(
      startWith(''),
      debounceTime(300),
      distinctUntilChanged(),
      switchMap(value => this.postsForUserId(value)),
      tap(posts => console.log(`[PostList] mostrando ${posts.length} posts`))
    );

    this.posts$ = combineLatest([filteredPosts$, this.favoritesService.favorites$]).pipe(
      map(([posts, favorites]) => posts.map(post => ({
        ...post,
        isFavorite: favorites.some(favorite => favorite.id === post.id)
      }))),
      tap(posts => console.log(`[PostList] mostrando ${posts.length} posts`))
    );
  }

  toggleFavorite(post: Post): void {
    this.favoritesService.toggle(post);
  }

  private postsForUserId(value: string): Observable<Post[]> {
    const userId = Number(value);

    if (value && userId >= 1 && userId <= 10) {
      return this.postService.getPostsByUser(userId);
    }

    return this.postsState.posts$.pipe(
      map(posts => posts.slice(0, 20))
    );
  }

}
