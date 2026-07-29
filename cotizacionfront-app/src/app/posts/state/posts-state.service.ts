import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

import { Post } from '../models/post.model';
import { PostService } from '../services/post.service';
import { NotificationService } from '../../shared/services/notification.service';

@Injectable({
  providedIn: 'root'
})
export class PostsStateService {

  private readonly postsSubject = new BehaviorSubject<Post[]>([]);
  private readonly loadingSubject = new BehaviorSubject<boolean>(false);

  readonly posts$: Observable<Post[]> = this.postsSubject.asObservable();
  readonly loading$: Observable<boolean> = this.loadingSubject.asObservable();

  constructor(
    private postService: PostService, 
    private notificationService: NotificationService) { }

  loadPosts(): void {
    this.loadingSubject.next(true);
    this.postService.getPosts().subscribe({
      next: posts => {
        this.postsSubject.next(posts);
        this.loadingSubject.next(false);
      },
      error: () => {
        this.loadingSubject.next(false);
        this.notificationService.notify('No se pudieron cargar los posts. Intenta de nuevo.');
      }
    });
  }

  get currentPosts(): Post[] {
    return this.postsSubject.getValue();
  }
}
