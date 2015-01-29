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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Scott Melanson
 */
public class OrderQueue {

    Queue<Order> orderQueue = new ArrayDeque<>();
    List<Order> orderProcessed = new ArrayList<>();
    List<Order> orderFulfilled = new ArrayList<>();

    public void add(Order order) throws Exception {
        orderQueue.add(order);

        if (order.getCustomerName().equals("") || order.getCustomerId().equals("")) {
            throw new Exception("Customer Name or ID is Empty");
        }

        if (order.getListOfPurchases().isEmpty()) {
            throw new Exception("Purchase list is Empty");
        }
        order.setTimeReceived(new Date());

    }

    public Order getEarliestNotProcessed() {

        return orderQueue.peek();

    }

    public Order processNextOrder() {
        if (orderQueue.peek().getTimeProcessed() == null) { //if null then not processed
            Order tempOrder = orderQueue.remove();
            orderProcessed.add(tempOrder);
            return tempOrder;
        } else {
            return null;
        }
    }
    
    public Order next(){
        return orderQueue.peek();
    }
    
    public void process(Order order){
        if(order.getTimeReceived()!= null){
            order.setTimeProcessed(new Date());
        }
        
        for(int x = 0;x < order.getListOfPurchases().size(); x++){
           String productId = order.getListOfPurchases().get(x).getProductId();
           //TODO: Check if product is in stock in Database
        }
            
            
    }
}
