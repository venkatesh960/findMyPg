import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VacantStudentComponent } from './vacant-student.component';

describe('VacantStudentComponent', () => {
  let component: VacantStudentComponent;
  let fixture: ComponentFixture<VacantStudentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VacantStudentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VacantStudentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
