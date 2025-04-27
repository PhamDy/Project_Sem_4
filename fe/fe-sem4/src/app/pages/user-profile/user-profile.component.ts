import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {AreaServicesService} from '../../services/area-services.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  user: any = {};

  isLoading = true;

  bookingDetails: any[] = [];
  bookingDate: string = '';
  areaDetail: any;
  isDetailsVisible: boolean = false;
  img: any = 'https://imgur.com/JMP9KcJ.png';

  constructor(
    private route: ActivatedRoute,
    private areaService: AreaServicesService,
  ) {}


  ngOnInit() {

    const stored = localStorage.getItem('bookingDetails');
    if (stored) {
      const parsed = JSON.parse(stored);
      this.bookingDate = parsed.date;
      this.bookingDetails = parsed.details;
      const listIdfield = this.bookingDetails.map(item => item.fieldId)
      const idArea = listIdfield[0];
      this.getAreaById(idArea);
      console.log(this.bookingDate);
      console.log('Ngày lưu:', this.bookingDate);
      console.log('Chi tiết booking:', this.bookingDetails);
    } else {
      console.log('Không tìm thấy dữ liệu trong localStorage.');
    }

    // Get user ID from route or use logged in user
    const userId = this.route.snapshot.paramMap.get('id') || 'current';
    // this.loadUserData(userId);
  }

  toggleDetails() {
    this.isDetailsVisible = !this.isDetailsVisible; // Lật lại trạng thái hiển thị
  }

  loadUserData(userId: string) {
    // this.isLoading = true;
  //   todo: load user data from service
  }
  isEditing = false;
  toggleEdit() {
    this.isEditing = !this.isEditing;
  }

  saveChanges() {

  }

  activeTab = 'info';

  changeTab(tab: string) {
    this.activeTab = tab;
  }

  getAreaById(id: string) {
    this.areaService.getDetailById(id).subscribe((data) => {
      if (data) {
        this.areaDetail = data;
        console.log("data",this.areaDetail);
      }
    });
  }

}
