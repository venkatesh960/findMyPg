import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { OwnerServiceService } from '../owner-service.service';
import { MatDialog } from '@angular/material/dialog';
import { CustomDialogComponent } from '../custom-dialog/custom-dialog.component';

@Component({
  selector: 'app-addbuilding',
  templateUrl: './addbuilding.component.html',
  styleUrls: ['./addbuilding.component.scss']
})
export class AddbuildingComponent implements OnInit {
  myForm: FormGroup;
  pgTypeArray = ['Girls', 'Boys', 'Co-Living'];
  selectedOption: any;
  ownerId: any;
  buildingId: any;
  selectedFile: File | null = null;

  constructor(
    private dialog: MatDialog,
    private ownerService: OwnerServiceService,
    private formBuilder: FormBuilder,
    private httpClient: HttpClient,
    private router: Router
  ) {
    this.myForm = this.formBuilder.group({
      'pgType': ['', Validators.required],
      'location': ['', Validators.required],
      'pgName': ['', Validators.required],
      'numberofFloors': ['', Validators.required],
      'file': [null, Validators.required]
    });
  }

  ngOnInit(): void {
    this.ownerId = this.ownerService.getOwner().id;
    console.log('This is owner Id in addBuilding components', this.ownerId);
  }

  onReset() {
    this.myForm.reset();
  }

  addBuilding() {
    const buildingData = {
      'id': this.ownerId,
      'pgType': this.myForm.get('pgType')?.value,
      'location': this.myForm.get('location')?.value,
      'pgName': this.myForm.get('pgName')?.value,
      'numberofFloors': this.myForm.get('numberofFloors')?.value,
    };

    const formData: FormData = new FormData();
    formData.append('building', JSON.stringify(buildingData));
    if (this.selectedFile) {
      formData.append('file', this.selectedFile, this.selectedFile.name);
    }

    this.httpClient.post('api/findmypg/building/addBuilding', formData).subscribe((response: any) => {
      if (response != null) {
        this.buildingId = response.id;
        this.openCustomDialog(`Building ${buildingData.pgName} Added Successfully`);
      } else {
        console.log('something went wrong while adding building', response);
      }
    });
  }

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
    }
  }

  openCustomDialog(message: string): void {
    const dialogRef = this.dialog.open(CustomDialogComponent, {
      data: { message, config: { okLabel: 'OK' } },
      width: '500px',
      minHeight: '20px',
      disableClose: true,
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      console.log('Dialog result:', result);
      if (result) {
        this.router.navigate(['/addFloor']);
      }
    });
  }
}




// import { HttpClient } from '@angular/common/http';
// import { Component, OnInit } from '@angular/core';
// import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import { Router } from '@angular/router';
// import { OwnerServiceService } from '../owner-service.service';
// import { CustomDialogComponent } from '../custom-dialog/custom-dialog.component';
// import { MatDialog } from '@angular/material/dialog';

// @Component({
//   selector: 'app-addbuilding',
//   templateUrl: './addbuilding.component.html',
//   styleUrls: ['./addbuilding.component.scss']
// })
// export class AddbuildingComponent implements OnInit {

//   myForm: FormGroup;
//   pgTypeArray = ['Girls', 'Boys', 'Co-Living'];
//   selectedOption: any;
//   ownerId: any;
//   buildingId: any;

//   constructor(
//     private dialog: MatDialog,
//     private ownerService: OwnerServiceService,
//     private formBuilder: FormBuilder,
//     private httpClient: HttpClient,
//     private router: Router
//   ) {
//     this.myForm = this.formBuilder.group({
//       'pgType': ['', Validators.required],
//       'location': ['', Validators.required],
//       'pgName': ['', Validators.required],
//       'numberofFloors': ['', Validators.required],
//     });
//   }

//   ngOnInit(): void {
//     this.ownerId = this.ownerService.getOwner().id;
//     console.log('This is owner Id in addBuilding components', this.ownerId);
//   }

//   onReset() {
//     this.myForm.reset();
//   }

//   addBuilding() {
//     this.openDialog();
//   }

//   openDialog(): void {
//     const dialogRef = this.dialog.open(CustomDialogComponent, {
//       width: '40vw',
//       data: { message: 'Do you want to add this building?' }
//     });

//     dialogRef.afterClosed().subscribe(result => {
//       console.log('The dialog was closed');
//       console.log('Dialog result:', result);
//       if (result) {
//         const buildingData = {
//           'id': this.ownerId,
//           'pgType': this.myForm.get('pgType')?.value,
//           'location': this.myForm.get('location')?.value,
//           'pgName': this.myForm.get('pgName')?.value,
//           'numberofFloors': this.myForm.get('numberofFloors')?.value
//         };

//         this.httpClient.post('api/findmypg/building/addBuilding', buildingData).subscribe((response: any) => {
//           if (response != null) {
//             this.buildingId = response.id;
//             this.dialog.open(CustomDialogComponent, {
//               width: '40vw',
//               data: { message: `Building "${buildingData.pgName}" Added Successfully` }
//             }).afterClosed().subscribe(() => {
//               this.router.navigate(['/addFloor']);
//             });
//           } else {
//             console.log('something went wrong while adding building', response);
//           }
//         });
//       }
//     });
//   }
// }

// import { HttpClient } from '@angular/common/http';
// import { Component, OnInit } from '@angular/core';
// import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import { Router } from '@angular/router';
// import { OwnerServiceService } from '../owner-service.service';
// import { CustomDialogComponent } from '../custom-dialog/custom-dialog.component';
// import { MatDialog } from '@angular/material/dialog';
// import { MatSnackBar } from '@angular/material/snack-bar';

// @Component({
//   selector: 'app-addbuilding',
//   templateUrl: './addbuilding.component.html',
//   styleUrls: ['./addbuilding.component.scss']
// })
// export class AddbuildingComponent implements OnInit {

//   myForm: FormGroup;
//   pgTypeArray = ['Girls', 'Boys', 'Co-Living'];
//   selectedOption: any;
//   ownerId: any;
//   buildingId: any;

//   constructor(
//     private dialog: MatDialog,
//     private ownerService: OwnerServiceService,
//     private formBuilder: FormBuilder,
//     private httpClient: HttpClient,
//     private router: Router,
//     private snackBar: MatSnackBar // Inject MatSnackBar
//   ) {
//     this.myForm = this.formBuilder.group({
//       'pgType': ['', Validators.required],
//       'location': ['', Validators.required],
//       'pgName': ['', Validators.required],
//       'numberofFloors': ['', Validators.required],
//     });
//   }

//   ngOnInit(): void {
//     this.ownerId = this.ownerService.getOwner().id;
//     console.log('This is owner Id in addBuilding components', this.ownerId);
//   }

//   onReset() {
//     this.myForm.reset();
//   }

//   addBuilding() {
//     this.openDialog();
//   }

//   openDialog(): void {
//     const dialogRef = this.dialog.open(CustomDialogComponent, {
//       width: '40vw',
//       data: { message: 'Do you want to add this building?' }
//     });

//     dialogRef.afterClosed().subscribe(result => {
//       console.log('The dialog was closed');
//       console.log('Dialog result:', result);
//       if (result) {
//         const buildingData = {
//           'id': this.ownerId,
//           'pgType': this.myForm.get('pgType')?.value,
//           'location': this.myForm.get('location')?.value,
//           'pgName': this.myForm.get('pgName')?.value,
//           'numberofFloors': this.myForm.get('numberofFloors')?.value
//         };

//         this.httpClient.post('api/findmypg/building/addBuilding', buildingData).subscribe((response: any) => {
//           if (response != null) {
//             this.buildingId = response.id;
//             this.snackBar.open(`Building "${buildingData.pgName}" Added Successfully`, 'Close', {
//               duration: 3000
//             });
//             this.router.navigate(['/addFloor']);
//           } else {
//             this.snackBar.open('Something went wrong while adding the building', 'Close', {
//               duration: 3000
//             });
//             console.log('something went wrong while adding building', response);
//           }
//         });
//       }
//     });
//   }
// }


