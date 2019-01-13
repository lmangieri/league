import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RestService } from '../rest.service';
import { Router } from '@angular/router';

export class Error {
  status : string;
  errorCode : string;
  message : string;

  clean() {
    this.status = '';
    this.errorCode = '';
    this.message = '';
  }

  assign (error : Error) {
    this.errorCode = error.errorCode; 
    this.message = error.message;
    this.status = error.status;
  }
}

export class LeagueTypeRepresentation {
  list : LeagueType[];
}

export class LeagueType {
  name : string;
  leagueTypeId : number;
}

export class CreateLeagueDTO {
  nicks : string[];
  leagueTypeId : number;
}

export class LeagueRepresentation {
  leagueid : number;
  listRound : Round[];
}

export class Round {
  leagueid : number;
  nick1 : string;
  score1 : number;
  nick2 : string;
  score2 : number;
  order : number;
}

@Component({
  selector: 'app-leagueform',
  templateUrl: './leagueform.component.html',
  styleUrls: ['./leagueform.component.scss']
})

export class LeagueformComponent implements OnInit {
  leagueTypeRepresentation : LeagueTypeRepresentation;
  

  errorIsValidNick : Error;
  errorCreateLeague : Error;


  leagueForm : FormGroup;
  restService: RestService;
  environmentName = environment.name;

  listNicks: string[];

  constructor(private fb: FormBuilder, private rService: RestService, private router: Router) { }

  ngOnInit() {
    this.leagueTypeRepresentation = new LeagueTypeRepresentation();
    this.leagueTypeRepresentation.list = [];

    this.listNicks = [];  
    this.restService = this.rService;

    this.restService.getLeagueType().subscribe(
      (data : LeagueTypeRepresentation) => {
        this.leagueTypeRepresentation = data;
        console.info('this.leagueTypeRepresentation');
        console.info(this.leagueTypeRepresentation);
      }
    );

    this.leagueForm = this.fb.group({
      nick : '',
      leagueTypeId : 0
    });

    this.errorIsValidNick = new Error();
    this.errorIsValidNick.errorCode = '';
    this.errorIsValidNick.message = '';
    this.errorIsValidNick.status = '';

    this.errorCreateLeague= new Error();
    this.errorCreateLeague.errorCode = '';
    this.errorCreateLeague.message = '';
    this.errorCreateLeague.status = '';


  }

  addNick() {

    if(this.leagueForm.value.nick == '') {
      this.errorIsValidNick.message = 'Nick is empty';
      return 1;
    }

    if(!this.listNicks.includes(this.leagueForm.value.nick)) {
      this.restService.isValidNick(this.leagueForm.value.nick)
      .subscribe(
        (data) => {
          if(data == true) {
            this.listNicks.push(this.leagueForm.value.nick);
            this.errorIsValidNick.clean();
          } else if (data == false){
            this.errorIsValidNick.message = 'Nick not found '+this.leagueForm.value.nick;
          }
        },(err) => {
          this.errorIsValidNick.assign(err.error);
        }
      );
    } else {
      this.errorIsValidNick.message = 'This nick is already added '+this.leagueForm.value.nick;
    }
    return 1;
  }

  cleans() {
    this.listNicks = [];
  }

  createLeague() {
    var createLeagueDTO = new CreateLeagueDTO();
    createLeagueDTO.nicks = this.listNicks;
    createLeagueDTO.leagueTypeId = this.leagueForm.value.leagueTypeId;
    this.restService.createLeague(createLeagueDTO).subscribe(
       (data : LeagueRepresentation) => {
        this.errorCreateLeague.clean();

        this.restService.setCurrentLeagueId(data.leagueid);
        this.router.navigateByUrl('/openleague');
       },
       (err) => {
        this.errorCreateLeague.assign(err.error);
      }

    );

    return true;
  }
}