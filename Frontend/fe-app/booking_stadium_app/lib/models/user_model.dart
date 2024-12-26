class User {
  final int id;
  final String name;
  final String address;
  final int? age;
  final String email;
  final String phoneNumber;

  User({
    required this.id,
    required this.name,
    required this.address,
    this.age,
    required this.email,
    required this.phoneNumber,
  });

  // Factory method to parse JSON data
  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      id: json['user_id'] ?? 0,
      name: json['name'] ?? '',
      address: json['address'] ?? '',
      age: json['age'],
      email: json['email'] ?? '',
      phoneNumber: json['phone_number'] ?? '',
    );
  }

  // Convert object to JSON
  Map<String, dynamic> toJson() {
    return {
      'user_id': id,
      'name': name,
      'address': address,
      'age': age,
      'email': email,
      'phone_number': phoneNumber,
    };
  }

  User copyWith({
    int? id,
    String? name,
    String? address,
    int? age,
    String? email,
    String? phoneNumber,
  }) {
    return User(
      id: id ?? this.id,
      name: name ?? this.name,
      address: address ?? this.address,
      age: age ?? this.age,
      email: email ?? this.email,
      phoneNumber: phoneNumber ?? this.phoneNumber,
    );
  }
}
