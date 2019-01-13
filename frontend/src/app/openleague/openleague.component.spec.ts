import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OpenleagueComponent } from './openleague.component';

describe('OpenleagueComponent', () => {
  let component: OpenleagueComponent;
  let fixture: ComponentFixture<OpenleagueComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OpenleagueComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OpenleagueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
