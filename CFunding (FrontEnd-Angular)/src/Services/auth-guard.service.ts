import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {AuthService} from './auth.service';
@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private auth: AuthService, private router: Router ) {}
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> |  boolean  {
    if( this.auth.giveUser() == null) {
      this.router.navigate(['/signin']);
    }
    return true;
  }

}
