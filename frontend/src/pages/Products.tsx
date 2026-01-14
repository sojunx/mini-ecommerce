import { Button } from "@/components/ui/button";
import {
  InputGroup,
  InputGroupAddon,
  InputGroupInput,
} from "@/components/ui/input-group";
import { mockProducts } from "@/lib/data";
import type { Product } from "@/lib/types";
import { Search } from "lucide-react";
import { useState } from "react";
import { Link } from "react-router";

const Products = () => {
  const [products, setProducts] = useState<Product[] | null>(
    mockProducts.data.products
  );

  // useEffect(() => {
  //   const getData = async () => {
  //     try {
  //       const res = await http.get("/api/products");
  //       const { data } = res.data;
  //       setProducts(data.products);
  //     } catch (error) {
  //       console.error("Error fetching products:", error);
  //     }
  //   };

  //   getData();
  // }, []);

  return (
    <div className="flex-1 flex flex-col px-4 pb-4 overflow-hidden">
      <section className="flex items-center justify-between py-4">
        <h1 className="text-xl">Products</h1>

        <InputGroup className="w-72">
          <InputGroupInput placeholder="Search product..." />
          <InputGroupAddon>
            <Search />
          </InputGroupAddon>
        </InputGroup>
      </section>

      <div className="space-x-3 pb-4">
        <Button className="rounded-full px-3" variant={"secondary"}>
          Category here
        </Button>

        <Button
          className="rounded-full px-3 text-muted-foreground"
          variant={"ghost"}
        >
          Category here
        </Button>
        <Button
          className="rounded-full px-3 text-muted-foreground"
          variant={"ghost"}
        >
          Category here
        </Button>
      </div>

      <div className="flex-1 overflow-y-auto space-y-4 hide-scrollbar">
        {products?.map((product) => (
          <div
            key={product.id}
            className="border rounded-md p-4 flex items-center justify-between"
          >
            <div>
              <h2 className="font-medium text-sm">{product.name}</h2>
              <p className="text-xs text-muted-foreground mt-1">
                {product.description}
              </p>
            </div>
            <div className="flex items-center gap-3">
              <p className="text-muted-foreground">$ {product.price}</p>
              <Button size="sm" variant="outline" asChild>
                <Link to={`/products/${product.id}`}>View</Link>
              </Button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Products;
