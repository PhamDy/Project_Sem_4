import 'package:flutter/material.dart';
import '../mock_data/booking_mock_data.dart';
import '../models/user_model.dart';
import '../mock_data/user_mock_data.dart';

class UserProvider with ChangeNotifier {
  User? _user;

  User? get user => _user;

  // Method to load user data by user ID
  void loadUser(int userId) {
    final userData = userMockData.firstWhere(
          (user) => user['user_id'] == userId,
      orElse: () => {},
    );
    if (userData.isNotEmpty) {
      _user = User.fromJson(userData);
      notifyListeners();
    }
  }

  // Example method to update user data
  void updateUser(String name, String address, String phoneNumber) {
    if (_user != null) {
      _user = _user!.copyWith(
        name: name,
        address: address,
        phoneNumber: phoneNumber,
      );
      notifyListeners();
    }
  }

  List<Map<String, dynamic>> getUserBookings(int userId) {
    // Filter bookings where the user_id matches
    return bookingMockData.where((booking) => booking['user_id'] == userId).toList();
  }
}
