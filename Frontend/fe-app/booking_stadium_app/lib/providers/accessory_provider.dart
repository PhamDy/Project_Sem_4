import 'package:flutter/material.dart';
import '../models/accessory_model.dart';
import '../mock_data/accessory_mock_data.dart';

class AccessoryProvider with ChangeNotifier {
  // List to hold accessories
  List<Accessory> _accessories = [];
  List<Accessory> get accessories => _accessories;

  // Load mock data
  void loadMockData() {
    _accessories = accessoryMockData.map((json) => Accessory.fromJson(json)).toList();
    notifyListeners();
  }

  // Get accessories by area ID
  List<Accessory> getAccessoriesByAreaId(int areaId) {
    return _accessories.where((accessory) => accessory.areaId == areaId).toList();
  }

  // Update accessory status (e.g., reduce quantity after booking)
  void updateAccessoryQuantity(int accessoryId, int newQuantity) {
    final index = _accessories.indexWhere((accessory) => accessory.id == accessoryId);
    if (index != -1) {
      _accessories[index] = _accessories[index].copyWith(quantity: newQuantity);
      notifyListeners();
    }
  }
}
