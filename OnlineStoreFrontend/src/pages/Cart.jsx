import { useEffect, useState } from "react";
import api from "../api";
import "./Cart.css";

function Cart() {
  const [cart, setCart] = useState(null);
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    loadCart();
  }, []);

  async function loadCart() {
    try {
      const [cartResponse, productsResponse] = await Promise.all([
        api.get("/api/cart"),
        api.get("/api/products?page=0&size=100"),
      ]);

      setCart(cartResponse.data);
      setProducts(productsResponse.data.content || []);
    } catch (error) {
      console.error("Error loading cart:", error);
      setCart(null);
    } finally {
      setLoading(false);
    }
  }

  async function updateQuantity(productId, amount) {
    try {
      const quantity = Math.abs(amount);

      let response;

      if (amount > 0) {
        response = await api.put(
          `/api/cart/items/${productId}/${quantity}`
        );
      } else {
        response = await api.delete(
          `/api/cart/items/${productId}/${quantity}`
        );
      }

      setCart(response.data);
    } catch (error) {
      console.error("Error updating quantity:", error);
    }
  }

  async function removeItem(productId, quantity) {
    try {
      const response = await api.delete(
        `/api/cart/items/${productId}/${quantity}`
      );

      setCart(response.data);
    } catch (error) {
      console.error("Error removing item:", error);
    }
  }

  async function placeOrder() {
    const confirmed = window.confirm(
      `Place this order for $${total.toFixed(2)}?`
    );

    if (!confirmed) {
      return;
    }

    try {
      const response = await api.post("/api/orders");

      const order = response.data;

      alert(
        `Order #${order.id} placed successfully! Total: $${order.totalPrice.toFixed(2)}`
      );

      await loadCart();
    } catch (error) {
      console.error("Error placing order:", error);

      const message =
        error.response?.data?.message ||
        error.response?.data ||
        "Failed to place order.";

      alert(`Could not place order: ${message}`);
    }
  }

  if (loading) {
    return (
      <div className="cart-page">
        <div className="cart-loading">Loading your cart...</div>
      </div>
    );
  }

  if (!cart || !cart.cartItems || cart.cartItems.length === 0) {
    return (
      <div className="cart-page">
        <div className="empty-cart">
          <div className="empty-cart-icon">🛒</div>
          <h1>Your cart is empty</h1>
          <p>Add some products and they will appear here.</p>
          <a href="/">Continue Shopping</a>
        </div>
      </div>
    );
  }

  const cartItems = cart.cartItems.map((item) => {
    const product = products.find(
      (product) => product.id === item.productId
    );

    return {
      ...item,
      product,
    };
  });

  const total = cartItems.reduce((sum, item) => {
    if (!item.product) return sum;

    return sum + item.product.price * item.quantity;
  }, 0);

  return (
    <div className="cart-page">
      <div className="cart-container">

        <div className="cart-header">
          <div>
            <h1>Shopping Cart</h1>
            <p>
              {cart.cartItems.length}{" "}
              {cart.cartItems.length === 1 ? "item" : "items"} in your cart
            </p>
          </div>

          <a href="/" className="continue-shopping">
            ← Continue Shopping
          </a>
        </div>

        <div className="cart-content">

          <div className="cart-items">

            {cartItems.map((item) => (
              <div className="cart-item" key={item.id}>

                <div className="product-image">
                  🛍️
                </div>

                <div className="product-info">
                  <h2>
                    {item.product?.name || "Product not found"}
                  </h2>

                  <p className="product-price">
                    ${item.product?.price?.toFixed(2) || "N/A"}
                  </p>

                  <button
                    className="remove-button"
                    onClick={() =>
                      removeItem(item.productId, item.quantity)
                    }
                  >
                    Remove
                  </button>
                </div>

                <div className="quantity-section">

                  <span className="quantity-label">
                    Quantity
                  </span>

                  <div className="quantity-controls">

                    <button
                      onClick={() =>
                        updateQuantity(item.productId, -1)
                      }
                    >
                      −
                    </button>

                    <span>{item.quantity}</span>

                    <button
                      onClick={() =>
                        updateQuantity(item.productId, 1)
                      }
                    >
                      +
                    </button>

                  </div>

                </div>

                <div className="item-total">
                  $
                  {item.product
                    ? (
                        item.product.price * item.quantity
                      ).toFixed(2)
                    : "N/A"}
                </div>

              </div>
            ))}

          </div>

          <div className="cart-summary">

            <h2>Order Summary</h2>

            <div className="summary-row">
              <span>Items</span>
              <span>{cart.cartItems.length}</span>
            </div>

            <div className="summary-row">
              <span>Subtotal</span>
              <span>${total.toFixed(2)}</span>
            </div>

            <div className="summary-divider"></div>

            <div className="summary-total">
              <span>Total</span>
              <span>${total.toFixed(2)}</span>
            </div>

            <button
              className="checkout-button"
              onClick={placeOrder}
            >
              Proceed to Checkout
            </button>

          </div>

        </div>

      </div>
    </div>
  );
}

export default Cart;