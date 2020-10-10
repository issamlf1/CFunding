import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditProjComponent } from './edit-proj.component';

describe('EditProjComponent', () => {
  let component: EditProjComponent;
  let fixture: ComponentFixture<EditProjComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditProjComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditProjComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
