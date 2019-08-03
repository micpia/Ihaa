import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompetitionPanelComponent } from './competition-panel.component';

describe('CompetitionPanelComponent', () => {
  let component: CompetitionPanelComponent;
  let fixture: ComponentFixture<CompetitionPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompetitionPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompetitionPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
