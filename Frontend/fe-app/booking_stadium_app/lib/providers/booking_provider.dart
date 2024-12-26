import 'package:flutter/material.dart';
import '../models/booking_model.dart';
import '../mock_data/booking_mock_data.dart';

class BookingProvider with ChangeNotifier {
  final List<Booking> _bookings = List.from(bookingMockData);

  List<Booking> get bookings => List.unmodifiable(_bookings);

  // Add a new booking
  void addBooking(Booking booking) {
    _bookings.add(booking);
    notifyListeners();
  }

  // Update an existing booking
  void updateBooking(int bookingId, Booking updatedBooking) {
    final index = _bookings.indexWhere((booking) => booking.bookingId == bookingId);
    if (index != -1) {
      _bookings[index] = updatedBooking;
      notifyListeners();
    }
  }

  // Remove a booking
  void removeBooking(int bookingId) {
    _bookings.removeWhere((booking) => booking.bookingId == bookingId);
    notifyListeners();
  }

  // Get bookings by user ID
  List<Booking> getBookingsByUserId(int userId) {
    return _bookings.where((booking) => booking.userId == userId).toList();
  }

  // Check availability of a field for a specific time
  bool isFieldAvailable(int fieldId, DateTime date, DateTime startTime, DateTime endTime) {
    return !_bookings.any((booking) =>
    booking.fieldId == fieldId &&
        booking.bookingDate == date &&
        !(endTime.isBefore(booking.startTime) || startTime.isAfter(booking.endTime)));
  }
}
