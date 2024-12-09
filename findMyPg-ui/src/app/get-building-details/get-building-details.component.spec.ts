import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetBuildingDetailsComponent } from './get-building-details.component';

describe('GetBuildingDetailsComponent', () => {
  let component: GetBuildingDetailsComponent;
  let fixture: ComponentFixture<GetBuildingDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GetBuildingDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetBuildingDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
