import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.css'
})
export class PaymentComponent {
  selectedFields: any[] = [];
  userInfo = { name: '', phone: '', email: '' };
  paymentMethod = 'VNPay';

  constructor(private route: ActivatedRoute) {}

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      if (params['selectedFields']) {
        this.selectedFields = JSON.parse(params['selectedFields']);
      }
      console.log(this.selectedFields);
    });
  }

  goBack() {
    window.history.back();
  }

  removeField(index: number) {
    this.selectedFields.splice(index, 1); // Xóa sân khỏi danh sách
  }


  getTotalPrice(): number {
    const totalPrice = this.selectedFields.reduce((total, field) => {
      // Chuyển đổi giá từ string sang number, bỏ dấu chấm nếu có
      const priceNumber = parseFloat(field.price.replace(/\./g, ''));
      return total + priceNumber;
    }, 0);

    return totalPrice * 0.5; // Giảm 50% tổng giá
  }

  confirmPayment() {
    if (!this.userInfo.name || !this.userInfo.phone || !this.userInfo.email) {
      alert('Vui lòng nhập đầy đủ thông tin!');
      return;
    }
    if (!this.paymentMethod) {
      alert('Vui lòng chọn phương thức thanh toán!');
      return;
    }
    alert(`Thanh toán thành công qua ${this.paymentMethod} với tổng tiền: ${this.getTotalPrice()} VND`);
  }
}
