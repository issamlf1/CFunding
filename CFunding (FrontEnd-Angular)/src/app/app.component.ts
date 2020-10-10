import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';
import { AuthService } from 'src/Services/auth.service';
import {User} from '../models/User';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

user: User;
  ngOnInit() {

  }

  onSubmit(f: NgForm) {



  }
  constructor(private userServ : AuthService, private router: Router){
    this.user = userServ.giveUser();
  }


  onDeconnexion() {
    this.userServ.deconexion();
    this.router.navigate(['/signin'])
      .then(() => {
        window.location.reload();
      });
  }
}
