import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map, shareReplay, switchMap } from 'rxjs/operators';

import { Post } from '../models/post.model';
import { User } from '../models/user.model';
import { PostService } from '../services/post.service';


@Component({
  selector: 'app-posts-detail',
  templateUrl: './posts-detail.component.html',
  styleUrls: ['./posts-detail.component.css']
})
export class PostsDetailComponent implements OnInit {
  post$: Observable<Post>;
  author$: Observable<User>;

  constructor(
    private route: ActivatedRoute,
    private postService: PostService
  ) { }

  ngOnInit(): void {
    this.post$ = this.route.paramMap.pipe(
      map(params => Number(params.get('id'))),
      switchMap(id => this.postService.getPost(id)),
      shareReplay(1)
    );

    this.author$ = this.post$.pipe(
      switchMap(post => this.postService.getUser(post.userId))
    );
  }
}
