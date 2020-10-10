import { Component, OnInit } from '@angular/core';
import { ProjetService } from 'src/Services/projet.service';
import { Projet } from 'src/models/Projet';
import { Router, NavigationExtras } from '@angular/router';
import { AuthService } from 'src/Services/auth.service';
import { User } from 'src/models/User';

@Component({
  selector: 'app-mesprojets',
  templateUrl: './mesprojets.component.html',
  styleUrls: ['./mesprojets.component.scss']
})
export class MesprojetsComponent implements OnInit {

  listProjets : Projet[];
  user: User;
  constructor(private authService: AuthService ,private projetSer : ProjetService , private router : Router) { }


    ngOnInit() {
    console.log(this.authService.giveUser());
    this.user = this.authService.giveUser();



    this.listProjet();
  }


  delete(projet : Projet){
   console.log(projet)
    this.projetSer.deleteProj(projet).subscribe(d => {


     console.log(d);
     this.listProjet();

   }, error => {
     console.log(error);

   });


  }

  update(title : any){

  //   let navigationExtras: NavigationExtras = {
  //     queryParams: {
  //         "projet": projet
  //     }
  // };
  //   this.router.navigate(["edit"], navigationExtras);
  let url = "edit/"+title;
  this.router.navigate([url]);



   }

  listProjet(){
  //   this.projetSer.ListProjets().subscribe(d => {
  //     this.listProjets = d.body as Projet[]

  //   //  console.log(this.listProjets);

  //  }, error => {
  //    console.log(error);

  //  });


  let user : User;
  this.authService.oneUser(this.user.emailUser).subscribe(data => {
   user = data as User
   this.projetSer.user = user ;
   this.projetSer.myProjets(user).subscribe(d => {
    this.listProjets = d.body as Projet[]


  }, error => {
    console.log(error);

  });
  }, err => {
    console.log(err);
  });







  }

}

