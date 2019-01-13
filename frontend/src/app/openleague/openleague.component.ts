import { Component, OnInit } from '@angular/core';
import { RestService } from '../rest.service';

@Component({
  selector: 'app-openleague',
  templateUrl: './openleague.component.html',
  styleUrls: ['./openleague.component.scss']
})
export class OpenleagueComponent implements OnInit {
  currentLeagueId : number;

  constructor(private rService: RestService) { }

  ngOnInit() {
    // if this.rService.getCurrentLeagueId() has a value
    // it means that it was set after league's creation
    // so user will be redirected imediatelly to the latest league created
    this.currentLeagueId = this.rService.getCurrentLeagueId();

    // below line makes that if user go to create user and comes back to 
    // openleague, he will be able to choose which league he wants to open...
    this.rService.setCurrentLeagueId(null);
  }

}
