import { Component } from '@angular/core';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-toast',
  templateUrl: './toast.component.html',
  styleUrls: ['./toast.component.scss'],
})
export class ToastComponent {
  constructor(private messageService: MessageService) {}

  showSuccess() {
    this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Message Content', life: 3000 });
    this.addAnimation();
  }

  showInfo() {
    this.messageService.add({ severity: 'info', summary: 'Info', detail: 'Message Content', life: 3000 });
    this.addAnimation();
  }

  showWarn() {
    this.messageService.add({ severity: 'warn', summary: 'Warn', detail: 'Message Content', life: 3000 });
    this.addAnimation();
  }

  showError() {
    this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Message Content', life: 3000 });
    this.addAnimation();
  }

  showContrast() {
    this.messageService.add({ severity: 'contrast', summary: 'Contrast', detail: 'Message Content', life: 3000 });
    this.addAnimation();
  }

  showSecondary() {
    this.messageService.add({ severity: 'secondary', summary: 'Secondary', detail: 'Message Content', life: 3000 });
    this.addAnimation();
  }

  addAnimation() {
    const toasts = document.querySelectorAll('.p-toast-message');
    toasts.forEach((toast) => {
      toast.classList.add('toast-enter');
      toast.addEventListener('animationend', () => {
        toast.classList.remove('toast-enter');
        toast.classList.add('toast-exit');
      });
    });
  }
}
