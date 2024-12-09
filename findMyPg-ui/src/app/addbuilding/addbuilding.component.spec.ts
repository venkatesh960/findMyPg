import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddbuildingComponent } from './addbuilding.component';

describe('AddbuildingComponent', () => {
  let component: AddbuildingComponent;
  let fixture: ComponentFixture<AddbuildingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddbuildingComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddbuildingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
