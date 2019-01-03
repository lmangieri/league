import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-leagueform',
  templateUrl: './leagueform.component.html',
  styleUrls: ['./leagueform.component.scss']
})
export class LeagueformComponent implements OnInit {
  environmentName = environment.name;
  constructor() { }

  ngOnInit() {
  }

}
