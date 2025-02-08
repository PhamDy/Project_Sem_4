import 'package:flutter/material.dart';
import '../models/field_model.dart';
import '../mock_data/field_mock_data.dart'; // Assuming mock data for fields is stored here

class FieldProvider with ChangeNotifier {
  // List to hold the fields
  List<Field> _fields = [];
  List<Field> get fields => _fields;

  // Method to load mock data
  void loadMockData() {
    _fields = fieldMockData.map((json) => Field.fromJson(json)).toList();
    notifyListeners();
  }

  // Method to filter fields by area ID
  List<Field> getFieldsByArea(int areaId) {
    return _fields.where((field) => field.areaId == areaId).toList();
  }

  // Search fields by name within a specific area
  List<Field> searchFields(String query, int? areaId) {
    final filteredFields = areaId == null
        ? _fields
        : _fields.where((field) => field.areaId == areaId).toList();

    if (query.isEmpty) {
      return filteredFields;
    }

    return filteredFields
        .where((field) => field.name.toLowerCase().contains(query.toLowerCase()))
        .toList();
  }
}
