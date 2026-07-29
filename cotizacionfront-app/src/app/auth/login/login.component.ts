import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent  {

 usernameControl = new FormControl('', Validators.required);

  constructor(
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  login(): void {
    if (this.usernameControl.invalid) {
      return;
    }

    this.authService.login(this.usernameControl.value);

    const returnUrl = this.route.snapshot.queryParamMap.get('returnUrl') || '/posts';
    this.router.navigateByUrl(returnUrl);
  }

}
