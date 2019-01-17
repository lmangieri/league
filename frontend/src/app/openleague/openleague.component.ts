import { Component, OnInit } from '@angular/core';
import { RestService } from '../rest.service';
import { FormBuilder, FormGroup, Validators,FormArray } from '@angular/forms';

export class LeagueRepresentation {
  leagueid : number;
  listRound : Round[];
}

export class LeagueDTO {
  leagueid : number;
  listRound : Round[];
  closeLeague : boolean;
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
    this.leagueRepresentation = new LeagueRepresentation();
    this.leagueRepresentation.leagueid = 0;
    this.leagueRepresentation.listRound = [];


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
      listRound : this.fb.array([]),
      closeLeague : ''
    });


    console.info('a2');
    var roundList = this.openLeagueForm2.get('listRound') as FormArray;
    console.info('a3');
    if(this.leagueRepresentation.listRound.length > 0) {
      for (var i = 0; i < this.leagueRepresentation.listRound.length; i++) {
        console.info('a4');
        roundList.push(this.createRound(this.leagueRepresentation.listRound[i]));
      }
    }

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
    this.rService.getLeague(this.openLeagueForm.value.leagueId)
    .subscribe(
      (data : LeagueRepresentation) => {
        this.leagueRepresentation = data;
        this.populateOpenLeagueForm2();
        this.currentLeagueId = data.leagueid;
        this.error.clean();
      },(err) => {
        this.error.assign(err.error);
      }
    );
  }

  submit() {
    var leagueDTO = new LeagueDTO();
    leagueDTO.leagueid = this.leagueRepresentation.leagueid;
    leagueDTO.closeLeague = this.openLeagueForm2.get('closeLeague').value;
    var z  = this.openLeagueForm2.get('listRound') as FormArray;
    leagueDTO.listRound = [];
    console.info('z.length => '+z.length);
    for (let i =0 ; i< z.length ; i++) {
      var v = <Round>z.at(i).value;
      //var round = new Round();
      //round.nick1 = v.get('nick1').value;

      console.info(v);
      leagueDTO.listRound.push(v);
    }

    this.rService.updateLeague(leagueDTO).subscribe(
      (data) => {
        console.info('updatedLeague successfully');
        this.error.clean();
      }, (err) => {
        this.error.assign(err.error);
      }
    );
  }

}
