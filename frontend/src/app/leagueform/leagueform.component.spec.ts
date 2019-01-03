import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LeagueformComponent } from './leagueform.component';

describe('LeagueformComponent', () => {
  let component: LeagueformComponent;
  let fixture: ComponentFixture<LeagueformComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LeagueformComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LeagueformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
