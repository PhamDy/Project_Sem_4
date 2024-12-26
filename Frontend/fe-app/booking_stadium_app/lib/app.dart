import 'models/user_model.dart';

void main() {
  // Mock JSON
  final userJson = {
    "user_id": 1,
    "name": "John Doe",
    "address": "123 Main St, Cityville",
    "age": 30,
    "email": "johndoe@example.com",
    "phone_number": "123-456-7890"
  };

  // Parse JSON to User
  User user = User.fromJson(userJson);

  // Print User details
  print('User Name: ${user.name}');
  print('User Email: ${user.email}');
  print('User Age: ${user.age}');
  print('User Address: ${user.address}');

  // Convert User back to JSON
  Map<String, dynamic> convertedJson = user.toJson();
  print('Converted JSON: $convertedJson');
}
