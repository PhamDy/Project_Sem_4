import 'package:flutter/material.dart';
import '../models/field_model.dart';

class FieldCardWidget extends StatelessWidget {
  final Field field;
  final VoidCallback onBookPressed;

  const FieldCardWidget({
    Key? key,
    required this.field,
    required this.onBookPressed,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // Placeholder image URL
    const placeholderImage =
        'https://img.freepik.com/free-photo/empty-stadium-day_1308-41390.jpg?t=st=1735205823~exp=1735209423~hmac=f31a4d4fa646142e0a7e22b0077d9bcc0546ae457c867275221542052ad497d6&w=826';

    return Card(
      elevation: 4,
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(12),
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          // Field Image
          ClipRRect(
            borderRadius: const BorderRadius.only(
              topLeft: Radius.circular(12),
              topRight: Radius.circular(12),
            ),
            child: Image.network(
              placeholderImage, // Always use the placeholder for now
              height: 150,
              width: double.infinity,
              fit: BoxFit.cover,
            ),
          ),
          Padding(
            padding: const EdgeInsets.all(12),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  field.name,
                  style: const TextStyle(
                    fontSize: 18,
                    fontWeight: FontWeight.bold,
                  ),
                ),
                const SizedBox(height: 8),
                Text(
                  field.address ?? 'No address available',
                  style: const TextStyle(
                    fontSize: 14,
                    color: Colors.grey,
                  ),
                ),
                const SizedBox(height: 8),
                Text(
                  'Rating: ${field.rating?.toStringAsFixed(1) ?? 'N/A'}',
                  style: const TextStyle(
                    fontSize: 14,
                  ),
                ),
                const SizedBox(height: 12),
                Align(
                  alignment: Alignment.centerRight,
                  child: ElevatedButton(
                    onPressed: onBookPressed,
                    child: const Text('Book'),
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
