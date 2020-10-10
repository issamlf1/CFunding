import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/Services/auth.service';
import { Projet } from 'src/models/Projet';
import { User } from 'src/models/User';
import { ProjetService } from 'src/Services/projet.service';
import { Donnation } from 'src/models/Donnation';
import { Problems } from 'src/models/Problems';

@Component({
  selector: 'app-mescontribution',
  templateUrl: './mescontribution.component.html',
  styleUrls: ['./mescontribution.component.scss']
})
export class MescontributionComponent implements OnInit {

  listDonnation : Donnation[]
   user : User;
   projToSignal : any;
   problemText = "";


  constructor(private authService :  AuthService ,private projetSer : ProjetService ) {
this.user=this.authService.giveUser();
    }

  ngOnInit() {
    this.listProjet();
  }


  listProjet(){

     this.projetSer.myDonnation(this.user).subscribe(d => {
      this.listDonnation = d.body as Donnation[]
     console.log(this.listDonnation);

    }, error => {
      console.log(error);

    });


    }

    signalerProb(title : any , first : boolean){
      if (first) {
        this.projToSignal = title;
      }
      else{
        let projet = new Projet( this.projToSignal );
        let probl = new Problems(this.user,this.problemText,projet);
        this.projetSer.Problem(probl).subscribe(
          data => {
          }, error => {
            console.log(error);
          }
        );
      }


    }

}
