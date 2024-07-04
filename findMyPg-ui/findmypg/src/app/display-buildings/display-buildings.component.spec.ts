import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayBuildingsComponent } from './display-buildings.component';

describe('DisplayBuildingsComponent', () => {
  let component: DisplayBuildingsComponent;
  let fixture: ComponentFixture<DisplayBuildingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DisplayBuildingsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisplayBuildingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
