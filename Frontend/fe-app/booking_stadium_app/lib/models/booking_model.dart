class Booking {
  final int bookingId;
  final int fieldId;
  final int userId;
  final DateTime bookingDate;
  final DateTime startTime;
  final DateTime endTime;
  final int paymentStatus;
  final int status;
  final int paymentMethod;
  final double totalPrice;

  Booking({
    required this.bookingId,
    required this.fieldId,
    required this.userId,
    required this.bookingDate,
    required this.startTime,
    required this.endTime,
    required this.paymentStatus,
    required this.status,
    required this.paymentMethod,
    required this.totalPrice,
  });

  // Factory method to parse JSON and map it to the Booking object.
  factory Booking.fromJson(Map<String, dynamic> json) {
    return Booking(
      bookingId: json['booking_id'] ?? 0,
      fieldId: json['field_id'] ?? 0,
      userId: json['user_id'] ?? 0,
      bookingDate: DateTime.parse(json['booking_date']),
      startTime: DateTime.parse(json['start_time']),
      endTime: DateTime.parse(json['end_time']),
      paymentStatus: json['payment_status'] ?? 0,
      status: json['status'] ?? 0,
      paymentMethod: json['payment_method'] ?? 0,
      totalPrice: json['total_price']?.toDouble() ?? 0.0,
    );
  }

  // Convert Booking object to JSON.
  Map<String, dynamic> toJson() {
    return {
      'booking_id': bookingId,
      'field_id': fieldId,
      'user_id': userId,
      'booking_date': bookingDate.toIso8601String(),
      'start_time': startTime.toIso8601String(),
      'end_time': endTime.toIso8601String(),
      'payment_status': paymentStatus,
      'status': status,
      'payment_method': paymentMethod,
      'total_price': totalPrice,
    };
  }
}
