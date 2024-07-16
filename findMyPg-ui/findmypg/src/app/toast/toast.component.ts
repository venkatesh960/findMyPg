import { Component, OnInit } from '@angular/core';
import { ToastService } from './toast.service';
import { ToastModel } from './toast.model';

@Component({
  selector: 'app-toast',
  templateUrl: './toast.component.html',
  styleUrls: ['./toast.component.scss'],
})
export class ToastComponent implements OnInit {
  toastMessage: any;
  constructor(
    public toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.toastService.toastSubject.subscribe((data:any) => {
      this.toastMessage = data;
    });
  }

  
  
}