import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {BookingServicesService} from '../../services/booking-services.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.css'
})
export class PaymentComponent {
  bookingDetails: any[] = [];
  userInfo = { name: '', phone: '', email: '' };
  paymentMethod = 'VNPay';
  totalPriceOrder:any;
  urlPayment:any;
  constructor(private route: ActivatedRoute,
              private bookingService: BookingServicesService,) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      if (params['selectedFields']) {
        this.bookingDetails = JSON.parse(params['selectedFields']);
      }
      console.log(this.bookingDetails);
    });
  }

  createOrder(CreateBookingRequest: any) {
    this.bookingService.createBooking(CreateBookingRequest).subscribe(
      (res) => {
        console.log("Dâ ",res)
        this.urlPayment = res.url
        console.log("is",this.urlPayment);
        if (this.urlPayment) {
          window.location.href = this.urlPayment;
        } else {
          console.error('Không có URL thanh toán hợp lệ!');
        }
      },
      (error) => {
        console.error('Error fetching stadium data:', error);
      }
    )
  }

  goBack() {
    window.history.back();
  }

  removeField(index: number) {
    this.bookingDetails.splice(index, 1); // Xóa sân khỏi danh sách
  }


  getTotalPrice(): number {
    const totalPrice = this.bookingDetails.reduce((total, field) => {
      return total + field.amount;
    }, 0);
    this.totalPriceOrder = totalPrice;// Giảm 50%
    return this.totalPriceOrder;
  }


  confirmPayment() {
    // if (!this.userInfo.name || !this.userInfo.phone || !this.userInfo.email) {
    //   alert('Vui lòng nhập đầy đủ thông tin!');
    //   return;
    // }
    // if (!this.paymentMethod) {
    //   alert('Vui lòng chọn phương thức thanh toán!');
    //   return;
    // }
    const createBookingPayload = {
      bookingId: null, // hoặc để backend tự sinh nếu không cần truyền
      userId: null, // lấy từ token/localStorage nếu cần
      totalPrice: this.totalPriceOrder, // bạn tự định nghĩa hàm tính tổng
      paymentStatus: 'PENDING',
      paymentMethod: 1,
      bookingAccessory: [
        // { accessoryId: 1, quantity: 2 },
      ],
      bookingReferees: [
        // { refereeId: 3 },
      ],
      bookingDetails: this.bookingDetails.map(item => ({
        bookingDate: item.date,
        timeFrame: item.timeFrame ?? null,
        fieldTypeId: item.fieldId,
        quantity: item.quantity
      })),
      bookingPeriod: [
        // { refereeId: 3 },
      ],
    };
    this.createOrder(createBookingPayload);
    // alert(`Thanh toán thành công qua ${this.paymentMethod} với tổng tiền: ${this.getTotalPrice()} VND`);
  }
}
