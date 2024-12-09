import { HttpClient } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { OwnerServiceService } from '../owner-service.service';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { jsPDF } from 'jspdf';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrl: './report.component.scss'
})
export class ReportComponent implements OnInit {



  studentData: any[] = [];
  ownerId: any;
  isAscending = true;



  navigateToAddStudent(_t61: any) {
    throw new Error('Method not implemented.');
  }
  listofBuildingsArray: string[] = ['All', 'By Building', 'By Student', 'By Floor', 'By Room'];
  selectedHostel = 'All';

  constructor(private httpClient: HttpClient, private dialog: MatDialog, private ownerService: OwnerServiceService) {

  }

  ngOnInit(): void {
    this.ownerId = this.ownerService.getOwner().id;
    this.getAllStudentsReports(this.ownerId);
  }
  filtterByDate() {
    console.log('filter by dates ');
    this.studentData = this.studentData.filter(studentReport => studentReport.joiningDate) // Filter out students without a joiningDate
      .sort((a, b) => {
        const dateA: any = new Date(a.joiningDate);
        const dateB: any = new Date(b.joiningDate);

        if (this.isAscending) {
          // Ascending order
          return dateA - dateB;
        } else {
          // Descending order
          return dateB - dateA;
        }
      });

    // Toggle the flag for the next click
    this.isAscending = !this.isAscending;

  }
  downloadAsPdf() {
    console.log('Download PDF');
    const doc = new jsPDF();
  
    // Add a title
    doc.setFontSize(18);
    doc.setFont('helvetica', 'bold');
    doc.text('Student Data', 14, 20);
  
    // Set font size for table content
    doc.setFontSize(12);
    const rowHeight = 12;
    const columnWidths = [50, 50, 60]; // Adjusted column widths
    const padding = 4;
  
    // Table Header
    doc.setFont('helvetica', 'bold');
    const headers = ['First Name', 'Last Name', 'Mobile Number'];
  
    // Set header background and text color
    doc.setFillColor(30, 144, 255); // A lighter blue color
    doc.setTextColor(255, 255, 255);
  
    headers.forEach((header, i) => {
      doc.rect(12 + columnWidths.slice(0, i).reduce((a, b) => a + b, 0), 30, columnWidths[i], rowHeight, 'F');
      doc.text(header, 12 + columnWidths.slice(0, i).reduce((a, b) => a + b, 0) + padding, 40, { baseline: 'middle' });
    });
  
    // Loop through the student data and create rows for the table
    let startY = 42;
    this.studentData.forEach((student, index) => {
      // Alternate row colors
      if (index % 2 === 0) {
        doc.setFillColor(230, 230, 230); // Light grey for alternate rows
        doc.rect(12, startY, columnWidths.reduce((a, b) => a + b, 0), rowHeight, 'F');
      }
  
      doc.setTextColor(0, 0, 0); // Black text for rows
      const data = [student.firstName || '', student.lastName || '', student.mobileNumber || ''];
      data.forEach((text, i) => {
        doc.text(text, 12 + columnWidths.slice(0, i).reduce((a, b) => a + b, 0) + padding, startY + rowHeight / 2, {
          baseline: 'middle',
        });
      });
  
      // Draw borders
      let x = 12;
      columnWidths.forEach((width) => {
        doc.rect(x, startY, width, rowHeight);
        x += width;
      });
  
      startY += rowHeight;
    });
  
    // Add footer
    doc.setFontSize(10);
    doc.setFont('helvetica', 'italic');
    doc.text(`Generated on: ${new Date().toLocaleDateString()}`, 12, doc.internal.pageSize.height - 10);
  
    // Save the generated PDF
    doc.save('student_data.pdf');
  }
  


  viewProfile(student: any) {
    this.openMyCustomDialog(student)
  }
  private openMyCustomDialog(message: any): void {
    const dialogRef = this.dialog.open(MyCustomDialogComponent, {
      data: { message, config: { okLabel: 'OK' } },
      width: '500px',
      minHeight: '20px',
      disableClose: true,
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        // this.router.navigate(['/home-page']);
      }
    });
  }
  getAllStudentsReports(ownerId: any) {

    this.httpClient.get(`/api/findmypg/reports/getAllStudentReports?ownerId=${ownerId}`).subscribe(response => {
      if (response != null && Array.isArray(response)) {
        console.log('Response from API ', response);
        this.studentData = response;
      }
    })
  }


  onItemSelected(value: string) {
    console.log('Selected Hostel:', value);
  }

}


@Component({
  templateUrl: './custom-dialog.html',
  styleUrls: ['./custom-dialog.scss']
})
export class MyCustomDialogComponent {
  student: any;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { message: string, config?: { okLabel?: string } },
    public dialogRef: MatDialogRef<MyCustomDialogComponent>
  ) {
    this.student = data.message;
  }

  public onButtonClick(successful: boolean): void {
    this.dialogRef.close(successful);
  }
}
