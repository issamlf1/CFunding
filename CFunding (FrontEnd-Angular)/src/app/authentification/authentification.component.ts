import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';
import {AuthService} from '../../Services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-authentification',
  templateUrl: './authentification.component.html',
  styleUrls: ['./authentification.component.scss']
})
export class AuthentificationComponent implements OnInit {

  constructor(private authService: AuthService , private router: Router) { }

  ngOnInit() {
  }

  onSubmit(f: NgForm) {
    console.log(f.value);
    this.authService.authentification( f.value.email, f.value.password).subscribe(
      data => {
        this.authService.email= f.value.email
        let jwt = data.headers.get('Authorization').split(" ");
        console.log(jwt);
        let jwtToken=jwt[0]+" "+jwt[1]
        let idUser = Number(jwt[2])
        this.authService.saveToken(jwtToken);
        this.router.navigateByUrl("/mesprojects");
        this.router.navigate(['/mesprojects'])
          .then(() => {
            window.location.reload();
          });

      }, error => {
        console.log(error);

      }
    );
  }
}
