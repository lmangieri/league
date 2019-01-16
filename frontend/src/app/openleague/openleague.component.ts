import { Component, OnInit } from '@angular/core';
import { RestService } from '../rest.service';
import { FormBuilder, FormGroup, Validators,FormArray } from '@angular/forms';

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

@Component({
  selector: 'app-openleague',
  templateUrl: './openleague.component.html',
  styleUrls: ['./openleague.component.scss']
})
export class OpenleagueComponent implements OnInit {
  currentLeagueId : number;
  openLeagueForm: FormGroup;
  openLeagueForm2: FormGroup;
  public roundList: FormArray;

  leagueRepresentation : LeagueRepresentation;

  error : Error;

  constructor(private rService: RestService, private fb: FormBuilder) { }

  ngOnInit() {
    // if this.rService.getCurrentLeagueId() has a value
    // it means that it was set after league's creation
    // so user will be redirected imediatelly to the latest league created
    this.currentLeagueId = this.rService.getCurrentLeagueId();



    this.openLeagueForm = this.fb.group({
      leagueId : ''
    });

    this.error = new Error();
    this.error.message = '';


    // IF this.currentLeagueId...
    // popular o openLeagueForm2
    if(this.currentLeagueId != null) {
      this.goToLeague();
      this.populateOpenLeagueForm2();
    }



    // below line makes that if user go to create user and comes back to 
    // openleague, he will be able to choose which league he wants to open...
    this.rService.setCurrentLeagueId(null);
  }

  populateOpenLeagueForm2() {
    //Não se trata de um formulário estático
    // para cada round... tenho que inicializar uma linha nova >.<
    
    console.info('a1');

    
    this.openLeagueForm2 = this.fb.group ({
      listRound : this.fb.array([])
    });


    console.info('a2');
    var roundList = this.openLeagueForm2.get('listRound') as FormArray;
    console.info('a3');
    console.info(this.leagueRepresentation.listRound);
    for (var i = 0; i < this.leagueRepresentation.listRound.length; i++) {
      console.info('a4');
      roundList.push(this.createRound(this.leagueRepresentation.listRound[i]));
    }
    console.info('a5');

    console.info('size of roundList = ' + roundList.length);
    console.info('content of listROundon openLeagueForm2 = ' + this.openLeagueForm2.get('listRound'));
  }


  createRound(round : Round): FormGroup {
    return this.fb.group({
      leagueid: [round.leagueid],
      nick1: [round.nick1],
      nick2: [round.nick2],
      score1: [round.score1],
      score2: [round.score2],
      order: [round.order]
    });
  }

  goToLeague() {
    this.openLeagueForm.value.leagueId;

    this.rService.getLeague(this.openLeagueForm.value.leagueId)
    .subscribe(
      (data : LeagueRepresentation) => {
        this.leagueRepresentation = data;
        this.currentLeagueId = data.leagueid;
        this.error.clean();
        this.populateOpenLeagueForm2();
      },(err) => {
        this.error.assign(err.error);
      }
    );
  }

}
