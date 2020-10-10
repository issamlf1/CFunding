import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChercherprjComponent } from './chercherprj.component';

describe('ChercherprjComponent', () => {
  let component: ChercherprjComponent;
  let fixture: ComponentFixture<ChercherprjComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChercherprjComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChercherprjComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
