import 'area_model.dart';
import 'price_model.dart';

class Field {
  final int id;
  final String name;
  final String? address;
  final String? description;
  final String? phoneNumber;
  final int? areaId;
  final int? size;
  final double? rating;
  final int? status;
  final Area? area;
  final List<Price>? prices;

  Field({
    required this.id,
    required this.name,
    this.address,
    this.description,
    this.phoneNumber,
    this.areaId,
    this.size,
    this.rating,
    this.status,
    this.area,
    this.prices,
  });

  // Factory method to parse JSON and map it to the Field object.
  factory Field.fromJson(Map<String, dynamic> json) {
    return Field(
      id: json['field_id'] ?? 0,
      name: json['name'] ?? '',
      address: json['address'],
      description: json['description'],
      phoneNumber: json['phone_number'],
      areaId: json['area_id'],
      size: json['size'],
      rating: json['rating']?.toDouble(),
      status: json['status'],
      area: json['area'] != null ? Area.fromJson(json['area']) : null,
      prices: json['prices'] != null
          ? (json['prices'] as List).map((p) => Price.fromJson(p)).toList()
          : null,
    );
  }

  // Convert Field object to JSON, including the Area reference and Price list.
  Map<String, dynamic> toJson() {
    return {
      'field_id': id,
      'name': name,
      'address': address,
      'description': description,
      'phone_number': phoneNumber,
      'area_id': areaId,
      'size': size,
      'rating': rating,
      'status': status,
      'area': area?.toJson(),
      'prices': prices?.map((p) => p.toJson()).toList(),
    };
  }
}
