import {Injectable} from '@angular/core';
import {map} from 'rxjs/operators';

import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { User } from 'src/models/User';


@Injectable()
export class AuthService {
  email: string;
  username: number;
  exist = false;
  user : User ;
  private jwtToken: string;

  constructor( private httpClient: HttpClient) {}
  authentification(email: string, password: string) {
    this.oneUser(email).subscribe((data)=>{
      this.user = data as User ;
      localStorage.setItem("user",JSON.stringify(this.user));
    })
    return this.httpClient.post('http://localhost:8080/login',{"emailUser":email,"password":password},{ observe: 'response'
    });

  }
  saveToken(jwtToken) {
    this.jwtToken = jwtToken;
    localStorage.setItem('token', jwtToken);
  }

  oneUser(email: any) {
    let params = new HttpParams().set('email', email);
    return this.httpClient.get<User>('http://localhost:8080/user', { params: params });
  }


  listUser(){
    return this.httpClient.get<User[]>('http://localhost:8080/users');
  }

  giveUser(){
    this.user =   JSON.parse(  localStorage.getItem("user"));
    return this.user;
  }
  deconexion(){
    localStorage.clear();
  }

}
