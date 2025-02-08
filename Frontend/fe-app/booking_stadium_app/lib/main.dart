import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../providers/area_provider.dart';
import '../providers/field_provider.dart';
import '../providers/price_provider.dart';
import '../providers/accessory_provider.dart';
import '../providers/booking_provider.dart';
import '../providers/user_provider.dart';
import '../screens/area_screen.dart';
import '../screens/field_screen.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        // Register all the providers here
        ChangeNotifierProvider(create: (context) => AreaProvider()),
        ChangeNotifierProvider(create: (context) => FieldProvider()),
        ChangeNotifierProvider(create: (context) => PriceProvider()),
        ChangeNotifierProvider(create: (context) => AccessoryProvider()),
        ChangeNotifierProvider(create: (context) => BookingProvider()),
        ChangeNotifierProvider(create: (context) => UserProvider()),
      ],
      child: MaterialApp(
        title: 'Stadium Booking App',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        initialRoute: '/',
        routes: {
          '/': (context) => AreaScreen(),
          '/field_screen': (context) => FieldScreen(
            areaId: ModalRoute.of(context)!.settings.arguments as int,
          ),
        },
      ),
    );
  }
}
