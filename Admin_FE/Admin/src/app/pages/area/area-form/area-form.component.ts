import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { Area } from 'src/app/models/area';
import { AreaService } from '../area.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-area-form',
  templateUrl: './area-form.component.html',
  styleUrls: ['./area-form.component.css'],
})
export class AreaFormComponent implements OnInit {
  areaCreate = new Area();
  // areaForm: FormGroup = new FormGroup({});
  img: File | null = null;
  imgPreview: SafeUrl | null = null;
  isLoading: boolean = false;

  districts: any[] = [];

  constructor(
    private areaService: AreaService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private sanitizer: DomSanitizer,
    private fb: FormBuilder
  ) {}

  myForm: FormGroup = new FormGroup({});

  ngOnInit() {
    this.myForm = this.fb.group({
      name: ['', Validators.required],
      address: ['', Validators.required],
      description: [''],
      phoneNumber: ['', [Validators.required, Validators.pattern('^[0-9]+$')]],
      email: ['', [Validators.required, Validators.email]],
      district: ['', Validators.required],
    });
    this.areaService.getDistricts().subscribe(
      (data) => {
        this.districts = data;
      },
      (error) => {
        console.error('Error fetching districts', error);
      }
    );
  }

  reloadComponent() {
    this.router
      .navigateByUrl('/area', { skipLocationChange: true })
      .then(() => {
        this.router.navigate([this.router.url]);
      });
  }

  selectedFiles: File[] = [];

  onFileSelect(event: any) {
    this.selectedFiles = Array.from(event.target.files);
  }

  onSubmit() {
    console.log(this.myForm);
    if (this.myForm.valid) {
      const formData = new FormData();
      Object.keys(this.myForm.value).forEach((key) => {
        formData.append(key, this.myForm.value[key]);
      });
      for (const file of this.selectedFiles) {
        formData.append('files', file);
      }

      this.areaService.createArea(formData).subscribe({
        next: (response) => {
          console.log('Success!', response);
          alert('Form submitted successfully!');
          this.router.navigate(['/area']);
        },
        error: (error) => {
          console.error('Error!', error);
          alert('Form submission failed!');
        }
      });
    } else {
      alert('Please fill all required fields correctly!');
      return;
    }
  } 
}
