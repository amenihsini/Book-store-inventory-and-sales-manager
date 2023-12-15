# Book-store-inventory-and-sales-manager
*Project Description
The considered project aims to enhance the operational efficiency of a Small Business (SB) through the development of an advanced Inventory Management and Sales Reporting System. This multifaceted system is meticulously designed to address two crucial aspects: the seamless management of the SB's inventory and the generation of insightful sales reports.

*Key Features
-Product Management:

Enable users to add, update, and delete products in the inventory.
Each product should have attributes such as name, quantity, price, and category.
Inventory Management:

Establish a robust inventory management system that empowers the SB to effortlessly add, update, and remove products.
Sales and Purchase Logging:

Record sales and purchase transactions, capturing details like product, quantity, date, and transaction type.
Basic Sales Reporting:

Generate basic reports, such as the current inventory status, sales history, and popular products.
Implement data analysis features, like calculating average sales, identifying peak sales periods, and determining the most popular products.
-User Authentication:

Incorporate a simple user authentication system to secure access to the inventory system.
Data Entry:

Develop a system that allows users to input sales data, including product details, sales quantity, and dates.
File Handling:

Use file handling to store and retrieve sales data.
Employ text files or simple flat file databases for storage.
Data Display:

Create a dashboard-like interface to display basic sales statistics, such as total sales, best-selling products, and sales trends over time.
*Technical Requirements
Object-Oriented Design
Utilize object-oriented principles to design classes for products, transactions, users, etc. The program must include at least one inheritance relationship between classes. Examples of the main used classes are described as follows:

-Class "Product":
Attributes: ‘productId’, ‘productName’, ‘quantityInStock’, ‘price’, ‘category’, etc.
Methods: Getters and setters for attributes, methods for updating quantities, and any other product-specific functionality.
-Class "InventoryManager":
Attributes: List of ‘Product’ objects.
Methods: Add, update, and remove products from the inventory. Categorization and organization of products.
-Class "Transaction":
Attributes: transactionId, book, quantity, price, category, transactionType, transactionTime, etc.
Methods: Constructor for initializing transactions, methods for recording and reading transaction history.
-Class "TransactionSale":
Attributes: Inherits from Transaction with additional attributes.
Methods: Constructor for sales transactions, overridden methods for recording and reading sale transaction history.
-Class "TransactionPurchase":
Attributes: Inherits from Transaction with additional attributes.
Methods: Constructor for purchase transactions, overridden methods for recording and reading purchase transaction history.
-Class "ReportGenerator":
Attributes: Lists for sale and purchase transactions, revenue, cost, and maps for product-wise analytics.
Methods: Load transaction history, calculate profit, retrieve costs and revenues, generate reports, and save to file.
-Class "Login":
Attributes: username, password, role, etc.
Methods: Authenticate users, perform authorization checks.
*File Handling
Implement file I/O operations to store and retrieve product information, transactions, user details, and sales reports.

*Basic Data Structures
Utilize basic data structures (arrays, lists) to manage and manipulate sales data.

*User Interface
Create a text-based user interface for easy interaction.

*Usage
To run the Inventory Management and Sales Reporting System, follow these steps:

*Compile and execute the main program.
Navigate through the user interface to perform actions like adding products, recording sales, generating reports, etc.
