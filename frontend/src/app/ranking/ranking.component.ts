import { Component, OnInit } from '@angular/core';

import { RestService } from '../rest.service';

export class RankingRepresentation {
  list : PlayerScoreInfo[];
}

export class PlayerScoreInfo {
  name : string;
  nick : string;
  email : string;
  createdDate: string;
  totalGames: number;
  totalVictory: number;
  totalLost: number;
  winrate: string;
}

@Component({
  selector: 'app-ranking',
  templateUrl: './ranking.component.html',
  styleUrls: ['./ranking.component.scss']
})
export class RankingComponent implements OnInit {
  rankingRepresentation : RankingRepresentation;

  playerScoreInfoList : PlayerScoreInfo[];
  restService: RestService;

  constructor(private rService: RestService) { 
    this.rankingRepresentation = new RankingRepresentation();
    this.rankingRepresentation.list = [];
  }

  ngOnInit() {

    this.restService = this.rService;
    this.restService.getRanking()
    .subscribe((data) => {
      console.info('success 116 ');
      this.rankingRepresentation = data;
      console.info(this.rankingRepresentation);
      console.info('success 2 ');
    });
  }
}
