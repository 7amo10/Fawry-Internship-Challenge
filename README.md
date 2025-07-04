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

### Expected Output

```
=== Scenario 1: Successful checkout with mixed products ===
** Shipment notice **
2x Cheese       400g
1x TV   8000g
Total package weight 8.4kg

** Checkout receipt **
2x Cheese       200
1x TV   5000
1x Scratch Card 50
----------------------
Subtotal        5250
Shipping        3
Amount          5253
Current Balance 4747

=== Scenario 2: Checkout with expired product ===
Error: Product 'Expired Cheese' is expired

=== Scenario 3: Checkout with insufficient balance ===
** Shipment notice **
1x Expensive TV 10000g
Total package weight 10.0kg

Error: Insufficient balance: required 20003.00, available 4747.00

=== Scenario 4: Checkout with empty cart ===
Error: Cannot checkout with an empty cart

=== Scenario 5: Checkout with quantity exceeding stock ===
Error: Not enough quantity available for TV
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

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is part of the Fawry Internship Challenge.
