import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {
  searchQuery: string = '';

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.searchQuery = params['query'] || '';
      console.log('Received search query:', this.searchQuery);
      this.performSearch(this.searchQuery);
    });
  }

  // Ví dụ hàm tìm kiếm hoặc xử lý logic
  performSearch(query: string): void {
    if (query) {
      // Thực hiện logic tìm kiếm với query
      console.log('Performing search with query:', query);
    } else {
      console.log('No search query provided.');
    }
  }
}
