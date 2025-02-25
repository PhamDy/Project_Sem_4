import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-content-1',
  templateUrl: './content-1.component.html',
  styleUrl: './content-1.component.css'
})
export class Content1Component {
  searchForm: FormGroup;
  districts: string[] = [];

  cityDistrictsMap: { [key: string]: string[] } = {
    'Hà Nội': ['Cầu Giấy', 'Đống Đa', 'Ba Đình'],
    'Đà Nẵng': ['Hải Châu', 'Sơn Trà', 'Liên Chiểu'],
    'TP.Hồ Chí Minh': ['Quận 1', 'Quận 3', 'Quận 7']
  };

  constructor(private fb: FormBuilder,private router: Router) {
    this.searchForm = this.fb.group({
      fieldSize: [''],
      city: [''],
      district: ['']
    });
  }

  onCityChange() {
    const selectedCity = this.searchForm.get('city')?.value;
    this.districts = this.cityDistrictsMap[selectedCity] || [];
    this.searchForm.patchValue({ district: '' }); // Reset quận/huyện khi đổi tỉnh
  }

  search() {
    const { fieldSize, city, district } = this.searchForm.value;

    this.router.navigate(['/booking'], {
      queryParams: {
        fieldSize: fieldSize || '',
        city: city || '',
        district: district || ''
      }
    });
  }

}
