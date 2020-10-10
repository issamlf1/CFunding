
import { Problems } from './Problems';
import { Donnation } from './Donnation';
import { Commantaire } from './Commantaire';
import { User } from './User';
import { Contrepartie } from './Contrepartie';

export class Projet {

    titre ?: string;
    description ?: any;
    montant ?: number;
    montantActuel ?: any;
    type?: string;
    categorie ?: string;
    etat ?: boolean;
    duree ?: number;
    dateCre ?: any;
    user ?: any;
    jourRestant ? :any;
      srcimage? : any;
    contreparties ?: Contrepartie[];

    constructor(titre ?: any, description ?: any, montant?: any, montantActuel?: any , type?: string, categorie?: string,
                etat?: boolean, duree?: number, dateCre?: any , idUser ?: any , contreparties ?: Contrepartie[] , jourRestant ? :any) {

            this.jourRestant = jourRestant;
            this.titre = titre;
            this.description = description;
            this.montant = montant;
            this.type = type;
            this.montantActuel = montantActuel;
            this.categorie = categorie;
            this.etat = etat;
            this.duree = duree;
            this.dateCre = dateCre;
            this.user = { 'idUser' : idUser};
            this.contreparties = contreparties
    }

}
