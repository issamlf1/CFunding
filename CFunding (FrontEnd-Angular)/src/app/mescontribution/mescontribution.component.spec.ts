import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MescontributionComponent } from './mescontribution.component';

describe('MescontributionComponent', () => {
  let component: MescontributionComponent;
  let fixture: ComponentFixture<MescontributionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MescontributionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MescontributionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
