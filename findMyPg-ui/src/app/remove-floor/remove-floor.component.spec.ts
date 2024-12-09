import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RemoveFloorComponent } from './remove-floor.component';

describe('RemoveFloorComponent', () => {
  let component: RemoveFloorComponent;
  let fixture: ComponentFixture<RemoveFloorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RemoveFloorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RemoveFloorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
