import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {User} from '../../models/User';
import {ProjetService} from '../../Services/projet.service';
import {AuthService} from '../../Services/auth.service';
import {Projet} from '../../models/Projet';

@Component({
  selector: 'app-monprofil',
  templateUrl: './monprofil.component.html',
  styleUrls: ['./monprofil.component.scss']
})
export class MonprofilComponent implements OnInit {
  firstFormGroup: FormGroup;
  public userfile: any = File;
  public imagePath;
  srcimg: any = '';
  user: User = new User();
  nom: any;
  pren: any;
  tele: any;
  emaill: any;
  id:any;
  pss:any;
  constructor(private formBuilder: FormBuilder, private projetService: ProjetService, private userService: AuthService) {  }

  ngOnInit() {
    this.user = this.userService.giveUser() as User;
    console.log(this.user);
    this.id=this.user.idUser;
    this.nom=this.user.nomUser;
    this.pren=this.user.preUser;
    this.tele=this.user.teleUser;
    this.emaill=this.user.emailUser;
    this.srcimg = this.user.srcimage;
    this.pss=this.user.password;

  }

  onSubmit(data) {

    this.user.nomUser = this.nom;
    this.user.preUser = this.pren;
    this.user.emailUser = data.emailUser;

    this.user.password = data.password;
    this.user.teleUser = data.teleUser;
    if(data.password === data.passwordch){
      this.user.password = data.teleUser;
        this.projetService.saveUserUp(this.user).subscribe(d => {
          console.log(d.body);
          localStorage.setItem("user", JSON.stringify(d.body));
        }, error => {
          console.log(error);
        });
    }
    console.log(this.user);


  }
  onSelectFile(event) {
    const file = event.target.files[0];
    console.log(file);
    this.userfile = file;
    const reader = new FileReader();
    this.imagePath = event.target.files;
    reader.readAsDataURL(file);
    reader.onload = (_event) => {
      this.srcimg = reader.result;
      console.log(this.srcimg);
    };
  }
}
