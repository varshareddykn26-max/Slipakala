# ShilpaKala Professional Features Guide

## Overview
This document details the 10 professional features added to ShilpaKala to transform it into a comprehensive artisan business management platform.

---

## ✅ Feature 1: Order Management System

### Location
- **Screen**: `ui/orders/OrderManagementScreen.kt`
- **ViewModel**: `ui/orders/OrderManagementViewModel.kt`
- **Database**: `data/database/OrderDao.kt`
- **Model**: `data/model/Order.kt`

### Features
- **Create Orders**: Add new customer orders with product details
- **Track Orders**: Monitor order status (pending, processing, completed, shipped)
- **Customer Management**: View orders by customer phone number
- **Order Metrics**:
  - Pending orders count
  - Total revenue tracking
  - Today's orders count
- **Status Filtering**: Filter orders by status for easy management
- **Order Details**: Update order information and delivery dates

### Data Structure
```kotlin
Order(
    orderId: Int,
    customerName: String,
    customerPhone: String,
    customerEmail: String?,
    productName: String,
    quantity: Int,
    price: Double,
    totalAmount: Double,
    status: String, // pending, processing, completed, shipped
    deliveryDate: String?,
    notes: String?,
    paymentStatus: String
)
```

---

## ✅ Feature 2: Advanced Analytics Dashboard

### Location
- **Screen**: `ui/analytics/AnalyticsDashboardScreen.kt`
- **ViewModel**: `ui/analytics/AnalyticsDashboardViewModel.kt`
- **Database**: `data/database/AnalyticsDao.kt`
- **Model**: `data/model/Analytics.kt`

### Features
- **Key Metrics**:
  - Total revenue
  - Average views per day
  - Total sales count
  - Total inquiries
- **Time Period Selection**: Week, Month, All-time views
- **Daily Performance**: Day-by-day breakdown of metrics
- **Top Selling Product**: Identifies best-performing product
- **Revenue Tracking**: Cumulative and daily revenue tracking
- **Inquiry Tracking**: Monitor customer inquiries by date

### Data Structure
```kotlin
DailyAnalytics(
    date: String, // YYYY-MM-DD
    views: Int,
    inquiries: Int,
    sales: Double,
    revenue: Double,
    topProduct: String?
)
```

---

## ✅ Feature 3: Multi-Language & Localization

### Location
- **Settings**: `data/model/Settings.kt`
- **DAO**: `data/database/SettingsDao.kt`

### Features
- **Supported Languages**:
  - English (en)
  - Hindi (hi)
  - Tamil (ta)
  - Telugu (te)
  - Bengali (bn)
- **Regional Pricing**: Currency settings (INR, USD, etc.)
- **Persistent Settings**: Language preference saved in database

### Implementation
```kotlin
AppSettings(
    language: String = "en", // en, hi, ta, te, bn
    currency: String = "INR",
    autoBackup: Boolean = true,
    // ... other settings
)
```

---

## ✅ Feature 4: Professional Watermarking & Copyright

### Location
- **Utility**: `utils/WatermarkUtil.kt`
- **Features**:
  - Text watermarking
  - Logo watermarking
  - Customizable opacity
  - Multiple positions

### Watermark Positions
- Bottom Right (default)
- Bottom Left
- Top Right
- Top Left
- Center

### Functions
```kotlin
WatermarkUtil.addTextWatermark(
    originalBitmap: Bitmap,
    watermarkText: String,
    opacity: Float = 0.5f,
    position: String = "bottom_right"
): Bitmap

WatermarkUtil.addLogoWatermark(
    originalBitmap: Bitmap,
    logoBitmap: Bitmap,
    opacity: Float = 0.5f,
    position: String = "bottom_right"
): Bitmap
```

---

## ✅ Feature 5: Product Catalog Builder

### Location
- **Screen**: `ui/catalog/ProductCatalogScreen.kt`
- **ViewModel**: `ui/catalog/ProductCatalogViewModel.kt`
- **Database**: `data/database/ProductDao.kt`
- **Model**: `data/model/Product.kt`

### Features
- **Product Management**:
  - Add, edit, delete products
  - Categorize products
  - Set pricing and stock levels
- **Category Filtering**: Filter products by category
- **PDF Catalog Generation**: Create professional product brochures
- **Product Features**:
  - Product name, description, category
  - Pricing information
  - Stock quantity
  - Featured product marking
  - Image storage paths
  - QR code integration

### Data Structure
```kotlin
Product(
    productId: Int,
    productName: String,
    category: String,
    description: String,
    price: Double,
    stockQuantity: Int,
    imagePath: String,
    qrCode: String?,
    watermarkEnabled: Boolean = true,
    isFeatured: Boolean = false
)
```

---

## ✅ Feature 6: Direct Buyer Communication

### Location
- **Screen**: `ui/chat/ChatScreen.kt`
- **ViewModel**: `ui/chat/ChatViewModel.kt`
- **Database**: `data/database/ChatDao.kt`
- **Model**: `data/model/Chat.kt`

### Features
- **In-App Messaging**:
  - Start conversations with buyers
  - Send/receive real-time messages
  - Message timestamp tracking
- **Conversation Management**:
  - Unread message tracking
  - Conversation history
  - Active conversation status
- **Buyer Profiles**:
  - Store buyer name, phone, ID
  - Track conversation activity

### Data Structure
```kotlin
ChatMessage(
    messageId: Int,
    conversationId: Int,
    senderId: String,
    senderName: String,
    message: String,
    timestamp: Long,
    isRead: Boolean = false,
    attachmentPath: String?
)

Conversation(
    conversationId: Int,
    buyerId: String,
    buyerName: String,
    buyerPhone: String,
    lastMessage: String,
    unreadCount: Int = 0,
    isActive: Boolean = true
)
```

---

## ✅ Feature 7: QR Code & Product Labeling

### Location
- **Utility**: `utils/QRCodeGenerator.kt`

### Features
- **QR Code Generation**:
  - Generate product-specific QR codes
  - Embed product details (name, ID, price)
  - Add text labels to QR codes
- **Product Linking**: QR codes link to product information
- **Label Generation**: Print-ready product labels

### Functions
```kotlin
QRCodeGenerator.generateQRCode(data: String): Bitmap?

QRCodeGenerator.generateProductQRCode(
    productName: String,
    productId: Int,
    price: Double
): Bitmap?

QRCodeGenerator.addTextToBitmap(qrBitmap: Bitmap, text: String): Bitmap
```

---

## ✅ Feature 8: Inventory & Stock Management

### Location
- **Screen**: `ui/inventory/InventoryViewModel.kt`
- **Database**: `data/database/InventoryDao.kt`
- **Model**: `data/model/Inventory.kt`

### Features
- **Stock Tracking**:
  - Current stock levels
  - Minimum stock thresholds
  - Low-stock alerts
- **Restock Management**:
  - Track last restock date and quantity
  - Monitor stock depletion
- **Expiry Tracking**:
  - Optional expiry date for perishable items
  - Alert on expired stock

### Data Structure
```kotlin
Inventory(
    inventoryId: Int,
    productId: Int,
    currentStock: Int,
    minimumStock: Int,
    lastRestockDate: Long,
    lastRestockQuantity: Int,
    expiryDate: Long? = null
)
```

---

## ✅ Feature 9: Payment Integration & Invoicing

### Location
- **Utility**: `utils/PaymentUtil.kt`
- **PDF Generation**: `utils/PDFGenerator.kt`
- **Database**: `data/database/PaymentDao.kt`
- **Model**: `data/model/Payment.kt`

### Features
- **Payment Gateway Integration**:
  - Razorpay integration ready
  - Support for: Card, UPI, Google Pay, Phone Pe
  - Payment status tracking
- **Invoice Generation**:
  - Auto-generate PDF invoices
  - Store invoice copies
  - Payment transaction tracking
- **Payment Methods**:
  - Credit/Debit Card
  - UPI
  - Digital Wallets
  - Bank Transfer

### Data Structure
```kotlin
Payment(
    paymentId: Int,
    orderId: Int,
    amount: Double,
    paymentMethod: String, // razorpay, gpay, phonepay, upi, card
    transactionId: String,
    status: String, // pending, success, failed
    invoiceNumber: String?
)

Invoice(
    invoiceId: Int,
    orderId: Int,
    invoiceNumber: String,
    invoicePath: String,
    dueDate: Long?,
    paidDate: Long?
)
```

### Functions
```kotlin
PDFGenerator.generateInvoice(
    context: Context,
    order: Order,
    artisanName: String,
    artisanPhone: String,
    artisanEmail: String?
): String?

PDFGenerator.generateCatalog(
    context: Context,
    artisanName: String,
    products: List<Product>,
    description: String = ""
): String?
```

---

## ✅ Feature 10: Cloud Backup & Portfolio Sync

### Location
- **Utility**: `utils/CloudBackupUtil.kt`
- **Database**: `data/database/SettingsDao.kt`
- **Model**: `data/model/Settings.kt`

### Features
- **Automatic Backup**:
  - Configure backup frequency
  - Backup orders, products, images
  - Firebase Cloud Storage integration
- **Data Restoration**:
  - Restore from cloud backups
  - Multi-device sync
- **Portfolio Sharing**:
  - Generate secure shareable links
  - Track portfolio view count
  - Expiry date for shared links
- **Profile Image Upload**:
  - Cloud storage for profile images
  - Download secure URLs

### Data Structure
```kotlin
BackupLog(
    backupId: Int,
    backupTimestamp: Long,
    backupPath: String,
    itemsBackedUp: Int,
    backupStatus: String, // pending, success, failed
    sizeInBytes: Long = 0
)

PortfolioLink(
    linkId: Int,
    shareToken: String,
    portfolioName: String,
    createdAt: Long,
    expiresAt: Long?,
    viewCount: Int = 0,
    isActive: Boolean = true
)
```

### Functions
```kotlin
CloudBackupUtil.backupOrders(orders: List<Order>): Boolean
CloudBackupUtil.backupProducts(products: List<Product>): Boolean
CloudBackupUtil.restoreOrders(): List<Order>?
CloudBackupUtil.restoreProducts(): List<Product>?
CloudBackupUtil.uploadProfileImage(context: Context, imagePath: String): String?
CloudBackupUtil.enableAutoBackup(enabled: Boolean): Boolean
```

---

## 📱 Database Schema

### Tables Created
1. **orders** - Customer orders and tracking
2. **products** - Product catalog
3. **inventory** - Stock management
4. **analytics** - Daily analytics data
5. **daily_analytics** - Aggregate daily metrics
6. **payments** - Payment transactions
7. **invoices** - Invoice records
8. **chat_messages** - Message history
9. **conversations** - Chat conversations
10. **reviews** - Customer reviews and ratings
11. **backup_logs** - Backup history
12. **app_settings** - App configuration
13. **portfolio_links** - Shareable portfolio links

---

## 🔧 Dependencies Added

### Core Libraries
- **Room Database**: `androidx.room:room-runtime:2.6.1`
- **Retrofit**: `com.squareup.retrofit2:retrofit:2.11.0`
- **OkHttp**: `com.squareup.okhttp3:okhttp:4.12.0`
- **QR Code**: `com.google.zxing:core:3.5.3`
- **PDF Generation**: `com.itextpdf:itextpdf:5.5.13.3`

### Firebase Integration
- **Firebase Storage**: `com.google.firebase:firebase-storage-ktx:20.3.0`
- **Firebase Auth**: `com.google.firebase:firebase-auth-ktx:22.3.1`
- **Firebase Firestore**: `com.google.firebase:firebase-firestore-ktx:24.11.1`

### Payment & Utilities
- **Razorpay**: `com.razorpay:checkout:1.6.33`
- **WorkManager**: `androidx.work:work-runtime-ktx:2.9.0`
- **DataStore**: `androidx.datastore:datastore-preferences:1.0.0`
- **Gson**: `com.google.code.gson:gson:2.10.1`

---

## 🚀 Integration Steps

### 1. Update Navigation
Add new routes to `AppNavigation.kt`:
```kotlin
composable("orders") { OrderManagementScreen(...) }
composable("analytics") { AnalyticsDashboardScreen(...) }
composable("catalog") { ProductCatalogScreen(...) }
composable("chat") { ChatListScreen(...) }
composable("inventory") { InventoryScreen(...) }
composable("settings") { SettingsScreen(...) }
```

### 2. Initialize Database
```kotlin
val database = Room.databaseBuilder(
    context,
    ShilpaKalaDatabase::class.java,
    "shilpakala_db"
).build()
```

### 3. Configure Firebase
Add `google-services.json` to the app directory

### 4. Add Permissions to AndroidManifest.xml
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```

---

## 📊 Usage Examples

### Creating an Order
```kotlin
val order = Order(
    customerName = "John Doe",
    customerPhone = "+91-9999999999",
    productName = "Wooden Bowl",
    quantity = 5,
    price = 500.0,
    totalAmount = 2500.0,
    status = "pending"
)
orderViewModel.addOrder(order)
```

### Generating QR Code
```kotlin
val qrBitmap = QRCodeGenerator.generateProductQRCode(
    productName = "Wooden Bowl",
    productId = 1,
    price = 500.0
)
```

### Creating Invoice
```kotlin
val invoicePath = PDFGenerator.generateInvoice(
    context,
    order,
    "Artisan Name",
    "+91-9999999999",
    "artisan@example.com"
)
```

### Backing Up Data
```kotlin
CloudBackupUtil.backupOrders(orders)
CloudBackupUtil.backupProducts(products)
```

---

## 🔐 Security Considerations

1. **API Keys**: Store Razorpay key securely in BuildConfig
2. **Firebase**: Enable authentication rules
3. **Database Encryption**: Consider enabling SQLCipher
4. **HTTPS**: Ensure all API calls are over HTTPS
5. **User Privacy**: Comply with data protection regulations

---

## 🎯 Next Steps

1. **Connect Screens**: Update HomeScreen to include navigation to new features
2. **Complete SettingsScreen**: Implement language, backup, and watermark settings
3. **Add PaymentScreen**: Complete Razorpay integration UI
4. **Implement Notifications**: Add WorkManager for low-stock and backup alerts
5. **Add Reviews System**: Implement ReviewScreen for customer ratings
6. **Localization**: Integrate multi-language support strings

---

## 📝 Notes

- All features use Room Database for local persistence
- Firebase integration required for cloud backup
- Razorpay account needed for payment processing
- QR codes use ZXing library for generation
- PDF generation uses iText library
- All data models follow MVVM architecture pattern

