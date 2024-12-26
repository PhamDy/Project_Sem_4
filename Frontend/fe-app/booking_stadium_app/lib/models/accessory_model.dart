class Accessory {
  final int id;
  final String name;
  final String? description;
  final double price;
  final int quantity;
  final int? areaId;
  final double? rating;
  final int? type; // 1 for rent, 2 for sale
  final int? status;
  final int? categoryAccessoryId;

  Accessory({
    required this.id,
    required this.name,
    this.description,
    required this.price,
    required this.quantity,
    this.areaId,
    this.rating,
    this.type,
    this.status,
    this.categoryAccessoryId,
  });

  // Factory method to parse JSON data
  factory Accessory.fromJson(Map<String, dynamic> json) {
    return Accessory(
      id: json['accessory_id'] ?? 0,
      name: json['name'] ?? '',
      description: json['description'],
      price: (json['price'] ?? 0).toDouble(),
      quantity: json['quantity'] ?? 0,
      areaId: json['area_id'],
      rating: json['rating']?.toDouble(),
      type: json['type'],
      status: json['status'],
      categoryAccessoryId: json['category_accessory_id'],
    );
  }

  // Convert object to JSON
  Map<String, dynamic> toJson() {
    return {
      'accessory_id': id,
      'name': name,
      'description': description,
      'price': price,
      'quantity': quantity,
      'area_id': areaId,
      'rating': rating,
      'type': type,
      'status': status,
      'category_accessory_id': categoryAccessoryId,
    };
  }
  // CopyWith method
  Accessory copyWith({
    int? id,
    String? name,
    String? description,
    double? price,
    int? quantity,
    int? areaId,
    double? rating,
    int? type,
    int? status,
    int? categoryAccessoryId,
  }) {
    return Accessory(
      id: id ?? this.id,
      name: name ?? this.name,
      description: description ?? this.description,
      price: price ?? this.price,
      quantity: quantity ?? this.quantity,
      areaId: areaId ?? this.areaId,
      rating: rating ?? this.rating,
      type: type ?? this.type,
      status: status ?? this.status,
      categoryAccessoryId: categoryAccessoryId ?? this.categoryAccessoryId,
    );
  }
}
