import { useEffect, useState } from "react";
import api from "../api";
import "./Orders.css";

function Orders() {
  const [orders, setOrders] = useState([]);
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    loadOrders();
  }, []);

  async function loadOrders() {
    try {
      const [ordersResponse, productsResponse] = await Promise.all([
        api.get("/api/orders"),
        api.get("/api/products?page=0&size=100"),
      ]);

      setOrders(ordersResponse.data);
      setProducts(productsResponse.data.content || []);
    } catch (error) {
      console.error("Error loading orders:", error);
      setOrders([]);
    } finally {
      setLoading(false);
    }
  }

  function getProduct(productId) {
    return products.find((product) => product.id === productId);
  }

  if (loading) {
    return (
      <div className="orders-page">
        <p className="orders-loading">Loading your orders...</p>
      </div>
    );
  }

  if (orders.length === 0) {
    return (
      <div className="orders-page">
        <div className="empty-orders">
          <div className="empty-orders-icon">📦</div>
          <h1>No orders yet</h1>
          <p>Your placed orders will appear here.</p>
          <a href="/">Start Shopping</a>
        </div>
      </div>
    );
  }

  return (
    <div className="orders-page">
      <div className="orders-container">

        <div className="orders-header">
          <h1>My Orders</h1>
          <p>
            {orders.length}{" "}
            {orders.length === 1 ? "order" : "orders"}
          </p>
        </div>

        <div className="orders-list">

          {orders.map((order) => (
            <div className="order-card" key={order.id}>

              <div className="order-header">
                <div>
                  <h2>Order #{order.id}</h2>
                  <p>Placed on {order.orderDate}</p>
                </div>

                <strong className="order-total">
                  ${order.totalPrice.toFixed(2)}
                </strong>
              </div>

              <div className="order-items">

                {order.orderItems.map((item) => {
                  const product = getProduct(item.productId);

                  return (
                    <div className="order-item" key={item.id}>

                      <div className="order-product-icon">
                        🛍️
                      </div>

                      <div className="order-product-info">
                        <h3>
                          {product?.name ||
                            `Product #${item.productId}`}
                        </h3>

                        <p>
                          Quantity: {item.quantity}
                        </p>
                      </div>

                      <div className="order-product-price">
                        {product
                          ? `$${(
                              product.price * item.quantity
                            ).toFixed(2)}`
                          : "N/A"}
                      </div>

                    </div>
                  );
                })}

              </div>

              <div className="order-footer">
                <span>Total</span>
                <strong>
                  ${order.totalPrice.toFixed(2)}
                </strong>
              </div>

            </div>
          ))}

        </div>

      </div>
    </div>
  );
}

export default Orders;