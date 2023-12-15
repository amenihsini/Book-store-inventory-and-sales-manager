package project;

import java.io.*;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class ReportGenerator {
    private List<Transaction> transactionSaleHistory;
    private List<Transaction> transactionPurchaseHistory;
    private double totalSalesRevenue = 0;
    private Map<String, Double> costPerProduct = new HashMap<>();
    private Map<String, Double> revenuePerProduct = new HashMap<>();

    public void loadTransactionHistory(String saleFileName, String purchaseFileName) {
        if (transactionSaleHistory == null) {
            transactionSaleHistory = new ArrayList<>(TransactionSale.readTransactionHistory(saleFileName));
        }

        if (transactionPurchaseHistory == null) {
            transactionPurchaseHistory = new ArrayList<>(TransactionPurchase.readTransactionHistory(purchaseFileName));
        }
    }

    public void calculateProfit(TransactionSale sale) {
        String productName = sale.getBook();
        int quantity = sale.getQuantity();
        double unitPrice = sale.getPrice();

        double salesRevenue = quantity * unitPrice;

        totalSalesRevenue += salesRevenue;

        // Update revenue per product
        revenuePerProduct.put(productName, revenuePerProduct.getOrDefault(productName, 0.0) + salesRevenue);
    }

    private double getTotalCostOfPurchases() {
        double totalCost = 0;

        for (Transaction purchase : transactionPurchaseHistory) {
            totalCost += purchase.getQuantity() * purchase.getPrice();
            costPerProduct.put(purchase.getBook(), costPerProduct.getOrDefault(purchase.getBook(), 0.0) + totalCost);
        }

        return totalCost;
    }

    private double getTotalRevenues() {
        return totalSalesRevenue;
    }

    private double getTotalCosts() {
        return getTotalCostOfPurchases();
    }

    private String getRevenuesPerBook() {
        StringBuilder revenuesPerBook = new StringBuilder();

        for (Map.Entry<String, Double> entry : revenuePerProduct.entrySet()) {
            revenuesPerBook.append(entry.getKey()).append(": $").append(new DecimalFormat("0.00").format(entry.getValue())).append("\n");
        }

        return revenuesPerBook.toString();
    }

    private String getCostsPerBook() {
        StringBuilder costsPerBook = new StringBuilder();
    
        for (Map.Entry<String, Double> entry : costPerProduct.entrySet()) {
            costsPerBook.append(entry.getKey()).append(": $").append(new DecimalFormat("0.00").format(entry.getValue())).append("\n");
        }
    
        return costsPerBook.toString();
    }

    public double getTotalProfit() {
        double totalCostOfPurchases = getTotalCostOfPurchases();
        return totalSalesRevenue - totalCostOfPurchases;
    }

    public Map<String, Double> getCostPerProduct() {
        return costPerProduct;
    }

    private String getTopProducts() {
    StringBuilder topProducts = new StringBuilder();

    // Filter out sales transactions
    List<TransactionSale> sales = transactionSaleHistory.stream()
            .filter(transaction -> transaction instanceof TransactionSale)
            .map(transaction -> (TransactionSale) transaction)
            .collect(Collectors.toList());

    // Sort sales transactions by quantity
    Collections.sort(sales, (t1, t2) -> Integer.compare(t2.getQuantity(), t1.getQuantity()));

    // Display the top 5 books
    for (int i = 0; i < Math.min(5, sales.size()); i++) {
        TransactionSale sale = sales.get(i);
        topProducts.append(sale.getBook()).append(" (").append(sale.getQuantity()).append(" units), ");
    }

    return topProducts.toString().replaceAll(", $", "");
}

    public void saveReportToFile(String fileName, String content) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateAndPrintReports() {
        double totalProfit = getTotalProfit();
        double totalRevenues = getTotalRevenues();
        double totalCosts = getTotalCosts();

        String totalProfitReport = "Total Profit: $" + new DecimalFormat("0.00").format(totalProfit);
        String totalRevenuesReport = "Total Revenues: $" + new DecimalFormat("0.00").format(totalRevenues);
        String totalCostsReport = "Total Costs: $" + new DecimalFormat("0.00").format(totalCosts);
        String topProductsReport = "Top 5 Books Sold: " + getTopProducts();
        String revenuesPerBookReport = "Revenues per Book Category:\n" + getRevenuesPerBook();
        String costsPerBookReport = "Costs per Book Category:\n" + getCostsPerBook();

        // Print the reports to the console
        System.out.println("Viewing and generating Reports...");
        System.out.println("\nTotal Profit Report:\n" + totalProfitReport);
        System.out.println("\nTotal Revenues Report:\n" + totalRevenuesReport);
        System.out.println("\nTotal Costs Report:\n" + totalCostsReport);
        System.out.println("\nTop Products Report:\n" + topProductsReport);
        System.out.println("\n" + revenuesPerBookReport);
        System.out.println("\n" + costsPerBookReport);
        System.out.println("\nReports generated and printed successfully!");
    }}
