import 'package:flutter/material.dart';
import '../models/area_model.dart';
import 'area_card_widget.dart';

class AreaListWidget extends StatelessWidget {
  final List<Area> areas;
  final Function(int id) onAreaTap;

  const AreaListWidget({
    Key? key,
    required this.areas,
    required this.onAreaTap,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: areas.length,
      itemBuilder: (context, index) {
        final area = areas[index];
        return AreaCardWidget(
          areaId: area.areaId,
          name: area.name,
          address: area.address,
          rating: area.rating,
          onTap: () => onAreaTap(area.areaId),
        );
      },
    );
  }
}
