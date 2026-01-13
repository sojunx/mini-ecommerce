import { Button } from "@/components/ui/button";
import { http } from "@/lib/http";
import { ArrowLeft } from "lucide-react";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router";

interface ProductVariant {
  color: string;
  image_url: string;
  price: number;
  size: string;
  sku: string;
  stock_quantity: number;
}

interface Product {
  base_price: number;
  category: string;
  description: string;
  id: string;
  image_url: string;
  name: string;
  variants?: ProductVariant[];
}

const ProductDetails = () => {
  const params = useParams();
  const navigate = useNavigate();
  const [product, setProduct] = useState<Product | null>(null);
  const [selectedVariant, setSelectedVariant] = useState<ProductVariant | null>(
    null
  );
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const getData = async () => {
      try {
        setLoading(true);
        const res = await http.get(`/api/products/${params.id}`);
        const { data } = res.data;
        setProduct(data.product);
        if (data.product.variants && data.product.variants.length > 0) {
          setSelectedVariant(data.product.variants[0]);
        }
      } catch (error) {
        console.error("Error fetching product details:", error);
      } finally {
        setLoading(false);
      }
    };

    getData();
  }, [params.id]);

  if (loading) {
    return (
      <div className="flex-1 flex items-center justify-center">
        <p className="text-muted-foreground">Loading...</p>
      </div>
    );
  }

  if (!product) {
    return (
      <div className="flex-1 flex items-center justify-center">
        <p className="text-muted-foreground">Product not found</p>
      </div>
    );
  }

  const currentPrice = selectedVariant?.price || product.base_price;
  const currentImage = selectedVariant?.image_url || product.image_url;

  return (
    <div className="flex-1 px-4 py-6">
      <Button
        variant="ghost"
        size="sm"
        onClick={() => navigate(-1)}
        className="mb-4"
      >
        <ArrowLeft className="h-4 w-4 mr-2" />
        Back
      </Button>

      <div className="grid md:grid-cols-2 gap-8 max-w-6xl">
        {/* Image Section */}
        <div className="border rounded-lg p-4">
          <img
            src={currentImage}
            alt={product.name}
            className="w-full h-96 object-cover rounded-md"
          />
        </div>

        {/* Details Section */}
        <div className="space-y-6">
          <div>
            <span className="inline-block px-3 py-1 text-xs rounded-full bg-secondary">
              {product.category}
            </span>
            <h1 className="text-3xl font-semibold mt-3">{product.name}</h1>
            <p className="text-2xl font-medium mt-2">
              $ {currentPrice.toFixed(2)}
            </p>
          </div>

          <div>
            <h3 className="text-sm font-medium mb-2">Description</h3>
            <p className="text-sm text-muted-foreground leading-relaxed">
              {product.description}
            </p>
          </div>

          {/* Variants */}
          {product.variants && product.variants.length > 0 && (
            <div className="space-y-4">
              <div>
                <h3 className="text-sm font-medium mb-3">Available Variants</h3>
                <div className="space-y-2">
                  {product.variants.map((variant, index) => (
                    <button
                      key={index}
                      onClick={() => setSelectedVariant(variant)}
                      className={`w-full text-left border rounded-lg p-3 transition-colors ${
                        selectedVariant === variant
                          ? "border-primary bg-accent"
                          : "hover:bg-accent"
                      }`}
                    >
                      <div className="flex items-center justify-between">
                        <div>
                          <p className="text-sm font-medium">
                            {variant.color} - {variant.size}
                          </p>
                          <p className="text-xs text-muted-foreground mt-1">
                            SKU: {variant.sku}
                          </p>
                        </div>
                        <div className="text-right">
                          <p className="text-sm font-medium">
                            $ {variant.price.toFixed(2)}
                          </p>
                          <p className="text-xs text-muted-foreground">
                            Stock: {variant.stock_quantity}
                          </p>
                        </div>
                      </div>
                    </button>
                  ))}
                </div>
              </div>
            </div>
          )}

          <Button className="w-full" size="lg">
            Add to Cart
          </Button>
        </div>
      </div>
    </div>
  );
};

export default ProductDetails;
