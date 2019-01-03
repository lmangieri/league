import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserformComponent } from './userform/userform.component';
import { LeagueformComponent} from './leagueform/leagueform.component';

const routes: Routes = [
  {
    path: 'userForm',
    component: UserformComponent
  },
  {
    path: 'leagueForm',
    component: LeagueformComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
