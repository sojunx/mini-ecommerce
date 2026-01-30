import type { Product } from "@/types/product";
import { Link, useLoaderData } from "react-router";

const ShopPage = () => {
  const data = useLoaderData<Product[]>();

  return (
    <div className="grid grid-cols-4 gap-4">
      {data.map((product) => (
        <Link to={`/product/${product.id}`} key={product.id} className="card">
          <img
            src={product.image}
            alt={product.name}
            draggable={false}
            className="aspect-square object-cover"
          />
          <div className="p-3 border-t">
            <h2>{product.name}</h2>
            <p className="text-xl font-medium">${product.price}</p>
          </div>
        </Link>
      ))}
    </div>
  );
};

export default ShopPage;
