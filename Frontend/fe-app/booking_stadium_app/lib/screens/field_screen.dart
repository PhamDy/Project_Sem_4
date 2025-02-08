import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../providers/field_provider.dart';
import '../widgets/field_card_widget.dart';
import '../widgets/loading_indicator_widget.dart';
import '../widgets/custom_error_widget.dart';

class FieldScreen extends StatefulWidget {
  final int areaId;

  const FieldScreen({Key? key, required this.areaId}) : super(key: key);

  @override
  _FieldScreenState createState() => _FieldScreenState();
}

class _FieldScreenState extends State<FieldScreen> {
  late TextEditingController _searchController;
  String _searchQuery = '';

  @override
  void initState() {
    super.initState();
    _searchController = TextEditingController();
    // Load mock data for fields
    WidgetsBinding.instance.addPostFrameCallback((_) {
      Provider.of<FieldProvider>(context, listen: false).loadMockData();
    });
  }

  @override
  void dispose() {
    _searchController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Sân'),
        actions: [
          IconButton(
            icon: const Icon(Icons.search),
            onPressed: () {
              // Focus on search bar when the search icon is clicked
              FocusScope.of(context).requestFocus();
            },
          ),
        ],
      ),
      body: Column(
        children: [
          // Search Bar
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: TextField(
              controller: _searchController,
              decoration: const InputDecoration(
                labelText: 'Tìm sân',
                border: OutlineInputBorder(),
                prefixIcon: Icon(Icons.search),
              ),
              onChanged: (value) {
                setState(() {
                  _searchQuery = value;
                });
              },
            ),
          ),
          // Fields List
          Expanded(
            child: Consumer<FieldProvider>(
              builder: (context, fieldProvider, child) {
                final fields = fieldProvider.searchFields(
                  _searchQuery,
                  widget.areaId,
                );

                if (fields.isEmpty && _searchQuery.isEmpty) {
                  return const LoadingIndicatorWidget();
                }

                if (fields.isEmpty) {
                  return const CustomErrorWidget(
                    message: 'Không có sân tại khu vực này.',
                  );
                }

                return ListView.builder(
                  itemCount: fields.length,
                  itemBuilder: (context, index) {
                    final field = fields[index];
                    return FieldCardWidget(
                      field: field,
                      onBookPressed: () {
                        // Handle booking
                        Navigator.pushNamed(
                          context,
                          '/booking',
                          arguments: field,
                        );
                      },
                    );
                  },
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}
