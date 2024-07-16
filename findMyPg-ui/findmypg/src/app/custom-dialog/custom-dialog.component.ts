import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-custom-dialog',
  templateUrl: './custom-dialog.component.html',
  styleUrl: './custom-dialog.component.scss'
})
export class CustomDialogComponent {
  message: string;
  //injecting the message here using mat_dialog_data
    constructor( @Inject(MAT_DIALOG_DATA) public data: { message: string, config?: { okLabel?: string, cancelLabel?: string, showCancel?: boolean } },
    public dialogRef: MatDialogRef<CustomDialogComponent>
    ) {
      this.message = data.message;
    }
  
    onButtonClick(successful: boolean): void {
      this.dialogRef.close(successful);
    }
  

}
