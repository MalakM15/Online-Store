function ProductCard({ product, onAddToCart }) {
  return (
    <div className="product-card">
      <div className="product-image">
        🛍️
      </div>

      <div className="product-info">
        <p className="category">{product.categoryName}</p>

        <h3>{product.name}</h3>

        <p className="description">
          {product.description}
        </p>

        <div className="product-bottom">
          <strong>${product.price.toFixed(2)}</strong>

          <span>
            {product.stock > 0 ? "In Stock" : "Out of Stock"}
          </span>
        </div>

        <button
          className="add-cart-btn"
          disabled={product.stock === 0}
          onClick={() => onAddToCart(product.id)}
        >
          {product.stock > 0 ? "Add to Cart" : "Out of Stock"}
        </button>
      </div>
    </div>
  );
}

export default ProductCard;