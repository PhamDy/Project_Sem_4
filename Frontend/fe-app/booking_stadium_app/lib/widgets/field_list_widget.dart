import 'package:flutter/material.dart';
import '../models/field_model.dart';
import 'field_card_widget.dart';

class FieldListWidget extends StatelessWidget {
  final List<Field> fields;

  const FieldListWidget({
    Key? key,
    required this.fields,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: fields.length,
      itemBuilder: (context, index) {
        final field = fields[index];
        return FieldCardWidget(
          field: field,
          onBookPressed: () {
            // Handle booking action here
            ScaffoldMessenger.of(context).showSnackBar(
              SnackBar(content: Text('Booking Field: ${field.name}')),
            );
          },
        );
      },
    );
  }
}
