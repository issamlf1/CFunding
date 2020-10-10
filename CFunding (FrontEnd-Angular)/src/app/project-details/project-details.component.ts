import { Component, OnInit } from '@angular/core';
import {ProjetService} from '../../Services/projet.service';
import {ActivatedRoute} from '@angular/router';
import {Projet} from '../../models/Projet';
import { Donnation } from 'src/models/Donnation';
import { AuthService } from 'src/Services/auth.service';
import { Contrepartie } from 'src/models/Contrepartie';
import {User} from '../../models/User';

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.scss']
})
export class ProjectDetailsComponent implements OnInit {
  titre: string;
  projet: Projet = new Projet();
  pourcentage : number;
  nbrContr : number;
  user: User;
  mescontri  = new Contrepartie(0," "," ",new Date," "," ")
  Montant: any;
  constructor( private route: ActivatedRoute, private serviceprojet: ProjetService , private authServ : AuthService) {
    this.user= this.authServ.giveUser();
    console.log(this.route.snapshot.params['titre']);
    this.titre = this.route.snapshot.params['titre'];
    this.projet.titre = this.titre;

    this.serviceprojet.oneProjet(this.projet).subscribe(
      d => {
        this.projet = d.body as  Projet;
        this.mescontri = this.projet.contreparties[0];
        console.log(this.projet);
        this.pourcentage = (this.projet.montantActuel / this.projet.montant)*100;

        this.serviceprojet.DonnationByProjet(this.projet).subscribe(data => {
         this.nbrContr = data.body;

        }, error => {
          console.log(error);

        });
      }, error => {
        console.log(error);

      }
    );
  }

  ngOnInit() {

  }

  effectuerDon(){
    console.log(this.Montant)
    this.authServ.oneUser(this.user.emailUser).subscribe(d => {

      let donnation = new Donnation(d.idUser,this.Montant,this.titre)
      this.serviceprojet.effectuerUnDon(donnation).subscribe(
        data => {

         console.log(data);
        }, error => {
          console.log(error);
        }
      );

    }, error => {
      console.log(error);

    });


  }

  effectuerContr(){
    this.authServ.oneUser(this.user.emailUser).subscribe(d => {

      let donnation = new Donnation(d.idUser,this.projet.contreparties[0].montant,this.titre)
      this.serviceprojet.effectuerUnDon(donnation).subscribe(
        data => {

         console.log(data);
        }, error => {
          console.log(error);
        }
      );

    }, error => {
      console.log(error);

    });
  }


}
