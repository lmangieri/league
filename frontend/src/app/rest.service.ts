import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of} from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { UserformComponent } from './userform/userform.component';
import { User } from './userform/userform.component';
import { UserFormError } from './userform/userform.component';
import { environment } from 'src/environments/environment';
import { PlayerScoreInfo, RankingRepresentation } from './ranking/ranking.component';
import { WebDriverLogger } from 'blocking-proxy/built/lib/webdriver_logger';
import { LeagueRepresentation } from './leagueform/leagueform.component';
import { CreateLeagueDTO } from './leagueform/leagueform.component';

import { OnInit } from '@angular/core';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class RestService  implements OnInit {
  currentLeagueId : number;
  httpClient : HttpClient;
  messageSuccess : string;
  
  httpBase = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  ngOnInit() {
    console.info('RestService - ngOnInit was called');
    this.currentLeagueId = 0;
  }

  getCurrentLeagueId() {
    console.info('RestService - getCurrentLeagueId was called');
    console.info(this.currentLeagueId);
    return this.currentLeagueId;
  }

  setCurrentLeagueId(currentLeagueId : number) {
    console.info('RestService - setCurrentLeagueId was called');
    console.info(currentLeagueId);
    this.currentLeagueId = currentLeagueId;
  }

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
    return null;
  }

   getRanking() {
    return this.http.get<RankingRepresentation>(this.httpBase+'/leagueapp/service/league/ranking');
  }

  isValidNick(nick : string) {
    return this.http.get(this.httpBase+'/leagueapp/service/player/isValidNick/'+nick);
  }

  getLeagueType() {
    return this.http.get(this.httpBase+'/leagueapp/service/league/leagueTypes');
  }

  createLeague(createLeagueDTO : CreateLeagueDTO) {
    return this.http.post<LeagueRepresentation>(this.httpBase+'/leagueapp/service/league',createLeagueDTO, httpOptions);
  }
}
