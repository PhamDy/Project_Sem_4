import 'package:flutter/material.dart';
import '../models/area_model.dart';
import '../mock_data/area_mock_data.dart';

class AreaProvider with ChangeNotifier {
  // List to hold the areas
  List<Area> _areas = [];
  List<Area> _filteredAreas = [];

  // Getters
  List<Area> get areas => _areas;
  List<Area> get filteredAreas => _filteredAreas;

  bool _isLoading = true;
  bool get isLoading => _isLoading;

  String? _errorMessage;
  String? get errorMessage => _errorMessage;

  // Method to load mock data
  void fetchAreas() {
    try {
      _isLoading = true;
      notifyListeners();

      _areas = areaMockData.map((json) => Area.fromJson(json)).toList();
      _filteredAreas = List.from(_areas);

      _isLoading = false;
      notifyListeners();
    } catch (e) {
      _isLoading = false;
      _errorMessage = 'Failed to load areas.';
      notifyListeners();
    }
  }

  // Method to filter areas by query
  void filterAreas(String query) {
    if (query.isEmpty) {
      _filteredAreas = List.from(_areas);
    } else {
      _filteredAreas = _areas
          .where((area) => area.name.toLowerCase().contains(query.toLowerCase()))
          .toList();
    }
    notifyListeners();
  }
}
