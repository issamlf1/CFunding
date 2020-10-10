import { User } from './User';

import { Projet } from './Projet';

export class Commantaire {


    user ?: User;
    commantaire ?: any;
    projet ?: Projet;


    constructor(user : User,commantaire : any,projet : Projet){

        this.user = user;
        this.commantaire = commantaire;
        this.projet = projet;
    }
}