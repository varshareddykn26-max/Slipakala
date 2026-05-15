package com.shilpakala.app

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.shilpakala.app.data.model.Order
import com.shilpakala.app.ui.orders.OrderCard
import com.shilpakala.app.ui.orders.capitalize
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Android instrumented tests for UI components
 */
@RunWith(AndroidJUnit4::class)
class OrderCardComposeTest {
    
    @get:Rule
    val composeTestRule = createComposeRule()
    
    @Test
    fun testOrderCardDisplaysCorrectly() {
        val testOrder = Order(
            orderId = 1,
            customerName = "John Doe",
            customerPhone = "+91-9999999999",
            productName = "Wooden Bowl",
            quantity = 5,
            price = 500.0,
            totalAmount = 2500.0,
            status = "pending"
        )
        
        composeTestRule.setContent {
            OrderCard(
                order = testOrder,
                onOrderClick = {}
            )
        }
        
        // Verify order details are displayed
        composeTestRule.onNodeWithText("Wooden Bowl").assertExists()
        composeTestRule.onNodeWithText("John Doe").assertExists()
        composeTestRule.onNodeWithText("2500.0").assertExists()
    }
    
    @Test
    fun testCapitalizeFunction() {
        val result = "pending".capitalize()
        assert(result == "Pending") { "capitalize() should capitalize first letter" }
        
        val result2 = "processing".capitalize()
        assert(result2 == "Processing") { "capitalize() should work for all words" }
    }
}
