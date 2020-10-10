import { User } from './User';
import { Projet } from './Projet';

export class Donnation {

    donneur ?: User;
    montant ?: any;
    projet ?: Projet;
    dateDonn ? : any;


    constructor(idUser : any,montant : any,projetTitle : any, dateDonn ? : any ){
       
        this.donneur ={  "idUser": idUser  }
        this.montant = montant;
        this.projet = {"titre" : projetTitle};
        this.dateDonn = dateDonn
    }


}