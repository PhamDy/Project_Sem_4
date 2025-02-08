import 'package:flutter/material.dart';
import '../models/price_model.dart';
import '../mock_data/price_mock_data.dart';

class PriceProvider with ChangeNotifier {
  // List to hold the prices
  List<Price> _prices = [];
  List<Price> get prices => _prices;

  // Load mock data
  void loadMockData() {
    _prices = priceMockData.map((json) => Price.fromJson(json)).toList();
    notifyListeners();
  }

  // Get prices by field ID
  List<Price> getPricesByFieldId(int fieldId) {
    return _prices.where((price) => price.fieldId == fieldId).toList();
  }
}
