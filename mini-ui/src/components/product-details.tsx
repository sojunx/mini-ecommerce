import ProductActions from "@/components/product-actions";
import ProductDescription from "@/components/product-description";
import type { Product } from "@/types/product";

const ProductDetails = ({ product }: { product: Product }) => {
  return (
    <div className="grid grid-cols-7 gap-16 select-none">
      <div className="col-span-3 outline rounded">
        <img
          className="w-full h-full object-cover"
          draggable={false}
          src={product.image}
          alt={product.name}
        />
      </div>

      <div className="col-span-4 flex flex-col justify-between">
        <section className="space-y-8">
          <h1 className="text-2xl font-medium">{product.name}</h1>
          <ProductDescription description={product.description} />
        </section>

        <ProductActions product={product} />
      </div>
    </div>
  );
};

export default ProductDetails;
