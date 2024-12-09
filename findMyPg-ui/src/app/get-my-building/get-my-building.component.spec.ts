import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetMyBuildingComponent } from './get-my-building.component';

describe('GetMyBuildingComponent', () => {
  let component: GetMyBuildingComponent;
  let fixture: ComponentFixture<GetMyBuildingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GetMyBuildingComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetMyBuildingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
