import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserformComponent } from './userform/userform.component';
import { LeagueformComponent} from './leagueform/leagueform.component';
import { RankingComponent } from './ranking/ranking.component';

const routes: Routes = [
  {
    path: 'userForm',
    component: UserformComponent
  },
  {
    path: 'leagueForm',
    component: LeagueformComponent
  },
  {
    path: 'rankingForm',
    component: RankingComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
