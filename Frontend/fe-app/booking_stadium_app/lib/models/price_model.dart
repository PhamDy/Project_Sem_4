class Price {
  final int id;
  final String? priceFrom;
  final String? priceTo;
  final double? price;
  final int? fieldId;
  final int? status;

  Price({
    required this.id,
    this.priceFrom,
    this.priceTo,
    this.price,
    this.fieldId,
    this.status,
  });

  // Factory method to parse JSON data into a Price object.
  factory Price.fromJson(Map<String, dynamic> json) {
    return Price(
      id: json['price_id'] ?? 0,
      priceFrom: json['price_from'],
      priceTo: json['price_to'],
      price: json['price'] != null ? double.parse(json['price'].toString()) : null,
      fieldId: json['field_id'],
      status: json['status'],
    );
  }

  // Convert Price object to JSON.
  Map<String, dynamic> toJson() {
    return {
      'price_id': id,
      'price_from': priceFrom,
      'price_to': priceTo,
      'price': price,
      'field_id': fieldId,
      'status': status,
    };
  }
}
