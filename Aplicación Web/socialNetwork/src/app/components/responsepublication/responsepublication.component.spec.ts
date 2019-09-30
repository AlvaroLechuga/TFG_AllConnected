import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResponsepublicationComponent } from './responsepublication.component';

describe('ResponsepublicationComponent', () => {
  let component: ResponsepublicationComponent;
  let fixture: ComponentFixture<ResponsepublicationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResponsepublicationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResponsepublicationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
