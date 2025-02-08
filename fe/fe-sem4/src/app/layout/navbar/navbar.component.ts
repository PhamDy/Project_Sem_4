import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'] // sửa styleUrl thành styleUrls
})
export class NavbarComponent {
  searchQuery: string = '';
  filteredResults: any[] = [];
  finalSearch: string = '';

  fakeData: any[] = [
    { name: 'Sân bóng Thanh Xuân', address: 'Thanh Xuân, Hà Nội' },
    { name: 'Sân bóng Mỹ Đình', address: 'Mỹ Đình, Hà Nội' },
    { name: 'Sân bóng Cầu Giấy', address: 'Cầu Giấy, Hà Nội' },
    { name: 'Sân bóng Hai Bà Trưng', address: 'Hai Bà Trưng, Hà Nội' },
    { name: 'Sân bóng Hoàng Mai', address: 'Hoàng Mai, Hà Nội' },
    { name: 'Sân bóng Long Biên', address: 'Long Biên, Hà Nội' },
    { name: 'Sân bóng Tây Hồ', address: 'Tây Hồ, Hà Nội' },
    { name: 'Sân bóng Đống Đa', address: 'Đống Đa, Hà Nội' },
    { name: 'Sân bóng Ba Đình', address: 'Ba Đình, Hà Nội' },
    { name: 'Sân bóng Hoàn Kiếm', address: 'Hoàn Kiếm, Hà Nội' },
    { name: 'Sân bóng Hà Đông', address: 'Hà Đông, Hà Nội' },
    { name: 'Sân bóng Bắc Từ Liêm', address: 'Bắc Từ Liêm, Hà Nội' },
    { name: 'Sân bóng Nam Từ Liêm', address: 'Nam Từ Liêm, Hà Nội' },
    { name: 'Sân bóng Gia Lâm', address: 'Gia Lâm, Hà Nội' },
    { name: 'Sân bóng Thanh Trì', address: 'Thanh Trì, Hà Nội' },
    { name: 'Sân bóng Sơn Tây', address: 'Sơn Tây, Hà Nội' },
    { name: 'Sân bóng Đông Anh', address: 'Đông Anh, Hà Nội' },
    { name: 'Sân bóng Chương Mỹ', address: 'Chương Mỹ, Hà Nội' },
    { name: 'Sân bóng Mê Linh', address: 'Mê Linh, Hà Nội' },
    { name: 'Sân bóng Thạch Thất', address: 'Thạch Thất, Hà Nội' }
  ];

  constructor(private router: Router) {
    this.filteredResults = this.fakeData;
  }

  // Hàm tìm kiếm
  onSearch() {
    if (this.searchQuery.trim() === '') {
      this.filteredResults = []; // Không hiển thị gợi ý khi không nhập gì
    } else {
      this.filteredResults = this.fakeData.filter(item =>
        item.name.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
        item.address.toLowerCase().includes(this.searchQuery.toLowerCase())
      );
    }
    // console.log("fillter",this.fakeData)
  }

  // Hàm chọn gợi ý
  selectPlace(place: any) {
    this.searchQuery = place.name; // Gán tên sân bóng vào ô tìm kiếm
    this.filteredResults = []; // Ẩn gợi ý sau khi chọn
  }

  search() {
    this.finalSearch = this.searchQuery.trim();
    if (this.finalSearch !== '') {
      this.router.navigate(['/booking'], { queryParams: { query: this.finalSearch } });
    }
  }
}
