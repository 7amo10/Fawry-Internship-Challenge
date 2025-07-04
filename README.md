# Fawry E-commerce System

A Java-based e-commerce system that demonstrates various checkout scenarios including product validation, shipping calculations, and error handling.

## Project Structure

```
src/main/java/com/fawry/ecommerce/
├── Main.java                 # Main application entry point
├── model/                    # Domain models
│   ├── Product.java          # Abstract base product class
│   ├── ExpiringProduct.java  # Products with expiration dates
│   ├── NonExpiringProduct.java # Products without expiration
│   ├── Cart.java            # Shopping cart implementation
│   ├── CartItem.java        # Individual cart items
│   ├── Customer.java        # Customer entity
│   ├── Shippable.java       # Interface for shippable items
│   └── ShippableProduct.java # Adapter for shipping products
├── service/                  # Business logic services
│   ├── CheckoutService.java  # Checkout process handling
│   └── ShippingService.java  # Shipping calculations
└── exception/               # Custom exceptions
    ├── EmptyCartException.java
    ├── InsufficientBalanceException.java
    ├── ProductExpiredException.java
    └── ProductOutOfStockException.java
```

## Features

- **Product Management**: Support for both expiring and non-expiring products
- **Shopping Cart**: Add, remove, and manage cart items
- **Checkout Process**: Complete checkout with validation
- **Shipping Calculation**: Weight-based shipping cost calculation
- **Error Handling**: Comprehensive exception handling for various scenarios
- **Balance Management**: Customer balance tracking and validation

## Demo Scenarios

The application demonstrates the following scenarios:

1. **Successful Checkout**: Mixed products with shipping calculation
2. **Expired Product**: Error handling for expired products
3. **Insufficient Balance**: Error handling for insufficient customer balance
4. **Empty Cart**: Error handling for checkout with empty cart
5. **Quantity Exceeded**: Error handling for exceeding available stock

## How to Run

### Prerequisites
- Java 8 or higher
- Git (for version control)

### Compilation and Execution

```bash
# Navigate to the Java source directory
cd src/main/java

# Compile the project
javac com/fawry/ecommerce/Main.java

# Run the application
java com.fawry.ecommerce.Main
```

## Design Patterns Used

- **Strategy Pattern**: Different product types (expiring vs non-expiring)
- **Adapter Pattern**: ShippableProduct adapts Product to Shippable interface
- **Service Layer Pattern**: Separation of business logic in service classes
- **Exception Handling Pattern**: Custom exceptions for different error scenarios

## Architecture

The system follows a clean architecture approach with:

- **Domain Layer**: Core business entities (Product, Cart, Customer)
- **Service Layer**: Business logic implementation
- **Exception Layer**: Custom exception handling
- **Main Application**: Demo scenarios and orchestration

