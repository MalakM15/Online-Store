import { useEffect, useState } from "react";
import api from "../api";
import ProductCard from "../components/ProductCard";

function Home() {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    api
      .get("/api/products?page=0&size=12")
      .then((response) => {
        setProducts(response.data.content);
        setLoading(false);
      })
      .catch(() => {
        setError("Could not load products.");
        setLoading(false);
      });
  }, []);

  const addToCart = (productId) => {
    api
      .put(`/api/cart/items/${productId}/1`)
      .then(() => {
        alert("Product added to cart!");
      })
      .catch((error) => {
        if (error.response?.status === 401) {
          alert("Please login first.");
        } else {
          alert("Could not add product to cart.");
        }
      });
  };

  if (loading) {
    return <div className="message">Loading products...</div>;
  }

  if (error) {
    return <div className="message error">{error}</div>;
  }

  return (
    <main>
      <section className="hero">
        <h1>Welcome to My Store</h1>
        <p>Find the products you need.</p>
      </section>

      <section className="products-section">
        <h2>Products</h2>

        <div className="product-grid">
          {products.map((product) => (
            <ProductCard
              key={product.id}
              product={product}
              onAddToCart={addToCart}
            />
          ))}
        </div>
      </section>
    </main>
  );
}

export default Home;