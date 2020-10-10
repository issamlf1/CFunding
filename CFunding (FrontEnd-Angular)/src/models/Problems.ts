import { Projet } from './Projet';
import { User } from './User';

export class Problems {
    user ?: User;
    description ?: any;
    projet ?: Projet;

    constructor( user : User,description: any,projet: Projet){
        this.user = user;
        this.description = description;
        this.projet = projet;
    }
}