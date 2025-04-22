import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  user: any = {};
  isEditing = false;
  isLoading = true;
  activeTab = 'info';

  constructor(
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    // Get user ID from route or use logged in user
    const userId = this.route.snapshot.paramMap.get('id') || 'current';
    // this.loadUserData(userId);
  }

  loadUserData(userId: string) {
    // this.isLoading = true;
  //   todo: load user data from service
  }

  toggleEdit() {
    this.isEditing = !this.isEditing;
  }

  saveChanges() {

  }

  changeTab(tab: string) {
    this.activeTab = tab;
  }
}
