import {
  InputGroup,
  InputGroupAddon,
  InputGroupInput,
} from "@/components/ui/input-group";
import {
  Pagination,
  PaginationContent,
  PaginationEllipsis,
  PaginationItem,
  PaginationLink,
  PaginationNext,
  PaginationPrevious,
} from "@/components/ui/pagination";
import type { Pageable } from "@/types";
import type { Product } from "@/types/product";
import { Search } from "lucide-react";
import { useEffect, useState } from "react";
import { Link, useLoaderData, useSearchParams } from "react-router";

const ShopPage = () => {
  const { content, page } = useLoaderData<{
    content: Product[];
    page: Pageable;
  }>();

  const [searchParams, setSearchParams] = useSearchParams();
  const currentPage = Number(searchParams.get("page") ?? 0);
  const nameParam = searchParams.get("name") ?? "";
  const [searchValue, setSearchValue] = useState(nameParam);

  useEffect(() => {
    setSearchValue(nameParam);
  }, [nameParam]);

  function getVisiblePages(current: number, total: number) {
    if (total <= 3) {
      return Array.from({ length: total }, (_, i) => i);
    }

    if (current === 0) {
      return [0, 1, 2];
    }

    if (current === total - 1) {
      return [total - 3, total - 2, total - 1];
    }

    return [current - 1, current, current + 1];
  }

  const totalPages = page?.total_pages ?? 0;
  const pages = getVisiblePages(currentPage, totalPages);

  const buildPageHref = (pageIndex: number) => {
    const params = new URLSearchParams(searchParams);
    params.set("page", String(pageIndex));
    if (!nameParam) {
      params.delete("name");
    }
    return `?${params.toString()}`;
  };

  const handleSearchSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const value = searchValue.trim();

    setSearchParams((prev) => {
      const params = new URLSearchParams(prev);
      params.set("page", "0");
      if (value) {
        params.set("name", value);
      } else {
        params.delete("name");
      }
      return params;
    });
  };

  return (
    <div className="space-y-4">
      <div className="flex items-end justify-between">
        <section>
          <h1 className="text-4xl font-serif">Shop</h1>
          <p className="text-lg font-serif text-muted-foreground">
            Browse our collection of products.
          </p>
        </section>

        <form onSubmit={handleSearchSubmit}>
          <InputGroup className="max-w-xs">
            <InputGroupInput
              placeholder="Search..."
              value={searchValue}
              onChange={(event) => setSearchValue(event.target.value)}
            />
            <InputGroupAddon>
              <Search />
            </InputGroupAddon>
            {/* <InputGroupAddon align="inline-end">12 results</InputGroupAddon> */}
          </InputGroup>
        </form>
      </div>

      {content.length === 0 ? (
        <p className="text-sm text-muted-foreground">No products found.</p>
      ) : (
        <div className="grid grid-cols-5 gap-4">
          {content.map((product) => (
            <Link
              to={`/product/${product.id}`}
              key={product.id}
              className="card"
            >
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
      )}

      {totalPages > 1 && (
        <Pagination>
          <PaginationContent>
            <PaginationItem>
              <PaginationPrevious
                aria-disabled={currentPage === 0}
                href={buildPageHref(Math.max(currentPage - 1, 0))}
              />
            </PaginationItem>
            {pages.map((p) => (
              <PaginationItem key={p}>
                <PaginationLink
                  isActive={p === currentPage}
                  href={buildPageHref(p)}
                >
                  {p + 1}
                </PaginationLink>
              </PaginationItem>
            ))}
            {totalPages > 3 && currentPage < totalPages - 1 && (
              <PaginationItem>
                <PaginationEllipsis />
              </PaginationItem>
            )}
            <PaginationItem>
              <PaginationNext
                aria-disabled={currentPage === totalPages - 1}
                href={buildPageHref(Math.min(currentPage + 1, totalPages - 1))}
              />
            </PaginationItem>
          </PaginationContent>
        </Pagination>
      )}
    </div>
  );
};

export default ShopPage;
