import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from '../models/User';

@Injectable()
export class InscriptionService {
  constructor(private http: HttpClient) {}
  inscription(user: User) {
    console.log('le rolr : ' + user.role);
    return this.http.post('http://localhost:8080/users', user,
        { observe: 'response'  });
  }
}
