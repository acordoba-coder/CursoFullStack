import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { map, tap } from 'rxjs/operators';

import { Post } from '../models/post.model';
import { PostsStateService } from '../state/posts-state.service';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {

  posts$: Observable<Post[]>;
  loading$: Observable<boolean>;

  constructor(private postsState: PostsStateService) { }

  ngOnInit(): void {
    this.posts$ = this.postsState.posts$.pipe(
      tap(posts => console.log(`[PostList] estado actualizado: ${posts.length} posts`)),
      map(posts => posts.slice(0, 20))
    );
    this.loading$ = this.postsState.loading$;

    this.postsState.loadPosts();
  }

}
