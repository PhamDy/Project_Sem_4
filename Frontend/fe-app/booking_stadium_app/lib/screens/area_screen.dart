import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../providers/area_provider.dart';
import '../widgets/search_bar_widget.dart';
import '../widgets/loading_indicator_widget.dart';
import '../widgets/custom_error_widget.dart';
import '../widgets/area_list_widget.dart';

class AreaScreen extends StatefulWidget {
  @override
  _AreaScreenState createState() => _AreaScreenState();
}

class _AreaScreenState extends State<AreaScreen> {
  final TextEditingController _searchController = TextEditingController();

  @override
  void initState() {
    super.initState();
    Provider.of<AreaProvider>(context, listen: false).fetchAreas();
  }

  @override
  Widget build(BuildContext context) {
    final areaProvider = Provider.of<AreaProvider>(context);

    return Scaffold(
      appBar: AppBar(
        title: const Text('Tìm theo khu vực'),
      ),
      body: Column(
        children: [
          // Search Bar
          SearchBarWidget(
            controller: _searchController,
            onChanged: (query) => areaProvider.filterAreas(query),
          ),
          // Main Content
          Expanded(
            child: areaProvider.isLoading
                ? const LoadingIndicatorWidget()
                : areaProvider.errorMessage != null
                ? CustomErrorWidget(message: areaProvider.errorMessage!)
                : areaProvider.filteredAreas.isEmpty
                ? const Center(child: Text('Không tìm thấy.'))
                : AreaListWidget(
              areas: areaProvider.filteredAreas,
              onAreaTap: (id) {
                Navigator.pushNamed(
                  context,
                  '/field_screen',
                  arguments: id,
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}
