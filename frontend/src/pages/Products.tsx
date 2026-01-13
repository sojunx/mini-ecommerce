import { Button } from "@/components/ui/button";
import {
  InputGroup,
  InputGroupAddon,
  InputGroupInput,
} from "@/components/ui/input-group";
import { mockProducts } from "@/lib/data";
import { SearchIcon } from "lucide-react";
import { useState } from "react";

interface Product {
  id: string;
  name: string;
  image_url: string;
  base_price: number;
}

const Products = () => {
  const data = mockProducts.data.products;
  const [products, setProducts] = useState<Product[] | null>(data);

  return (
    <div className="flex-1 px-4 space-y-4">
      <section className="flex items-center justify-between">
        <h1 className="text-xl">Products</h1>

        <InputGroup className="w-72">
          <InputGroupInput placeholder="Search product..." />
          <InputGroupAddon>
            <SearchIcon />
          </InputGroupAddon>
        </InputGroup>
      </section>

      <div className="space-x-3">
        <Button className="rounded-full px-3!" variant={"secondary"}>
          Category here
        </Button>

        <Button
          className="rounded-full px-3! text-muted-foreground"
          variant={"ghost"}
        >
          Category here
        </Button>
        <Button
          className="rounded-full px-3! text-muted-foreground"
          variant={"ghost"}
        >
          Category here
        </Button>
      </div>

      <div className="grid lg:grid-cols-4 xl:grid-cols-7 gap-3">
        {products?.map((product) => (
          <div
            key={product.id}
            className="border rounded-md h-70 p-3 flex flex-col justify-between"
          >
            <img
              src={product?.image_url}
              alt={product?.name}
              className="w-full h-48 object-cover rounded"
            />
            <section>
              <h1 className="font-medium text-sm">{product?.name}</h1>
              <p className="text-muted-foreground">$ {product?.base_price}</p>
            </section>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Products;
