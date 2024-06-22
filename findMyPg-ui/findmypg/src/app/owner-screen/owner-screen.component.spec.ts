import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnerScreenComponent } from './owner-screen.component';

describe('OwnerScreenComponent', () => {
  let component: OwnerScreenComponent;
  let fixture: ComponentFixture<OwnerScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [OwnerScreenComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OwnerScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
