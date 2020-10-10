import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Contrepartie} from '../models/Contrepartie';
import { Projet } from 'src/models/Projet';
import { User } from 'src/models/User';
import { Donnation } from 'src/models/Donnation';
import { Problems } from 'src/models/Problems';

@Injectable()
export class ProjetService {

  user : User;

  constructor(private httpClient: HttpClient) {}
  saveProject(formData: FormData) {
    return  this.httpClient.post('http://localhost:8080/projects', formData);
  }
  saveContrepartie(contrepartie: Contrepartie) {
    return this.httpClient.post('http://localhost:8080/contrepartie', contrepartie ,{ observe: 'response'  });
  }

  ListProjets() {
    return this.httpClient.get<Projet[]>('http://localhost:8080/projects', { observe: 'response'  });
  }

  deleteProj(projet : Projet){
    return this.httpClient.post('http://localhost:8080/deleteProj', projet ,{ observe: 'response'  });

  }

  oneProjet(projet : Projet){
    return this.httpClient.post<Projet>('http://localhost:8080/oneProhet', projet ,{ observe: 'response'  });
  }

  myProjets(user : User){

      return this.httpClient.post('http://localhost:8080/filterProjects', user ,{ observe: 'response'  });


  }
  getfilterOne(titre: string , etat: boolean){
    let params = new HttpParams().set('titre', titre).set('etat', String(etat));


    return this.httpClient.get('http://localhost:8080/getfilterone', { params: params });
  }
  getfilterTwo(titre: string , etat: boolean, categorie: string){
    let params = new HttpParams().set('titre', titre).set('etat', String(etat)).set('categorie', categorie);
    return this.httpClient.get('http://localhost:8080/getfilterTwo', { params: params });
  }
  getoneprojet(projet: Projet) {
    return this.httpClient.post('http://localhost:8080/oneProhet', projet ,{ observe: 'response'  });

  }
   // ici
  myDonnation(user : User){
    return this.httpClient.post<Donnation[]>('http://localhost:8080/donnUser',user,
    { observe: 'response'  });
  }

  DonnationByProjet(projet : Projet){
    return this.httpClient.post<number>('http://localhost:8080/donnNbrProj',projet,
    { observe: 'response'  });
  }

  effectuerUnDon(donn : Donnation){
    return this.httpClient.post('http://localhost:8080/donn',donn,
    { observe: 'response'  });
  }

  Problem(prob : Problems){
    return this.httpClient.post('http://localhost:8080/problems',prob,
    { observe: 'response'  });
  }
  saveUserUp(user: User) {
    return  this.httpClient.post('http://localhost:8080/usersupdaa', user,{ observe: 'response'  });
  }

}
