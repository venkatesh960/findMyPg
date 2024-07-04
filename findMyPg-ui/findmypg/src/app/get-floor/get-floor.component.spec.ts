import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetFloorComponent } from './get-floor.component';

describe('GetFloorComponent', () => {
  let component: GetFloorComponent;
  let fixture: ComponentFixture<GetFloorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GetFloorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetFloorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
