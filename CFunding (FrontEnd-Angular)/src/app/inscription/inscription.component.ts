import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';
import {InscriptionService} from '../../Services/inscription.service';
import {User} from '../../models/User';
import {Router} from '@angular/router';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.scss']
})
export class InscriptionComponent implements OnInit {

  constructor(private inscriptionService: InscriptionService, private router: Router) { }

  ngOnInit() {
  }

  onSubmit(f: NgForm) {
    console.log(f.value);
    const user =  new User(
      f.value.nomUser,
      f.value.preUser,
      f.value.emailUser,
      f.value.teleUser,
      f.value.password,
      'user');
    this.inscriptionService.inscription(user).subscribe(
      data => {
        console.log(data);
        this.router.navigate(['/signin']);
      }, error => {
        console.log(error);

      }
    );

  }
}
