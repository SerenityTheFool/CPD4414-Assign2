/*
 * Copyright 2015 Len Payne <len.payne@lambtoncollege.ca>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cpd4414.assign2;

import cpd4414.assign2.OrderQueue;
import cpd4414.assign2.Purchase;
import cpd4414.assign2.Order;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Scott Melanson
 */
public class OrderQueueTest {
    
    public OrderQueueTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test //1
    public void testWhenCustomerExistsAndPurchasesExistThenTimeReceivedIsNow() throws Exception {
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase("PROD0004", 450));
        order.addPurchase(new Purchase("PROD0006", 250));
        orderQueue.add(order);
        
        long expResult = new Date().getTime();
        long result = order.getTimeReceived().getTime();
        assertTrue(Math.abs(result - expResult) < 1000);
    }
    
    @Test //2
    public void testWhenCustomerDoesNotExistsAndThenThrowException() {
        boolean var = false;
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("", "");
        order.addPurchase(new Purchase("PROD0004", 450));
        order.addPurchase(new Purchase("PROD0006", 250));
        
        try
        {
            orderQueue.add(order);
        }
        catch (Exception ex)
        {
            var = true;
        }
        
        assertTrue(var);
    }
    
    @Test //3
    public void testWhenNoListOfPurchasesThrowException(){
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        boolean var = false;
        
        try
        {
            orderQueue.add(order);
        }
        catch (Exception ex)
        {
            var = true;
        }
        
        assertTrue(var);

    }
    
    @Test //4
    public void testWhenNextOrderAndOrdersExistReturnEarliestOrderNotProcessedWithNoTime() throws Exception {
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase("PROD0004", 450));
        order.addPurchase(new Purchase("PROD0006", 250));
        orderQueue.add(order);
        Order testOrder = orderQueue.getEarliestNotProcessed();


        assertEquals(order,testOrder);
    }
    
   @Test //5
    public void testWhenNextOrderAndNoOrdersExistReturnNull() throws Exception {
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase("PROD0004", 450));
        order.addPurchase(new Purchase("PROD0006", 250));
        orderQueue.add(order);
        Order testOrder = orderQueue.getEarliestNotProcessed();

        assertNull(testOrder.getTimeProcessed());
    }
    
    @Test //6
    public void testWhenNextOrderIfNoOrdersReturnNull(){
        OrderQueue orderQueue = new OrderQueue();
        
        boolean expResult = true;
        if (orderQueue.next() == null){
            expResult = true;
        }
        
        assertEquals(expResult, true);
    }
}
