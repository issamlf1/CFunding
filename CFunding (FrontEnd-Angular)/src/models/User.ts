import { Projet } from './Projet';
import { Problems } from './Problems';
import { Donnation } from './Donnation';
import { Commantaire } from './Commantaire';

export class User {
    idUser?: any;
    nomUser?: any;
    preUser?: any;
    emailUser?: any;
    teleUser?: any;
    activation?: boolean;
    password?: any;
  srcimage?: any;
    role?: string;
    projets?: Projet[];
    problems?: Problems[];
    donnations ?: Donnation[];
    commantaires ?: Commantaire[];

    constructor(nomUser?: any , preUser?: any, emailUser?: any, teleUser?: any,
                password?: any, role?: any,idUser?: any) {
                    this.nomUser = nomUser;
                    this.preUser = preUser;
                    this.emailUser = emailUser;
                    this.teleUser = teleUser;
                    this.password = password;
                    this.role = role;
                    this.idUser=idUser;
    }




}
