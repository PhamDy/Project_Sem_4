import 'field_model.dart';

class Area {
  final int areaId;
  final String name;
  final String? address;
  final double? rating;
  final String? description;
  final int? status;
  final String? phoneNumber;
  final String? email;
  final List<Field>? fields;

  Area({
    required this.areaId,
    required this.name,
    this.address,
    this.rating,
    this.description,
    this.status,
    this.phoneNumber,
    this.email,
    this.fields,
  });

  // Factory method to parse JSON data
  factory Area.fromJson(Map<String, dynamic> json) {
    return Area(
      areaId: json['area_id'] ?? 0,
      name: json['name'] ?? '',
      address: json['address'],
      rating: json['rating']?.toDouble(),
      description: json['description'],
      status: json['status'],
      phoneNumber: json['phone_number'],
      email: json['email'],
      fields: json['fields'] != null
          ? (json['fields'] as List)
          .map((fieldJson) => Field.fromJson(fieldJson))
          .toList()
          : null,
    );
  }

  // Convert object to JSON
  Map<String, dynamic> toJson() {
    return {
      'area_id': areaId,
      'name': name,
      'address': address,
      'rating': rating,
      'description': description,
      'status': status,
      'phone_number': phoneNumber,
      'email': email,
      'fields': fields?.map((field) => field.toJson()).toList(),
    };
  }
}
