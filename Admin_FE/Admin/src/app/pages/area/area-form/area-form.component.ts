import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { Area } from 'src/app/models/area';
import { AreaService } from '../area.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-area-form',
  templateUrl: './area-form.component.html',
  styleUrls: ['./area-form.component.css']
})
export class AreaFormComponent implements OnInit {

  areaCreate = new Area();
  areaForm: FormGroup = new FormGroup({});
  img: File | null = null;
  imgPreview: SafeUrl | null = null;
  isLoading: boolean = false;


  constructor(
    private areaService: AreaService,
    private formBuider: FormBuilder,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private sanitizer: DomSanitizer
  ) { }

  ngOnInit() {
  }

  prepareFormData(area: Area): FormData {
      const formData = new FormData();
      formData.append('img', this.img);
      formData.append('name', )

  }

  reloadComponent() {
    this.router
      .navigateByUrl('/area', { skipLocationChange: true })
      .then(() => {
        this.router.navigate([this.router.url]);
      });
  }

}
