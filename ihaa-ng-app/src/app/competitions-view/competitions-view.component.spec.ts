import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompetitionsViewComponent } from './competitions-view.component';

describe('CompetitionsViewComponent', () => {
  let component: CompetitionsViewComponent;
  let fixture: ComponentFixture<CompetitionsViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompetitionsViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompetitionsViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
