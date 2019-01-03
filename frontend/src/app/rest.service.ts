import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of} from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { UserformComponent } from './userform/userform.component';
import { User } from './userform/userform.component';
import { UserFormError } from './userform/userform.component';
import { environment } from 'src/environments/environment';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class RestService {
  httpClient : HttpClient;
  messageSuccess : string;
  
  httpBase = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  registerUser(userFormValue : string, userFormError : UserFormError) {
    this.http.post<User>(this.httpBase+'/leagueapp/service/player',userFormValue,httpOptions )
    .subscribe((data : User) => {
        userFormError.clean();
      },
      (err) => {
        var userFormError2 = err.error;
        userFormError.assign(userFormError2);
      }
    );
    return 1;
  }
}
